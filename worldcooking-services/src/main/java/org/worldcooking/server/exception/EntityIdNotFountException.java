package org.worldcooking.server.exception;

public class EntityIdNotFountException extends ServiceException {

	private static final long serialVersionUID = -8220830203049737289L;

	private final Class<?> persistentClass;

	private Object id;

	public EntityIdNotFountException(Class<?> persistentClass, Object id) {
		this.persistentClass = persistentClass;
		this.id = id;
	}

	public Object getId() {
		return id;
	}

	public Class<?> getPersistentClass() {
		return persistentClass;
	}

	@Override
	protected String getSimpleErrorMessage() {
		String cause = "entity '" + this.getPersistentClass() + "' of id '"
				+ this.getId() + "' was not fount";

		return cause;
	}
}
