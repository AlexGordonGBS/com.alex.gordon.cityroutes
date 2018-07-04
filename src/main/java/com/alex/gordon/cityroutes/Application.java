package com.alex.gordon.cityroutes;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * <p> Main class for SrpingBoot application. </p>
 * <p> The main() method of this class is called when the SpringBoot application is being started.</p>
 * @author Alex Gordon
 */
@SpringBootApplication(scanBasePackages = { "com.alex.gordon.cityroutes" })
@EnableSwagger2
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}
