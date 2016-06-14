package fr.soc.business.services;

import java.util.List;

import fr.soc.data.model.User;

/**
 * Interface of the service UserServiceImpl
 * 
 * @author thomas.lemercier.pro@gmail.com
 *
 */
public interface UserService {

	/**
	 * Retrieve all User from the DB
	 * 
	 * @return List of User
	 */
	public List<User> getUsers();

	/**
	 * Retrieve a User by its unique ID
	 * 
	 * @param userId
	 *            The unique ID of a User
	 * @return The User
	 */
	public User getUserById(Long userId);

	/**
	 * Retrieve a User by its Login
	 * 
	 * @param userLogin
	 *            The login
	 * @return
	 */
	public User getUserByUserLogin(String userLogin);

	/**
	 * Retrieve a User by its Mail
	 * 
	 * @param userMail
	 *            The mail
	 * @return
	 */
	public User getUserByUserMail(String userMail);

	/**
	 * Retrieve a User by its authenticated token
	 * 
	 * @param token
	 *            The token
	 * @return The User
	 */
	public User getUserByAuthenticatedTokenAndUserLogin(String token, String login);
}
