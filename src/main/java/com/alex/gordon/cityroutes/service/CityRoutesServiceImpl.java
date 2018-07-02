package com.alex.gordon.cityroutes.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alex.gordon.cityroutes.util.DataFileLoader;

@Service
public class CityRoutesServiceImpl implements CityRoutesService {
	private List<String> citiesChecked = new ArrayList<>();

	@Autowired
	private DataFileLoader dataLoader;

	@Override
	public String connected(String origin, String destination) {
		String result;
		String city1 = origin;
		String city2 = destination;
		Map<String, Set<String>> routes = null;
		try {
			routes = dataLoader.buildRoutesMap();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (routeExists(city1, city2, routes)) {
			result = "yes";
		} else {
			result = "no";
		}
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
