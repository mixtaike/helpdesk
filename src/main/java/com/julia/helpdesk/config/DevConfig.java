package com.julia.helpdesk.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.julia.helpdesk.services.DBService;

@Configuration
@Profile("test")
public class DevConfig {
	
	@Autowired
	private DBService dbService;
	
	@Bean
	public void instanciaDB( ) {
		this.dbService.instanciaDB();
	}
	
	

}
