package fr.soc.api.controller;

import java.util.List;

import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import fr.soc.business.services.RoleService;
import fr.soc.data.model.Role;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * <h1>REST API for roles</h1>
 * 
 * <p>
 * This api manages roles.
 * </p>
 * 
 * @author thomas.lemercier.pro@gmail.com
 *
 */
@Api(value = "roles")
@RestController
@RequestMapping("/api/roles")
public class RoleController {

	@Autowired
	private RoleService roleService;
	
	// GET ALL
	
	/**
	 * <h2>Get All Roles</2>
	 * 
	 * @return ResponseEntity<List<Role>> The response containing a role list
	 */
	@ApiOperation(value = "getRoles", nickname = "getRoles")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Success", response = List.class),
			@ApiResponse(code = 401, message = "Unauthorized"),
			@ApiResponse(code = 403, message = "Forbidden"),
			@ApiResponse(code = 500, message = "Failure")
	})
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<Role>> getRoles() {

		List<Role> roleList = roleService.getRoles();

		return new ResponseEntity<>(roleList, HttpStatus.OK);

	}
	
	// GET ONE BY
	
	/**
	 * <h2>Get One Role By ID</h2>
	 * 
	 * @param roleId The unique ID of a Role
	 * @return ResponseEntity<Role> The response containing a Role
	 */
	@ApiOperation(value = "getRole", nickname = "getRole")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "roleId", value = "The role ID", required = false, dataType = "Long", paramType = "query")
	})
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Success", response = Role.class),
			@ApiResponse(code = 400, message = "Bad Request"),
			@ApiResponse(code = 401, message = "Unauthorized"),
			@ApiResponse(code = 403, message = "Forbidden"),
			@ApiResponse(code = 404, message = "Not Found"),
			@ApiResponse(code = 500, message = "Failure") })
	@RequestMapping(method = RequestMethod.GET, path = "/{roleId}")
	public ResponseEntity<Role> getRole(@NotNull @PathVariable("roleId") Long roleId) {

		Role role = roleService.getRoleById(roleId);

		return new ResponseEntity<>(role, HttpStatus.OK);

	}
	
	// POST ALL
	
	/**
	 * <h2>Create All Roles</2>
	 * 
	 * @return ResponseEntity<List<Role>> The response containing a role list
	 */
	@ApiOperation(value = "createRoles", nickname = "createRoles")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Success", response = List.class),
			@ApiResponse(code = 401, message = "Unauthorized"),
			@ApiResponse(code = 403, message = "Forbidden"),
			@ApiResponse(code = 500, message = "Failure")
	})
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<List<Role>> createRoles(@NotNull @RequestBody List<Role> roleList) {

		List<Role> createdRroles = roleService.createRoles(roleList);

		return new ResponseEntity<>(createdRroles, HttpStatus.OK);

	}
	
	//POST ONE
	
	/**
	 * <h2>Create one Role</2>
	 * 
	 * @return ResponseEntity<List<Role>> The response containing a role
	 */
	@ApiOperation(value = "createRole", nickname = "createRole")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Success", response = Role.class),
			@ApiResponse(code = 401, message = "Unauthorized"),
			@ApiResponse(code = 403, message = "Forbidden"),
			@ApiResponse(code = 500, message = "Failure")
	})
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Role> createRole(@NotNull @RequestBody Role role) {

		Role createdRrole = roleService.createRole(role);

		return new ResponseEntity<>(createdRrole, HttpStatus.OK);

	}
	
	// PUT ONE
	
	// DELETE ALL
	
	// DELETE ONE
	
	

}