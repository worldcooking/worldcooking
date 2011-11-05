/**
 * 
 */
package org.worldcooking.web.util;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;

/**
 * Mapper used for model <-> view conversion.
 * 
 * @author MatthieuG
 * 
 */
public class ModelViewMapper {

	private static ModelViewMapper instance;

	private Mapper mapper;

	private ModelViewMapper() {
		mapper = new DozerBeanMapper();
	}

	public static ModelViewMapper getInstance() {
		if (instance == null) {
			instance = new ModelViewMapper();
		}
		return instance;
	}

	/**
	 * Create an object from a source object.
	 * 
	 * @param source
	 *            Original object to map.
	 * @param d
	 *            Type of object to create from source.
	 * @return a new D object create from source
	 */
	@SuppressWarnings("unchecked")
	public <D extends Object, S> D map(S source, Class<? extends Object> D) {
		return (D) mapper.map(source, D);
	}

	/**
	 * Complete an object using an other object.
	 * 
	 * @param source
	 *            Source object.
	 * @param destination
	 *            Destination object (not null)
	 */
	public <D extends Object, S> void completeWithMapping(S source,
			D destination) {
		mapper.map(source, destination);
	}

}
