package com.configs;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.services.DbService;

@Configuration
@Profile("test")
public class TestConfig {

	@Autowired
	private DbService _dbService;

	@Bean
	public boolean instaciateDataBase() throws ParseException {

		_dbService.instanciateDataBase();
		return true;
	}
}
