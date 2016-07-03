package com.ttnd.springSecurity.configuration.initializers;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.springframework.util.SocketUtils;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import com.ttnd.springSecurity.configuration.AppConfig;

public class WebAppInitializer implements WebApplicationInitializer {

	public void onStartup(ServletContext container) throws ServletException {
		 // Create the 'root' Spring application context
		System.out.println(">>>>>>>>>>>><<<<<<<<<<<<>>>>>>>>>>>>>><<<<<");
	      AnnotationConfigWebApplicationContext rootContext =
	        new AnnotationConfigWebApplicationContext();
	      rootContext.register(AppConfig.class);
	      System.out.println(">>>>>>>>>>>><<<<<<<<<<<<>>>>>>>>>>>>>><<<<<");
	      // Manage the lifecycle of the root application context
	      container.addListener(new ContextLoaderListener(rootContext));
	      System.out.println(">>>>>>>>>>>><<<<<<<<<<<<>>>>>>>>>>>>>><<<<<");
	      // Register and map the dispatcher servlet
	      ServletRegistration.Dynamic dispatcher =
	      container.addServlet("dispatcher", new DispatcherServlet(rootContext));
	      dispatcher.setLoadOnStartup(1);
	      dispatcher.addMapping("/");
	      
	      
	    }
		

}
