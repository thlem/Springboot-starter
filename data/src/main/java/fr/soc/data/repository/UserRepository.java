package fr.soc.data.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import fr.soc.data.model.User;

/**
 * Service that provide methods to retrieve User data from DB
 * 
 * @author thomas.lemercier.pro@gmail.com
 *
 */
@Repository
public interface UserRepository extends CrudRepository<User, Long> {

	/**
	 * Retrieve a User by its Login
	 * 
	 * @param userLogin
	 *            The login
	 * @return The User
	 */
	public User findByUserLogin(String userLogin);

	/**
	 * Retrieve a User by its Mail
	 * 
	 * @param userMail
	 *            The mail
	 * @return The USer
	 */
	public User findByUserMail(String userMail);

	/**
	 * Retrieve a User by its authenticated token and its login
	 * 
	 * @param token
	 *            The token
	 * @param login
	 *            The login
	 * @return The User
	 */
	public User findByAuthenticatedTokenAndUserLogin(String token, String login);

}
