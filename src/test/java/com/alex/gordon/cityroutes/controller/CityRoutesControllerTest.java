package com.alex.gordon.cityroutes.controller;

import static org.junit.Assert.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.alex.gordon.cityroutes.Application;

@RunWith(SpringRunner.class)
@SpringBootTest (classes = Application.class)
@WebAppConfiguration
public class CityRoutesControllerTest {

	@Autowired
	private WebApplicationContext webContext;

	private MockMvc mockMvc;

	@Before
	public void setupMockMvc() {
		mockMvc = MockMvcBuilders.webAppContextSetup(webContext)
				.build();
	}
	
	@Test
	public void controller_test() throws Exception {
		String body = mockMvc.perform(get("/connected?origin=New York&destination=Boston"))
		 .andExpect(status().isOk())
		 .andReturn()
		 .getResponse()
		 .getContentAsString();
		
		assertTrue(body.equals("yes"));

	}


}
