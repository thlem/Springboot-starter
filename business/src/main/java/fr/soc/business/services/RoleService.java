package fr.soc.business.services;

import java.util.List;

import fr.soc.data.model.Role;

/**
 * Interface of the service RoleServiceImpl
 * 
 * @author thomas.lemercier.pro@gmail.com
 *
 */
public interface RoleService {

	/**
	 * Retrieve all Role from the DB
	 * 
	 * @return List of Role
	 */
	public List<Role> getRoles();

	/**
	 * Retrieve a Role by its unique ID
	 * 
	 * @param roleId
	 * @return The Role
	 */
	public Role getRoleById(Long roleId);

	/**
	 * Retrieve a Role by its label
	 * 
	 * @param roleLabel
	 *            The label
	 * @return The Role
	 */
	public Role getRoleByLabel(String roleLabel);

	/**
	 * 
	 * @param role
	 * @return
	 */
	public Role createRole(Role role);

	/**
	 * 
	 * @param roleList
	 * @return
	 */
	public List<Role> createRoles(List<Role> roleList);

	/**
	 * 
	 * @param role
	 * @return
	 */
	public Role updateRole(Role role);

	/**
	 * 
	 * @param roleList
	 */
	public void deleteRoles(List<Role> roleList);

	/**
	 * 
	 * @param role
	 */
	public void deleteRole(Role role);

}
