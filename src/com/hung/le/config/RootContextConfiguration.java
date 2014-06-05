package com.hung.le.config;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Bean;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import org.springframework.stereotype.Controller;

@Configuration
@ComponentScan(basePackages = "com.hung.le.site", excludeFilters = @ComponentScan.Filter(Controller.class))
public class RootContextConfiguration {
	
	private static final Logger log = LogManager.getLogger();

	
	@Bean
	public ObjectMapper objectMapper(){
		
		log.info("Object Mapper has been created in root context.");
		ObjectMapper mapper = new ObjectMapper();
		mapper.findAndRegisterModules();
		mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
		mapper.configure(DeserializationFeature.ADJUST_DATES_TO_CONTEXT_TIME_ZONE, false);
		
		return mapper;
	}
	
	@Bean
	public Jaxb2Marshaller jaxb2Marshaller(){
		log.info("jaxb2 Marshaller has been created in root context.");
		Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
		marshaller.setPackagesToScan(new String[] { "com.hung.le.site" });
		
		return marshaller;
	}
	
}
