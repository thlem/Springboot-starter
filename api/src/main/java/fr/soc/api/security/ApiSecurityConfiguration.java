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
 * Initialize the security of the application.
 * </p>
 * <p>
 * Specify a filter for each request to manage unauthorized connections.
 * </p>
 * <p>
 * Set the list of authorized Role for each API REST resource.
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

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		// Security config to allow some Role or anybody to acces to a REST
		// resource
		http.csrf().disable()
				// The service that provide checks for authenticated user
				.addFilterBefore(apiSecurityFilter, AnonymousAuthenticationFilter.class).authorizeRequests()
				// The list of API REST resource and authorized Roles
				// Oragnize as the less generic to the most generic
				.antMatchers("/swagger-ui**").permitAll().antMatchers(HttpMethod.GET, "/api/admin/**")
				.access(securityService.getRestAuthorityByRestValue("get/api/admin/**"))
				.antMatchers(HttpMethod.POST, "/api/admin/**")
				.access(securityService.getRestAuthorityByRestValue("post/api/admin/**"))
				.antMatchers(HttpMethod.GET, "/api/**")
				.access(securityService.getRestAuthorityByRestValue("get/api/**"));
	}

	/**
	 * <h1>Inner Constant Class of @see ApiSecurityConfiguration</h1>
	 * 
	 * <p>
	 * List all HEADER specific data relative to the authentication.
	 * </p>
	 * 
	 * @author thomas.lemercier.pro@gmail.com
	 *
	 */
	public class SecurityConstant {

		public final static String HEADER_AUTH_TOKEN = "AUTH_TOKEN";
		public final static String HEADER_AUTH_LOGIN = "AUTH_LOGIN";

	}

}
