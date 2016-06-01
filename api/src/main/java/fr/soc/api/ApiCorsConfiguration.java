package fr.soc.api;

import org.springframework.http.HttpHeaders;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * <h1>The API CORS configuration</h1>
 * 
 * <p>
 * This manages request details.
 * </p>
 * 
 * @author thomas.lemercier.pro@gmail.com
 *
 */
public class ApiCorsConfiguration extends WebMvcConfigurerAdapter {

	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry
			.addMapping("/api/**")
			.allowedOrigins("*")
			.allowedMethods("POST", "GET", "DELETE", "PUT")
			.allowedHeaders(HttpHeaders.ACCESS_CONTROL_ALLOW_HEADERS, HttpHeaders.CONTENT_TYPE)
			.allowCredentials(false)
			.maxAge(3600);
	}

}
