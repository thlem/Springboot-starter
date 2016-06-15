package fr.soc.business.services.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import fr.soc.business.services.ApiAuthorizationService;
import fr.soc.data.model.ApiAuthorization;
import fr.soc.data.repository.ApiAuthorizationRepository;

/**
 * Business service to manage User. This is the proxy between API and DATA
 * modules
 * 
 * @author thomas.lemercier.pro@gmail.com
 *
 */
@Service
public class ApiAuthorizationServiceImpl implements ApiAuthorizationService {

	@Resource
	private ApiAuthorizationRepository apiAuthorizationRepository;

	@Override
	public String getRestAuthorityByRestValue(String restValue) {

		// Initialize the authority definition
		StringBuilder roleRestriction = new StringBuilder();

		// Retrieve links api/role
		List<ApiAuthorization> apiAuthList = apiAuthorizationRepository.findAllByApiValue(restValue);

		if (null != apiAuthList && !apiAuthList.isEmpty()) {

			for (ApiAuthorization apiAuthorization : apiAuthList) {
				
				// Add each authorized roles for the given api value
				roleRestriction.append("hasAuthority('" + apiAuthorization.getRequiredRole().getRoleLabel() + "')");
				
				// If this is not the last entry
				if (apiAuthList.indexOf(apiAuthorization) != apiAuthList.size() - 1) {
					roleRestriction.append(" and ");
				}
			}

		}

		if (roleRestriction.length() == 0) {
			roleRestriction.append("hasRole('NO_ROLE')");
		}

		return roleRestriction.toString();
	}

	@Override
	public ApiAuthorization createApiAuthorization(ApiAuthorization apiAuthorization) {

		return apiAuthorizationRepository.save(apiAuthorization);

	}

}
