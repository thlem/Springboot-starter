package fr.soc.api.security;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.EntityNotFoundException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

import fr.soc.business.services.UserService;
import fr.soc.data.model.User;

/**
 * <h1>The filter that manage HEADER based token security</h1>
 * <p>
 * Retrieve data from the HEADER to check if current access is authorized.
 * </p>
 * 
 * @author thomas.lemercier.pro@gmail.com
 *
 */
@Component
public class ApiSecurityFilter extends GenericFilterBean {

	private static final Logger LOGGER = LoggerFactory.getLogger(ApiSecurityFilter.class);

	@Autowired
	private UserService userService;

	/**
	 * Add a filter for each request to check for authorization. Return an error in the response for unauthorized request.
	 * 
	 * @param request
	 * @param response
	 * @param chain
	 */
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		// Retrieve the token from the Header
		String authenticatedToken = ((HttpServletRequest) request)
				.getHeader(ApiSecurityConfiguration.SecurityConstant.HEADER_AUTH_TOKEN);

		// Retrieve the login from the Header
		String authenticatedLogin = ((HttpServletRequest) request)
				.getHeader(ApiSecurityConfiguration.SecurityConstant.HEADER_AUTH_LOGIN);

		LOGGER.debug("[API-SECURITY-FILTER] Current access to the REST resource with {} = {} and {} = {}",
				ApiSecurityConfiguration.SecurityConstant.HEADER_AUTH_TOKEN, authenticatedToken,
				ApiSecurityConfiguration.SecurityConstant.HEADER_AUTH_LOGIN, authenticatedLogin);

		if (null == authenticatedToken || null == authenticatedLogin) {

			// If one of the required header is missing
			changeResponseToUnauthorized(response);

		} else {

			try {

				// Retrieve the user by token and login
				User authenticatedUser = userService.getUserByAuthenticatedTokenAndUserLogin(authenticatedToken,
						authenticatedLogin);

				Set<GrantedAuthority> authorities = new HashSet<>();

				authorities.add(new SimpleGrantedAuthority(authenticatedUser.getUserRole().getRoleName().name()));

				// Populating authentication token with user information
				UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(authenticatedUser,
						authenticatedUser.getUserPassword(), authorities);

				auth.setDetails(authenticatedUser);
				SecurityContextHolder.getContext().setAuthentication(auth);
				chain.doFilter(request, response);

			} catch (EntityNotFoundException enfExc) {
				LOGGER.error("[API-SECURITY-FILTER] The {} = {} has no assiocated User",
						ApiSecurityConfiguration.SecurityConstant.HEADER_AUTH_TOKEN, authenticatedToken);

				changeResponseToUnauthorized(response);
			}

		}
	}

	/**
	 * Set the response to unauthorized.
	 * 
	 * @param response
	 * @throws IOException
	 */
	private void changeResponseToUnauthorized(ServletResponse response) throws IOException {
		((HttpServletResponse) response).setContentType(MediaType.APPLICATION_JSON_VALUE);
		((HttpServletResponse) response).setStatus(HttpServletResponse.SC_UNAUTHORIZED);
		((HttpServletResponse) response).getOutputStream().println("{ \"error\": \"UNAUTHORIZED\" }");
	}
}
