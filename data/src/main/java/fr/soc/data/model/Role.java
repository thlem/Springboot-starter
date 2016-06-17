package fr.soc.data.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Role model that is used in DB
 * 
 * @author thomas.lemercier.pro@gmail.com
 *
 */
@Entity
@Table(name = "ROLE")
public class Role implements Serializable {

	// Unique key of a role
	@Id
	@Enumerated(EnumType.STRING)
	@Column(name = "ROLE_NAME")
	private RoleEnum roleName;

	/*
	 * GETTER & SETTER
	 */

	public RoleEnum getRoleName() {
		return roleName;
	}

	public void setRoleName(RoleEnum roleName) {
		this.roleName = roleName;
	}

}
