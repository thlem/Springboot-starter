package fr.soc.data.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * User model that is used in DB
 * 
 * @author thomas.lemercier.pro@gmail.com
 *
 */
@Entity
public class User {

	// Unique key of a user
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long userId;
	
	// Login of the User, should be unique
	@Column(nullable = false)
	private String userLogin;

	// Password of the User, should be crypt before inserted in DB
	@Column(nullable = false)
	private String userPassword;
	
	// Mail of the User, should be unique
	@Column(nullable = false)
	private String userMail;
	
	// First Name of the User
	private String userFirstName;

	// Last Name of the User
	private String userLastName;

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

}
