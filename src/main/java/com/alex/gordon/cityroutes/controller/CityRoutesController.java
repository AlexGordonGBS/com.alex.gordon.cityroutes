package com.alex.gordon.cityroutes.controller;

import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alex.gordon.cityroutes.service.CityRoutesService;

@RestController
public class CityRoutesController implements ConnectedApi {
	
	@Autowired
	CityRoutesService service;

	@RequestMapping(value = "/connected", produces = { "application/json" }, method = RequestMethod.GET)
	@Override
	public ResponseEntity<String> connected(
			@NotNull @RequestParam(value = "origin", required = true) String origin,
	    @NotNull @RequestParam(value = "destination", required = true) String destination) {
		return new ResponseEntity<String>(service.connected(origin, destination), HttpStatus.OK);
	}

}
