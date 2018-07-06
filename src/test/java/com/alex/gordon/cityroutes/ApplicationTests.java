package com.alex.gordon.cityroutes;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.client.MockRestServiceServer;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
/**
 * <p>
 * A typical test application class for the SpringBoot application </p/
 * 
 * @author Alex Gordon
 *
 */
public class ApplicationTests {

	protected MockRestServiceServer mockRestServer;

	@Test
	public void contextLoads() {
	}

}
