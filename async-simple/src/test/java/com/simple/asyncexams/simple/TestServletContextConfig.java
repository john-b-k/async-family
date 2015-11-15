package com.simple.asyncexams.simple;

import java.util.logging.Logger;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@EnableWebMvc
@ComponentScan(
        basePackages = "com.simple.asyncexams.simple",
        useDefaultFilters = false,
        includeFilters = @ComponentScan.Filter(Controller.class)
)
public class TestServletContextConfig {
	private static final Logger logger = Logger.getLogger(ServletContextConfig.class.getName());
}
