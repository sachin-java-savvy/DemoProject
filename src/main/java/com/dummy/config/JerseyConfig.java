package com.dummy.config;

import com.dummy.csv.controller.CsvController;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JerseyConfig extends ResourceConfig {

	public JerseyConfig() {
		register(CsvController.class);
	}
}