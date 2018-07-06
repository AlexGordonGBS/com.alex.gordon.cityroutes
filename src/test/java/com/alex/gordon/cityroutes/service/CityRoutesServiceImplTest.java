package com.alex.gordon.cityroutes.service;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;

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
 * <p>
 * Test class example for servie class
 * </p>
 * 
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

	/**
	 * test method for the service layer for one particular test case when the route does NOT exist
	 */
	@Test
	public void connectedTest2() {
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
		String destination = "z";
		String expected = "no";

		String actual = service.connected(origin, destination);

		assertTrue(expected.equals(actual));
	}

	/**
	 * test method for the service layer for one particular test case when the INDIRECT route does exist
	 * ==> recursive part of the algorithm would be called
	 */
	@Test
	public void connectedTest3() {
		Map<String, Set<String>> map = new HashMap<>();

		// imitate route x==>a==>b==>c==d==>e
		// thus x==>e
		Set<String> set = new HashSet<>();
		set.add("a");
		map.put("x", set);
		set = new HashSet<>();
		set.add("x");
		map.put("a", set);

		set = new HashSet<>();
		set.add("b");
		map.put("a", set);
		set = new HashSet<>();
		set.add("a");
		map.put("b", set);

		set = new HashSet<>();
		set.add("b");
		map.put("c", set);
		set = new HashSet<>();
		set.add("c");
		map.put("b", set);

		set = new HashSet<>();
		set.add("c");
		map.put("d", set);
		set = new HashSet<>();
		set.add("d");
		map.put("c", set);

		set = new HashSet<>();
		set.add("d");
		map.put("e", set);
		set = new HashSet<>();
		set.add("e");
		map.put("d", set);

		doReturn(map).when(loader).buildRoutesMap();

		String origin = "a";
		String destination = "e";
		String expected = "yes";

		String actual = service.connected(origin, destination);

		assertTrue(expected.equals(actual));
	}

	/**
	 * test method for the service layer when an error occures in loader
	 */
	@Test(expected = RuntimeException.class)
	public void connectedTest_error() {
		Map<String, Set<String>> map = new HashMap<>();

		// imitate route x==>a==>b==>c==d==>e
		// thus x==>e
		Set<String> set = new HashSet<>();
		set.add("a");
		map.put("x", set);
		set = new HashSet<>();
		set.add("x");
		map.put("a", set);

		doThrow(new RuntimeException()).when(loader).buildRoutesMap();

		String origin = "a";
		String destination = "x";
		String expected = "yes";

		String actual = service.connected(origin, destination);
	}

}
