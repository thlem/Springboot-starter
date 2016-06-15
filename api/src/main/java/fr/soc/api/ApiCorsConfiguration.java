package fr.soc.api;

import org.springframework.http.HttpHeaders;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * <h1>The API CORS configuration</h1>
 * 
 * <p>
 * For security reasons, browsers prohibit AJAX calls to resources residing outside the current origin.
 * </p>
 * <p>
 * We specify here in a flexible way what kind of cross domain requests are authorized.
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
