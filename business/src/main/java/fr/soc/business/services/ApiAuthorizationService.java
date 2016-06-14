package fr.soc.business.services;

import fr.soc.data.model.ApiAuthorization;

/**
 * Interface of the service SecurityServiceImpl
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
	public String getAllByApiValue(String apiValue);
	
	/**
	 * 
	 */
	public ApiAuthorization createApiAuthorization(ApiAuthorization apiAuthorization);

}
