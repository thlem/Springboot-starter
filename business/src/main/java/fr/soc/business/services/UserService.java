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
	 * @param userId The unique ID of a User
	 * @return The User
	 */
	public User getUserById(Long userId);
}
