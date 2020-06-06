package com.bookshopapp.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.bookshopapp.service.ProviderConnerctor;
import com.fasterxml.jackson.databind.ObjectMapper;

@Configuration
public class MyConfig {
		@Value("${user-service.rooturi}")
	    String userServiceRootUri;

		@Bean
	    public ProviderConnerctor getProviderConnector(@Autowired ObjectMapper objectMapper, @Autowired RestTemplateBuilder restTemplateBuilder) {
	        return new ProviderConnerctor(userServiceRootUri, restTemplateBuilder, objectMapper);
	    }

	}
