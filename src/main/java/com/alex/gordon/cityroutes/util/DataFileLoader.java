package com.alex.gordon.cityroutes.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class DataFileLoader {
	private static final Logger LOGGER = LoggerFactory.getLogger(DataFileLoader.class);

	@Value("${dataFileName:unknownFile}")
	private String fileName;

	public Map<String, Set<String>> buildRoutesMap() {
		Map<String, Set<String>> routes = new HashMap<>();
		BufferedReader br = new BufferedReader(new InputStreamReader(DataFileLoader.class.getResourceAsStream(fileName)));
		try {
			if (br.ready()) {
				String line;
				while ((line = br.readLine()) != null) {
					// ignore dirty data - skip empty lines
					if (!line.trim().equals("")) {
						String cities[] = line.split(",");
						// ignore dirty data - skip single term lines
						if (cities.length == 2) {
							String city1 = cities[0].trim();
							String city2 = cities[1].trim();
							// adding the routes in both directions - city1 <--> city2 !!
							addCityToMap(routes, city1, city2);
							addCityToMap(routes, city2, city1);
						}
					}
				}
			}
			return routes;
		} catch (IOException e) {
			LOGGER.error("Can not read file " + fileName);
			throw new RuntimeException("Can not read file " + fileName);
		} finally {
			try {
				br.close();
			} catch (IOException e) {
				// do nothing
			}
		}
	}

	private static void addCityToMap(Map<String, Set<String>> routes, String cityKey, String cityRoute) {
		if (!routes.containsKey(cityKey)) {
			Set<String> cityRouteList = new HashSet<>();
			cityRouteList.add(cityRoute);
			routes.put(cityKey, cityRouteList);
		} else {
			routes.get(cityKey).add(cityRoute);
		}
	}

}
