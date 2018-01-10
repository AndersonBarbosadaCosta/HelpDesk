package com.schoolofnet.HelpDesk.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "tickets")
public class Ticket {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column
	@NotEmpty(message = "can not be empty")
	private String nome;

	@Column
	@NotEmpty(message = "can not be empty")
	private String description;

	@Column
	private Date created;

	@Column
	private Date closed;

	@Column
	private Boolean finished= false;

	@ManyToOne
	@JoinColumn(name = "user_id")
	@JsonBackReference
	private User userOpen;

	@ManyToOne
	@JoinColumn(name = "tecnico_id")
	@JsonBackReference
	private User tecnico;

	@PrePersist
	public void prePersist() {
		this.setCreated(new Date());
	}

	public Ticket() {

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public Date getClosed() {
		return closed;
	}

	public void setClosed(Date closed) {
		this.closed = closed;
	}

	public Boolean getFinished() {
		return finished;
	}

	public void setFinished(Boolean finished) {
		this.finished = finished;
	}

	public User getUserOpen() {
		return userOpen;
	}

	public void setUserOpen(User userOpen) {
		this.userOpen = userOpen;
	}

	public User getTecnico() {
		return tecnico;
	}

	public void setTecnico(User tecnico) {
		this.tecnico = tecnico;
	}

}
