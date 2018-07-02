package com.alex.gordon.cityroutes.service;

import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alex.gordon.cityroutes.util.DataFileLoader;

@Service
public class CityRoutesServiceImpl implements CityRoutesService {
	
	@Autowired
	private DataFileLoader dataLoader;

	@Override
	public String connected(String origin, String destination) {
		// TODO Auto-generated method stub
		//load data file
		try {
			Map<String, Set<String>> routes = dataLoader.buildRoutesMap();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		return "Not implemented yet!";
	}

}
