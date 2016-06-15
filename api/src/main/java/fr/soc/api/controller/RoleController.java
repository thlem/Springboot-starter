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
 * <h1>REST Resource for managing Roles</h1>
 * 
 * <p>
 * Provide an interface to manage Roles.
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
	 * Retrieve all Roles.
	 * 
	 * @return ResponseEntity<List<Role>> The response containing a role list
	 */
	@ApiOperation(value = "getRoles", nickname = "getRoles")
	@ApiResponses(value = { 
			@ApiResponse(code = 200, message = "Success", response = List.class),
			@ApiResponse(code = 401, message = "Unauthorized"), 
			@ApiResponse(code = 403, message = "Forbidden"),
			@ApiResponse(code = 500, message = "Failure") })
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<Role>> getRoles() {

		List<Role> roleList = roleService.getRoles();

		return new ResponseEntity<>(roleList, HttpStatus.OK);

	}

	// GET ONE BY

	/**
	 * Retrieve one Role by its ID.
	 * 
	 * @param roleId
	 *            The unique ID of a Role
	 * @return ResponseEntity<Role> The response containing a Role
	 */
	@ApiOperation(value = "getRole", nickname = "getRole")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "roleId", value = "The role ID", required = false, dataType = "Long", paramType = "query") })
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

	// POST ONE

	/**
	 * Create a new Role.
	 * 
	 * @param role
	 *            The Role to create
	 * @return ResponseEntity<Role> The response containing a role
	 */
	@ApiOperation(value = "createRole", nickname = "createRole")
	@ApiResponses(value = { 
			@ApiResponse(code = 200, message = "Success", response = Role.class),
			@ApiResponse(code = 401, message = "Unauthorized"), 
			@ApiResponse(code = 403, message = "Forbidden"),
			@ApiResponse(code = 500, message = "Failure") })
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Role> createRole(@NotNull @RequestBody Role role) {

		Role createdRole = roleService.createRole(role);

		return new ResponseEntity<>(createdRole, HttpStatus.OK);

	}

	// PUT ONE

	/**
	 * Update an existing Role.
	 * 
	 * @param role
	 *            The Role to update
	 * @return ResponseEntity<Role> The response containing a role
	 */
	@ApiOperation(value = "updateRole", nickname = "updateRole")
	@ApiResponses(value = { 
			@ApiResponse(code = 200, message = "Success", response = Role.class),
			@ApiResponse(code = 401, message = "Unauthorized"), 
			@ApiResponse(code = 403, message = "Forbidden"),
			@ApiResponse(code = 404, message = "Not Found"), 
			@ApiResponse(code = 500, message = "Failure") })
	@RequestMapping(method = RequestMethod.PUT, path = "/{roleId}")
	public ResponseEntity<Role> updateRole(@NotNull @RequestBody Role role) {

		Role updatedRole = roleService.updateRole(role);

		return new ResponseEntity<>(updatedRole, HttpStatus.OK);

	}

	// DELETE ALL

	/**
	 * Delete all Roles.
	 * 
	 * @param roleList
	 *            The Role List to delete
	 * @return ResponseEntity The response
	 */
	@ApiOperation(value = "deleteRoles", nickname = "deleteRoles")
	@ApiResponses(value = { 
			@ApiResponse(code = 200, message = "Success", response = List.class),
			@ApiResponse(code = 401, message = "Unauthorized"),
			@ApiResponse(code = 403, message = "Forbidden"),
			@ApiResponse(code = 500, message = "Failure") })
	@RequestMapping(method = RequestMethod.DELETE)
	public ResponseEntity deleteRoles(@NotNull @RequestBody List<Role> roleList) {

		roleService.deleteRoles(roleList);

		return new ResponseEntity<>(HttpStatus.OK);

	}

	// DELETE ONE

	/**
	 * Delete one Role.
	 * 
	 * @param role
	 *            The Role to delete
	 * @return ResponseEntity The response
	 */
	@ApiOperation(value = "deleteRole", nickname = "deleteRole")
	@ApiResponses(value = { 
			@ApiResponse(code = 200, message = "Success", response = Role.class),
			@ApiResponse(code = 401, message = "Unauthorized"),
			@ApiResponse(code = 403, message = "Forbidden"),
			@ApiResponse(code = 404, message = "Not Found"),
			@ApiResponse(code = 500, message = "Failure") })
	@RequestMapping(method = RequestMethod.DELETE, path = "/{roleId}")
	public ResponseEntity deleteRole(@NotNull @RequestBody Role role) {

		roleService.deleteRole(role);

		return new ResponseEntity<>(HttpStatus.OK);

	}

}
