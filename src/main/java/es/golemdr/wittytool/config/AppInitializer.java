package es.golemdr.wittytool.config;

import javax.servlet.Filter;

import org.springframework.web.filter.DelegatingFilterProxy;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

/**
 * Initialize the Servlet container. This class is detected by the Servlet
 * container on startup.
 */
public class AppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer{

	
	// Specify @Configuration and/or @Component classes for the root application context. (Cuando usamos spring-secutiry hay que incluir SecurityConfig.class)
	@Override
	protected Class<?>[] getRootConfigClasses() {
		return new Class[] { RootConfig.class , SecurityConfig.class};
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		return new Class[] { WebMvcConfig.class };
	}

	@Override
	protected String[] getServletMappings() {
		return new String[] { "/" };
	}

	@Override
	protected Filter[] getServletFilters() {
		return new Filter[] { new DelegatingFilterProxy("csrfFilter") };
	}
	
	

}
