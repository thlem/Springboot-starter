package fr.soc.business.services.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.persistence.EntityNotFoundException;

import org.springframework.stereotype.Service;

import fr.soc.business.services.RoleService;
import fr.soc.data.model.Role;
import fr.soc.data.repository.RoleRepository;

/**
 * Business service to manage User. This is the proxy between API and DATA
 * modules
 * 
 * @author thomas.lemercier.pro@gmail.com
 *
 */
@Service
public class RoleServiceImpl implements RoleService {

	@Resource
	private RoleRepository roleRepository;

	@Override
	public List<Role> getRoles() {

		List<Role> roleList = new ArrayList<>();

		roleRepository.findAll().iterator().forEachRemaining(roleList::add);

		return roleList;
	}

	@Override
	public Role getRoleById(Long roleId) {

		Role role = roleRepository.findOne(roleId);

		if (null == role) {
			throw new EntityNotFoundException("The role with the ID " + roleId + " does not exist.");
		}

		return role;

	}

	@Override
	public Role getRoleByLabel(String roleLabel) {

		Role role = roleRepository.findByLabel(roleLabel);

		if (null == role) {
			throw new EntityNotFoundException("The role with the label " + roleLabel + " does not exist.");
		}

		return role;

	}

	@Override
	public Role createRole(Role role) {
		
		Role createdRole = null;
		
		if (null != role) {
			createdRole = roleRepository.save(role);
		}
		
		return createdRole;

	}

	@Override
	public List<Role> createRoles(List<Role> roleList) {

		final List<Role> createdRoles = new ArrayList<>();

		if (null != roleList) {
			roleList.stream().forEach(currentRole -> {
				createdRoles.add(createRole(currentRole));
			});
		}

		return createdRoles;

	}

}
