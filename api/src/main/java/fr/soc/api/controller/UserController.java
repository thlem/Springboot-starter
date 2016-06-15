package fr.soc.api.controller;

import java.util.List;

import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import fr.soc.business.services.UserService;
import fr.soc.data.model.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * <h1>REST Resource for managing Users</h1>
 * 
 * <p>
 * Provide an interface to manage Users.
 * </p>
 * 
 * @author thomas.lemercier.pro@gmail.com
 *
 */
@Api(value = "users")
@RestController
@RequestMapping("/api/users")
public class UserController {

	@Autowired
	private UserService userService;

	/**
	 * Retrieve all Users.
	 * 
	 * @return ResponseEntity<List<User>> The response containing a user list
	 */
	@ApiOperation(value = "getUsers", nickname = "getUsers")
	@ApiResponses(value = { 
			@ApiResponse(code = 200, message = "Success", response = List.class),
			@ApiResponse(code = 401, message = "Unauthorized"), 
			@ApiResponse(code = 403, message = "Forbidden"),
			@ApiResponse(code = 500, message = "Failure") })
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<User>> getUsers() {

		List<User> userList = userService.getUsers();

		return new ResponseEntity<>(userList, HttpStatus.OK);

	}

	/**
	 * Retrieve one User by its ID.
	 * 
	 * @param userId
	 *            The unique ID of a User
	 * @return ResponseEntity<User> The response containing a User
	 */
	@ApiOperation(value = "getUser", nickname = "getUser")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "userId", value = "The user ID", required = false, dataType = "Long", paramType = "query") })
	@ApiResponses(value = { 
			@ApiResponse(code = 200, message = "Success", response = User.class),
			@ApiResponse(code = 400, message = "Bad Request"), 
			@ApiResponse(code = 401, message = "Unauthorized"),
			@ApiResponse(code = 403, message = "Forbidden"), 
			@ApiResponse(code = 404, message = "Not Found"),
			@ApiResponse(code = 500, message = "Failure") })
	@RequestMapping(method = RequestMethod.GET, path = "/{userId}")
	public ResponseEntity<User> getUser(@NotNull @PathVariable("userId") Long userId) {

		User user = userService.getUserById(userId);

		return new ResponseEntity<>(user, HttpStatus.OK);

	}
}
