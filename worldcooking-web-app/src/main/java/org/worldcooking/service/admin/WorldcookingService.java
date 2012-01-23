package org.worldcooking.service.admin;

import java.util.Set;

import org.mishk.business.event.entity.Event;
import org.mishk.business.event.entity.EventRole;
import org.mishk.business.event.entity.Participant;
import org.mishk.business.event.entity.Registration;
import org.mishk.business.event.entity.Role;
import org.mishk.business.event.entity.Task;
import org.mishk.business.event.service.EventService;
import org.mishk.business.event.service.RegistrationService;
import org.mishk.business.event.service.TaskService;
import org.mishk.business.event.service.model.NewRegistration;
import org.mishk.business.event.service.model.RegistrationProduct;
import org.mishk.business.shop.entity.PaymentMode;
import org.mishk.business.shop.entity.PaymentStatus;
import org.mishk.business.shop.entity.Shopping;
import org.mishk.business.shop.exception.InsufficientStockException;
import org.mishk.business.shop.service.CatalogService;
import org.mishk.business.shop.service.PaymentService;
import org.mishk.business.shop.service.ShoppingService;
import org.oupsasso.mishk.core.dao.exception.EntityIdNotFoundException;
import org.oupsasso.mishk.core.dao.exception.EntityNotFoundException;
import org.oupsasso.mishk.core.dao.exception.EntityReferenceNotFoundException;
import org.oupsasso.mishk.core.dao.exception.ServiceException;
import org.oupsasso.mishk.security.authentication.MishkUserHolder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional(rollbackFor = ServiceException.class)
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

	@Autowired
	private EventService eventService;

	public Registration register(NewRegistration newRegistration) throws EntityNotFoundException,
			InsufficientStockException {

		// create shopping
		Shopping shopping = shoppingService.createShoppingWithShoppingBag(SHOPPING_REF_PREFIX);

		// persist registration
		Registration registration = registrationService.register(newRegistration, shopping.getReference(),
				MishkUserHolder.getUsername());

		// add participants
		registrationService.addParticipants(registration, newRegistration.getSubscriber(),
				newRegistration.getAdditionalParticipants());

		Set<RegistrationProduct> registrationProducts = registrationService.findRegistrationProducts(registration
				.getId());
		// add all products
		for (RegistrationProduct registrationProduct : registrationProducts) {
			logger.debug("Adding {} products ({}) to shopping bag ({}).",
					new Object[] { registrationProduct.getQuantity(), registrationProduct.getProductReference(),
							shopping.getReference() });
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

	/**
	 * Method synchronized to avoir conflicts.
	 */
	public synchronized Registration validatePayment(Long registrationId) throws EntityNotFoundException,
			InsufficientStockException {

		// find registration
		Registration registration = registrationService.findRegistrationById(registrationId, false);

		// validate shopping payment
		paymentService.validatePayment(registration.getShoppingReference());

		// retrieve from stock
		catalogService.retrieveShoppingBagFromStock(STOCK_REF, registration.getShoppingReference());

		// validate registration
		return registrationService.validateRegistration(registrationId);
	}

	public Registration unvalidatePayment(Long registrationId) throws EntityNotFoundException {
		// find registration
		Registration registration = registrationService.findRegistrationById(registrationId, false);

		// validate shopping payment
		paymentService.unvalidatePayment(registration.getShoppingReference());

		// add to stock
		catalogService.addShoppingBagToStock(STOCK_REF, registration.getShoppingReference());

		// unvalidate registration
		return registrationService.unvalidateRegistration(registrationId);
	}

	public Participant updateParticipantEventRole(Long participantId, Long eventRoleId) throws EntityNotFoundException,
			InsufficientStockException {
		// retrieve participant
		Participant participant = registrationService.findParticipantById(participantId);
		EventRole oldEventRole = participant.getEventRole();

		// retrieve event role
		EventRole newEventRole = eventService.findEventRoleById(eventRoleId);

		if (oldEventRole.getId() == newEventRole.getId()) {
			logger.warn("Try to updating role of participant {} with the actual role '{}'.",
					new Object[] { participant.getName(), newEventRole.getRole().getName() });
			return participant;
		}

		String shoppingReference = participant.getRegistration().getShoppingReference();
		// update shopping bag

		String productReference = newEventRole.getProductReference();

		// retrieve shopping
		Shopping shopping = shoppingService.findShoppingByReference(shoppingReference);

		// remove old product from shopping bag
		shoppingService.removeFromShoppingBag(shopping.getShoppingBag().getId(), oldEventRole.getProductReference());

		// add new product to shopping bag
		shoppingService.addToShoppingBag(shopping.getShoppingBag().getId(), STOCK_REF, productReference);

		// update registration
		registrationService.updateParticipantEventRole(participantId, eventRoleId);

		return participant;
	}

	public Registration removeRegistration(Long registrationId) throws EntityIdNotFoundException,
			EntityReferenceNotFoundException {
		// remove registration
		Registration registration = registrationService.removeRegistration(registrationId);

		shoppingService.removeShopping(registration.getShoppingReference());

		return registration;
	}

}
