package com.hung.le.config;

import javax.servlet.FilterRegistration;
import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import com.hung.le.site.AuthenticationFilter;
import com.hung.le.site.LoggingFilter;

/*
 * Springframework is a container so it must be started and given instructions how to run the application
 */
@SuppressWarnings("unused")
public class BootStrap implements WebApplicationInitializer{
	
	private static final Logger log = LogManager.getLogger();

	@Override
	public void onStartup(ServletContext container) throws ServletException {

		//register the default servlet for static resources
		container.getServletRegistration("default").addMapping("/resource/*");
		
		AnnotationConfigWebApplicationContext rootContext = new AnnotationConfigWebApplicationContext();
		rootContext.register(RootContextConfiguration.class);
		container.addListener(new ContextLoaderListener(rootContext));
		
		AnnotationConfigWebApplicationContext servletContext = new AnnotationConfigWebApplicationContext();
		servletContext.register(ServletContextConfiguration.class);
		
		ServletRegistration.Dynamic dispatcher = container.addServlet("springDispatcher",  new DispatcherServlet(servletContext));
		dispatcher.setLoadOnStartup(1);
		dispatcher.setMultipartConfig(new MultipartConfigElement(null, 20_971_520L, 41_943_040L, 512_000));
		dispatcher.addMapping("/");
		
		FilterRegistration.Dynamic registration = container.addFilter("loggingFilter",  new LoggingFilter());
		registration.addMappingForUrlPatterns(null,  false,  "/*");
		registration = container.addFilter("authenticationFilter",  new AuthenticationFilter());
		registration.addMappingForUrlPatterns(null,  false,  "/ticket", "/ticket/*", "/chat", "/chat/*", "/session", "/session/*");
		
		log.info("BOOTSTRAP HAS BEEN INVOKED. FILTERS HAVE BEEN REGISTERED.");
		System.out.println("BOOTSTRAP HAS BEEN INVOKED. FILTERS HAVE BEEN REGISTERED.");
	}

	
}
