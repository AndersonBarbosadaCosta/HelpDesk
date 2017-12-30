package com.schoolofnet.HelpDesk.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name="roles")
public class Role {
	@Id@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column
	private Long id;
	
	@Column
	@NotEmpty(message="Can not be empty")
	private String role;
	
	public Role(){
		
	}
	
	public Role(Long id, String name) {

		this.id = id;
		this.role = name;
	}
	
	public Role( String name) {
		this.role = name;
	}

	public String getName() {
		return role;
	}

	public void setName(String name) {
		this.role = name;
	}
	
	

}
