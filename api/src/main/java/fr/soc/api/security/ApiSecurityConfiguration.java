package fr.soc.api.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.AnonymousAuthenticationFilter;

import fr.soc.business.services.ApiAuthorizationService;

/**
 * <h1>The Application security initialization</h1>
 * 
 * <p>
 * This manages the security of the Application.
 * </p>
 * 
 * @author thomas.lemercier.pro@gmail.com
 *
 */
@Configuration
// keeps all the defaults set by Spring Boot, only overriding them in this file
@Order(SecurityProperties.ACCESS_OVERRIDE_ORDER)
public class ApiSecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Autowired
	private ApiSecurityFilter apiSecurityFilter;

	@Autowired
	private ApiAuthorizationService securityService;

	/*
	 * @Autowired private ApiSecurityExceptionHandler
	 * apiSecurityExceptionHandler;
	 */

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		// Security config to allow some Role or anybody to acces to a REST
		// resource
		http
		// The service that provide checks for authenticated user
		.addFilterBefore(apiSecurityFilter, AnonymousAuthenticationFilter.class)
		.authorizeRequests().antMatchers("/swagger-ui**").permitAll()
		.antMatchers(HttpMethod.GET, "/api/admin/**").access(securityService.getAllByApiValue("get/api/admin/**"))
		.antMatchers(HttpMethod.POST, "/api/admin/**").access(securityService.getAllByApiValue("post/api/admin/**"))
		.antMatchers(HttpMethod.GET, "/api/**").access(securityService.getAllByApiValue("get/api/**"));
	}

	/**
	 *
	 */
	public class SecurityConstant {

		public final static String HEADER_AUTH_TOKEN = "AUTH_TOKEN";
		public final static String HEADER_AUTH_LOGIN = "AUTH_LOGIN";

	}

}
