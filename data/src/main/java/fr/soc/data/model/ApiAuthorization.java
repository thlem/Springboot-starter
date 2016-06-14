package fr.soc.data.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.ConstraintMode;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Role model that is used in DB
 * 
 * @author thomas.lemercier.pro@gmail.com
 *
 */
@Entity
@Table(name = "API_AUTHORIZATION")
public class ApiAuthorization implements Serializable {

	// Unique key of a api authorization
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "API_AUTH_ID")
	private Long apiAuthorizationId;

	// The RequestMapping value
	@Column(nullable = false, name = "API_VALUE")
	private String apiValue;

	// The minimum role required to access this resource
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "ROLE_ID", foreignKey = @ForeignKey(value = ConstraintMode.NO_CONSTRAINT, name = "FK_API_AUTH_ROLE"))
	private Role requiredRole;

	/*
	 * GETTER & SETTER
	 */

	public Long getApiAuthorizationId() {
		return apiAuthorizationId;
	}

	public void setApiAuthorizationId(Long apiAuthorizationId) {
		this.apiAuthorizationId = apiAuthorizationId;
	}

	public String getApiValue() {
		return apiValue;
	}

	public void setApiValue(String apiValue) {
		this.apiValue = apiValue;
	}

	public Role getRequiredRole() {
		return requiredRole;
	}

	public void setRequiredRole(Role requiredRole) {
		this.requiredRole = requiredRole;
	}

}
