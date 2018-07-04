package com.alex.gordon.cityroutes.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alex.gordon.cityroutes.util.CityRoutesConstants;
import com.alex.gordon.cityroutes.util.DataFileLoader;

/**
 * <p>
 * Service implementation class.
 * </p>
 * <p>
 * <li>Reads data file Builds Map data structure</li>
 * <li>Loads data into the Map<String, Set<String>></li>
 * <li>Processes the request</li>
 * 
 * </p>
 * 
 * @author Alex Gordon
 *
 */
@Service
public class CityRoutesServiceImpl implements CityRoutesService {
	private static final Logger LOGGER = LoggerFactory.getLogger(CityRoutesServiceImpl.class);

	private List<String> citiesChecked = new ArrayList<>();

	@Autowired
	private DataFileLoader dataLoader;

	@Override
	public String connected(String origin, String destination) {
		LOGGER.info(String.format("Received request for origin=%s and destination=%s", origin, destination));
		String result;
		if (origin.equals(destination)) {
			result = CityRoutesConstants.YES;
		} else {
			Map<String, Set<String>> routes = null;
			try {
				routes = dataLoader.buildRoutesMap();
				LOGGER.info(String.format("routes=%s", routes));
			} catch (Exception e) {
				throw new RuntimeException("BAD ERROR! Could not build the routes map!");
			}
			if (routeExists(origin, destination, routes)) {
				result = CityRoutesConstants.YES;
			} else {
				result = CityRoutesConstants.NO;
			}
		}
		LOGGER.info(String.format("Processed request for origin=%s and destination=%s with result=%s", origin, destination, result));
		return result;
	}

	/**
	 * <p>
	 * This method calls itself recursively until hitting the "base" case, then it returns
	 * </p>
	 * <p>
	 * While traversing over the Map<String, Set<String>> we keep adding cities to the List<String>
	 * citiesChecked list to keep track of the cities we have already visited and checked for routes.
	 * This is very important for the algorithm and allows to avoid cycling and endless loops if the
	 * input data contains cycles.
	 * </p>
	 * 
	 * @param city1
	 * @param city2
	 * @param routes
	 * @return boolean - does the route exist?
	 */
	private boolean routeExists(String city1, String city2, Map<String, Set<String>> routes) {
		if (!routes.containsKey(city1) || !routes.containsKey(city2)) {
			return false;
		}
		//The base case in recursion. 
		if (routes.get(city1).contains(city2))
			return true;
		else {
			// the "recursive" case in recursion
			citiesChecked.add(city1);
			for (String cityConnected : routes.get(city1)) {
				if (!citiesChecked.contains(cityConnected) && routeExists(cityConnected, city2, routes))
					return true;
			}
		}
		return false;
	}

}
