package com.tikbiz.model;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.data.annotation.PersistenceConstructor;

import javax.persistence.*;

import java.io.Serializable;

@Entity
@Table(name="TMS_USER")
public class TMSUser implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public TMSUser(){
		
	}
	
	public TMSUser(String errorMessage) {
		super();
		this.errorMessage = errorMessage;
	}
	
	@PersistenceConstructor
	public TMSUser(Long id, String firstName, String lastName, String userName,
			String password) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.userName = userName;
		this.password = password;
	}

	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="FIRST_NAME", nullable=false)
	private String firstName;
	
	@Column(name="LAST_NAME", nullable=false)
	private String lastName;

	@NotEmpty
	@Column(name="USER_NAME", nullable=false)
	private String userName;

	@Column(name="PASSWORD", nullable=false)
	private String password;
	
	@Transient
	private String errorMessage;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
}
