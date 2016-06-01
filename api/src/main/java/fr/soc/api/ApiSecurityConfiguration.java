package fr.soc.api;

import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

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

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		// Security config to allow some Role or anybody to acces to a REST
		// resource
		http.authorizeRequests()
		.antMatchers("/swagger-ui**").permitAll()
		.antMatchers(HttpMethod.GET, "/api/**").permitAll();
		// .antMatchers(HttpMethod.GET, "/api/bordereaux/**").authenticated()
		// .antMatchers("/api/utilisateurs").hasAuthority(ADMIN.name())
	}

}
