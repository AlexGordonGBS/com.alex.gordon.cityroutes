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
/**
 * <p> Test class example for servie class </p>
 * @author Alex Gordon
 *
 */
public class CityRoutesServiceImplTest {

	@Mock
	/**
	 * Mock object for the loader utility class
	 */
	DataFileLoader loader;

	@InjectMocks
	/**
	 * to injects mock beans into the service bean
	 */
	CityRoutesService service = new CityRoutesServiceImpl();

	/**
	 * test method for the service layer for one particular test case when the route does exist
	 */
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
