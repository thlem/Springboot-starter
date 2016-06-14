package fr.soc.data.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import fr.soc.data.model.ApiAuthorization;

/**
 * Service that provide methods to retrieve User data from DB
 * 
 * @author thomas.lemercier.pro@gmail.com
 *
 */
@Repository
public interface ApiAuthorizationRepository extends CrudRepository<ApiAuthorization, Long> {

	/**
	 * Retrieve a User by its Login
	 * 
	 * @param userLogin
	 *            The login
	 * @return The User
	 */
	public List<ApiAuthorization> findAllByApiValue(String apiValue);

}
