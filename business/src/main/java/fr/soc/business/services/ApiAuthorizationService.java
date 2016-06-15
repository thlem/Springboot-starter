package fr.soc.business.services;

import fr.soc.data.model.ApiAuthorization;

/**
 * Interface of the service ApiAuthorizationServiceImpl
 * 
 * @author thomas.lemercier.pro@gmail.com
 *
 */
public interface ApiAuthorizationService {

	/**
	 * Retrieve all Role as a String for security
	 * 
	 * @return List of User
	 */
	public String getRestAuthorityByRestValue(String restValue);

	/**
	 * Create the given api authorization
	 * 
	 * @param apiAuthorization
	 *            The API value and the Role associated
	 * @return The created ApiAuthorization
	 */
	public ApiAuthorization createApiAuthorization(ApiAuthorization apiAuthorization);

}
