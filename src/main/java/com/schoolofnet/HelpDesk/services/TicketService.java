package com.schoolofnet.HelpDesk.services;

import java.util.List;

import org.springframework.ui.Model;

import com.schoolofnet.HelpDesk.model.Ticket;

public interface TicketService {

	public Ticket create(Ticket ticket);

	public List<Ticket> findAll();

	public Boolean delete(Long id);

	public Ticket show(Long id);

	public Boolean update(Ticket ticket, Long id);
	
	public Model findAllTechicals(Model model);

}
