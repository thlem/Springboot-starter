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
	public String getAllByApiValue(String apiValue) {

		String roleRestriction = "";

		List<ApiAuthorization> apiAuthList = apiAuthorizationRepository.findAllByApiValue(apiValue);

		if (null != apiAuthList && !apiAuthList.isEmpty()) {

			for (ApiAuthorization apiAuthorization : apiAuthList) {

				if (roleRestriction.isEmpty()) {
					roleRestriction = "hasAuthority('" + apiAuthorization.getRequiredRole().getRoleLabel() + "')";
				} else {
					roleRestriction += "hasAuthority('" + apiAuthorization.getRequiredRole().getRoleLabel() + "')";
				}

				if (apiAuthList.indexOf(apiAuthorization) != apiAuthList.size() - 1) {
					roleRestriction += " and ";
				}
			}

		}

		if (roleRestriction.isEmpty()) {
			roleRestriction = "hasRole('TEST')";
		}

		return roleRestriction;
	}

	@Override
	public ApiAuthorization createApiAuthorization(ApiAuthorization apiAuthorization) {

		return apiAuthorizationRepository.save(apiAuthorization);

	}

}
