package com.alex.gordon.cityroutes.service;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.doReturn;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.alex.gordon.cityroutes.util.DataFileLoader;

@RunWith(MockitoJUnitRunner.class)
public class CityRoutesServiceImplTest {

	@Mock
	DataFileLoader loader;

	@InjectMocks
	CityRoutesService service = new CityRoutesServiceImpl();

	@Test
	public void connectedTest1() {
		Map<String, Set<String>> map = new HashMap<>();
		Set<String> set = new HashSet<>();
		set.add("a");
		set.add("b");
		map.put("x", set);
		set = new HashSet<>();
		set.add("x");
		map.put("a", set);

		doReturn(map).when(loader).buildRoutesMap();

		String origin = "x";
		String destination = "a";
		String expected = "yes";

		String actual = service.connected(origin, destination);

		assertTrue(expected.equals(actual));
	}

}
