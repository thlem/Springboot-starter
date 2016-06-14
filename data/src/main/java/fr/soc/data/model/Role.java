package fr.soc.data.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ROLE_ID")
	private Long roleId;

	// Login of the User, should be unique
	@Column(nullable = false, name = "ROLE_LABEL")
	private String roleLabel;

	@Column(nullable = false, name = "ROLE_LEVEL")
	private int roleLevel;

	/*
	 * GETTER & SETTER
	 */

	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	public String getRoleLabel() {
		return roleLabel;
	}

	public void setRoleLabel(String roleLabel) {
		this.roleLabel = roleLabel;
	}

	public int getRoleLevel() {
		return roleLevel;
	}

	public void setRoleLevel(int roleLevel) {
		this.roleLevel = roleLevel;
	}

}
