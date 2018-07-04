package com.alex.gordon.cityroutes.service;

/**
 * <p> Service Interface.</p>
 * @author Alex Gordon
 *
 */
public interface CityRoutesService {
	
	/**
	 * <p> method Connected</p>
	 * @param origin
	 * @param destination
	 * @return String:
	 * <li>  - yes if route exists between origin and destination</li>
	 * <li>  - no if route does NOT exist between origin and destination or invalid data was entered</li>
	 * 
	 */
	String connected(String origin, String destination);

}
