package fr.soc.data.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * User model that is used in DB
 * 
 * @author thomas.lemercier.pro@gmail.com
 *
 */
@Entity
@Table(name = "USER")
public class User implements Serializable {

	// Unique key of a user
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "USER_ID")
	private Long userId;

	// Login of the User, should be unique
	@Column(nullable = false, name = "USER_LOGIN")
	private String userLogin;

	// Password of the User, should be crypt before inserted in DB
	@Column(nullable = false, name = "USER_ENC_PASSWORD")
	private String userPassword;

	// Mail of the User, should be unique
	@Column(nullable = false, name = "USER_MAIL")
	private String userMail;

	// First Name of the User
	@Column(name = "USER_FIRST_NAME")
	private String userFirstName;

	// Last Name of the User
	@Column(name = "USER_LAST_NAME")
	private String userLastName;

	// The Role of the User
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "ROLE_ID")
	private Role userRole;

	@Column(name = "AUTH_TOKEN")
	private String authenticatedToken;

	/*
	 * GETTER & SETTER
	 */

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getUserLogin() {
		return userLogin;
	}

	public void setUserLogin(String userLogin) {
		this.userLogin = userLogin;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public String getUserMail() {
		return userMail;
	}

	public void setUserMail(String userMail) {
		this.userMail = userMail;
	}

	public String getUserFirstName() {
		return userFirstName;
	}

	public void setUserFirstName(String userFirstName) {
		this.userFirstName = userFirstName;
	}

	public String getUserLastName() {
		return userLastName;
	}

	public void setUserLastName(String userLastName) {
		this.userLastName = userLastName;
	}

	public Role getUserRole() {
		return userRole;
	}

	public void setUserRole(Role userRole) {
		this.userRole = userRole;
	}

	public String getAuthenticatedToken() {
		return authenticatedToken;
	}

	public void setAuthenticatedToken(String authenticatedToken) {
		this.authenticatedToken = authenticatedToken;
	}

}
