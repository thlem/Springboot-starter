package fr.soc.data.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import fr.soc.data.model.Role;

/**
 * Service that provide methods to retrieve Role data from DB
 * 
 * @author thomas.lemercier.pro@gmail.com
 *
 */
@Repository
public interface RoleRepository extends CrudRepository<Role, Long> {

	/**
	 * Retrieve a Role by its label
	 * 
	 * @param roleLabel
	 *            The label
	 * @return Role The Role
	 */
	public Role findByRoleLabel(String roleLabel);

}
