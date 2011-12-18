package org.worldcooking;

import org.hibernate.FlushMode;
import org.hibernate.SessionFactory;
import org.junit.After;
import org.junit.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

/**
 * Base class fo unit tests with spring context.
 * <p>
 * Each method is run within a transaction which is rolled back when method is
 * over. Hibernate sessions which used in tests are have
 * {@link org.hibernate.FlushMode#ALWAYS}
 * 
 * @see org.springframework.test.context.transaction.TransactionConfiguration
 * @see org.springframework.transaction.annotation.Transactional
 * @see org.hibernate.Session#setFlushMode(org.hibernate.FlushMode)
 */
@ContextConfiguration(locations = { "classpath:spring/test-spring-dao-context.xml" })
public abstract class ApplicationContextAwareTest extends AbstractApplicationContextAware {

	@Autowired
	public SessionFactory sessionFactory;

	@Override
	@Before
	public void beforeMethod() {
		sessionFactory.getCurrentSession().setFlushMode(FlushMode.ALWAYS);
	}

	@Override
	@After
	public void afterMethod() {
		// nothing
	}

}
