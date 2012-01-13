package org.worldcooking.service.admin;

import java.util.List;

import org.mishk.business.event.entity.Event;
import org.mishk.business.event.entity.Registration;
import org.mishk.business.event.entity.Role;
import org.mishk.business.event.entity.Task;
import org.mishk.business.event.service.RegistrationService;
import org.mishk.business.event.service.TaskService;
import org.mishk.business.event.service.model.NewRegistration;
import org.mishk.business.event.service.model.RegistrationProduct;
import org.mishk.business.shop.entity.PaymentMode;
import org.mishk.business.shop.entity.PaymentStatus;
import org.mishk.business.shop.entity.Shopping;
import org.mishk.business.shop.service.CatalogService;
import org.mishk.business.shop.service.PaymentService;
import org.mishk.business.shop.service.ShoppingService;
import org.oupsasso.mishk.business.shop.exception.InsufficientStockException;
import org.oupsasso.mishk.core.dao.exception.EntityIdNotFoundException;
import org.oupsasso.mishk.core.dao.exception.EntityNotFoundException;
import org.oupsasso.mishk.core.dao.exception.EntityReferenceNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class WorldcookingService {

	private static final String SHOPPING_REF_PREFIX = "wk";

	private static final String STOCK_REF = "worldcooking-participants-roles";

	private Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private CatalogService catalogService;

	@Autowired
	private ShoppingService shoppingService;

	@Autowired
	private PaymentService paymentService;

	@Autowired
	private TaskService taskService;

	@Autowired
	private RegistrationService registrationService;

	public Registration register(NewRegistration newRegistration) throws EntityNotFoundException,
			InsufficientStockException {

		// create shopping
		Shopping shopping = shoppingService.createShoppingWithShoppingBag(SHOPPING_REF_PREFIX);

		// persist registration
		Registration registration = registrationService.register(newRegistration, shopping.getReference());

		// add participants
		registrationService.addParticipants(registration, newRegistration.getSubscriber(),
				newRegistration.getAdditionalParticipants());

		List<RegistrationProduct> registrationProducts = registrationService.findRegistrationProducts(registration
				.getId());
		// add all products
		for (RegistrationProduct registrationProduct : registrationProducts) {
			shoppingService.addToShoppingBag(shopping.getShoppingBag().getId(), STOCK_REF,
					registrationProduct.getProductReference(), registrationProduct.getQuantity());
		}

		// configure payment

		PaymentMode paymentMode;
		switch (newRegistration.getPaymentMode()) {
		case MANUAL:
			paymentMode = PaymentMode.MANUAL;
			break;
		case PAYPAL:
			paymentMode = PaymentMode.PAYPAL;
			break;
		default:
			throw new RuntimeException("Unexpected error during registration.");
		}

		paymentService.createPayment(shopping, shoppingService.calculateTotal(shopping.getReference()), paymentMode,
				newRegistration.getPaymentTarget(), PaymentStatus.PENDING);

		return registration;
	}

	public Double calculateRegistrationPrice(Long registrationId) throws EntityNotFoundException {
		Registration registration = registrationService.findRegistrationById(registrationId, false);

		return shoppingService.calculateTotal(registration.getShoppingReference());
	}

	public void createNewRole(Event e, String name, String description, Long maxParticipants, Double price,
			Task... tasks) throws EntityNotFoundException {

		// create role
		Role roleChef = taskService.createNewRole(name, description, tasks);

		String productReference = createProductReferenceFromName(name);

		// associate role to event
		taskService.addRoleToEvent(e, roleChef, maxParticipants, productReference);

		// create product
		catalogService.createProduct(name, description, productReference, price);

		// add products to stock
		catalogService.addToStock(STOCK_REF, productReference, maxParticipants);

		logger.debug("New role '{}' (price={}, places={}, product reference={}) has bean created.", new Object[] {
				name, price, maxParticipants, productReference });

	}

	private String createProductReferenceFromName(String name) {
		return "worldcooking-role-" + name.toLowerCase().replaceAll(" ", "_");
	}

	public Registration validatePayment(Long registrationId) throws EntityIdNotFoundException,
			EntityReferenceNotFoundException {

		// find registration
		Registration registration = registrationService.findRegistrationById(registrationId, false);

		// validate shopping payment
		paymentService.validatePayment(registration.getShoppingReference());

		// validate registration
		return registrationService.validateRegistration(registrationId);
	}
}
