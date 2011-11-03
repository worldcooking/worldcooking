package org.worldcooking.server.exception;

public abstract class ServiceException extends Exception {

	private static final long serialVersionUID = 382222061780388726L;

	abstract protected String getSimpleErrorMessage();

	/**
	 * Return a formated message of the error. Recursive call to
	 * getSimpleErrorMessage().
	 * 
	 * @return
	 */
	public String getErrorMessage() {
		String cause = getMessage();
		if (this.getCause() instanceof ServiceException) {
			cause += " (because "
					+ ((ServiceException) this.getCause())
							.getSimpleErrorMessage() + ")";
		}
		return cause;
	}
}
