package org.apache.logging.log4j.audit.service.config;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.apache.logging.log4j.audit.rest.RequestContextFilter;
import org.apache.logging.log4j.audit.service.RequestContext;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

public class RequestContextInitializer implements WebApplicationInitializer {

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        RequestContextFilter filter = new RequestContextFilter(RequestContext.class);
        servletContext.addFilter("requestContextFilter", filter).addMappingForUrlPatterns(null, true, "/*");
        servletContext.log("Added RequestContextFilter");
    }
}
