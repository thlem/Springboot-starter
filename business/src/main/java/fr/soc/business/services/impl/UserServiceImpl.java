package fr.soc.business.services.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.persistence.EntityNotFoundException;

import org.springframework.stereotype.Service;

import fr.soc.business.services.UserService;
import fr.soc.data.model.User;
import fr.soc.data.repository.UserRepository;

/**
 * Business service to manage User. This is the proxy between API and DATA
 * modules
 * 
 * @author thomas.lemercier.pro@gmail.com
 *
 */
@Service
public class UserServiceImpl implements UserService {

	@Resource
	private UserRepository userRepository;

	@Override
	public List<User> getUsers() {

		List<User> userList = new ArrayList<>();

		userRepository.findAll().iterator().forEachRemaining(userList::add);

		return userList;
	}

	@Override
	public User getUserById(Long userId) {

		User user = userRepository.findOne(userId);

		if (null == user) {
			throw new EntityNotFoundException("The user with the ID " + userId + " does not exist.");
		}

		return userRepository.findOne(userId);

	}

}
