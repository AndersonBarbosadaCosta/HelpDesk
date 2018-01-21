package com.schoolofnet.HelpDesk.services;

import java.util.List;

import com.schoolofnet.HelpDesk.model.Interaction;


public interface InteractionService {

	public Interaction create(Interaction name, Long ticketId);

	public List<Interaction> listAll();

	public Boolean delete(Long id, Long ticketId);
	
	public Interaction findByName(String name);
}
