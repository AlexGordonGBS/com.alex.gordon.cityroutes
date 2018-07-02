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
				// never?
				// TODO build exception hierarchy for the project and exception handler aspect!!!!!!!!
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

	private boolean routeExists(String city1, String city2, Map<String, Set<String>> routes) {
		if (!routes.containsKey(city1) || !routes.containsKey(city2)) {
			return false;
		}
		if (routes.get(city1).contains(city2))
			return true;
		else {
			citiesChecked.add(city1);
			for (String cityConnected : routes.get(city1)) {
				if (!citiesChecked.contains(cityConnected) && routeExists(cityConnected, city2, routes))
					return true;
			}
		}
		return false;
	}

}
