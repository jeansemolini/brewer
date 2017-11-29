package com.algaworks.brewer.config.init;

import java.util.EnumSet;

import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.SessionTrackingMode;

import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;
import org.springframework.web.filter.CharacterEncodingFilter;

public class SecurityInitializer extends AbstractSecurityWebApplicationInitializer {
	
	@Override
	protected void beforeSpringSecurityFilterChain(ServletContext servletContext) {
//		servletContext.getSessionCookieConfig().setMaxAge(10); //tempo em segundos independete e esta ou nao usando
		servletContext.setSessionTrackingModes(EnumSet.of(SessionTrackingMode.COOKIE)); //não apareceo sessionID na URL esta sendo usado pelo COOKIE
		
		FilterRegistration.Dynamic characterEncodingFilter = servletContext.addFilter("encodingFilter",
					new CharacterEncodingFilter());
		characterEncodingFilter.setInitParameter("encoding", "UTF-8"); //qual encoding
		characterEncodingFilter.setInitParameter("forceEncoding", "true"); //forçar
		characterEncodingFilter.addMappingForUrlPatterns(null, false, "/*"); //todas URL
	}
	
}
