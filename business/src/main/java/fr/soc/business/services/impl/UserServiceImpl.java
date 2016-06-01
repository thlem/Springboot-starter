package fr.soc.business.services.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import fr.soc.business.services.UserService;
import fr.soc.data.model.User;
import fr.soc.data.repository.UserRepository;

/**
 * Business service to manage User. This is the proxy between API and DATA modules
 * 
 * @author thomas.lemercier.pro@gmail.com
 *
 */
@Service
public class UserServiceImpl implements UserService {

	@Resource
	private UserRepository userDataService;

	@Override
	public List<User> getUsers() {

		List<User> userList = new ArrayList<>();

		userDataService.findAll().iterator().forEachRemaining(userList::add);

		return userList;
	}

	@Override
	public User getUserById(Long userId) {

		User user = userDataService.findOne(userId);

		return user;

	}

}
