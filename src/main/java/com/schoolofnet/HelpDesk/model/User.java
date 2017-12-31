package com.schoolofnet.HelpDesk.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name = "users")
public class User {

	public User() {
		// TODO Auto-generated constructor stub
	}

	public User(String name, String lastName, String email, Boolean active, String password) {

		this.name = name;
		this.lastName = lastName;
		this.email = email;
		this.active = active;
		this.password = password;
	}

	public User(Long id, String name, String lastName, String email, Boolean active, String password) {

		this.id = id;
		this.name = name;
		this.lastName = lastName;
		this.email = email;
		this.active = active;
		this.password = password;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private Long id;

	@NotEmpty(message = "can not be empty")
	@Column
	private String name;

	@NotEmpty(message = "can not be empty")
	@Column
	private String lastName;

	@Email(message = "Please provide a valid email ")
	@NotEmpty(message = "can not be empty")
	@Column
	private String email;

	@Column
	private Boolean active =true;

	@NotEmpty(message = "can not be empty")
	@Length(min = 5, message = "You need to provide a password that contains at least 5 characters ")
	@Column
	private String password;

	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "users_roles",
	joinColumns = @JoinColumn(name = "user_id") , 
	inverseJoinColumns = @JoinColumn(name = "role_id") )
	private Set<Role> roles;

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
}
