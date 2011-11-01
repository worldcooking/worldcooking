/**
 * 
 */
package org.worldcooking.server.entity.payment;

/**
 * @author MatthieuG
 * 
 */
public class PaypalPaymentMode extends PaymentMode {

	private String user;

	public PaypalPaymentMode() {
		super(true);
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

}
