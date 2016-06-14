package fr.soc.api.controller.administration;

import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import fr.soc.business.services.ApiAuthorizationService;
import fr.soc.data.model.ApiAuthorization;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * <h1>REST API for API Authorizations</h1>
 * 
 * <p>
 * This api manages roles access for api rest resources.
 * </p>
 * 
 * @author thomas.lemercier.pro@gmail.com
 *
 */
@Api(value = "admin/api-auth")
@RestController
@RequestMapping("/api/admin/api-auth")
public class ApiAuthorizationController {

	@Autowired
	private ApiAuthorizationService apiAuthorizationService;
	
	// GET ALL
	
	// GET ONE BY
	
	//POST ONE
	
	/**
	 * <h2>Create one Api Authorization</2>
	 * 
	 * @return ResponseEntity<ApiAuthorization> The response containing an api-auth
	 */
	@ApiOperation(value = "createApiAuthorization", nickname = "createApiAuthorization")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Success", response = ApiAuthorization.class),
			@ApiResponse(code = 401, message = "Unauthorized"),
			@ApiResponse(code = 403, message = "Forbidden"),
			@ApiResponse(code = 500, message = "Failure")
	})
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<ApiAuthorization> createApiAuthorization(@NotNull @RequestBody ApiAuthorization apiAuthorization) {

		ApiAuthorization createdApiAuthorization = apiAuthorizationService.createApiAuthorization(apiAuthorization);

		return new ResponseEntity<>(createdApiAuthorization, HttpStatus.OK);

	}
	
	// PUT ONE
	
	// DELETE ALL
	
	// DELETE ONE
	
}
