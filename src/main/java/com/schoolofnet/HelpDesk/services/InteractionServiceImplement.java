package com.schoolofnet.HelpDesk.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.schoolofnet.HelpDesk.Repositories.InteractionRepository;
import com.schoolofnet.HelpDesk.Repositories.TicketRepository;
import com.schoolofnet.HelpDesk.Repositories.UserRepository;
import com.schoolofnet.HelpDesk.model.Interaction;
import com.schoolofnet.HelpDesk.model.Ticket;
import com.schoolofnet.HelpDesk.model.User;

@Service
public class InteractionServiceImplement implements InteractionService {

	@Autowired
	private InteractionRepository repositorio;

	@Autowired
	private TicketRepository repositorioTicket;

	@Autowired
	private UserRepository userRepository;

	public InteractionServiceImplement(InteractionRepository repositorio, TicketRepository repositorioTicket,
			UserRepository userRepository) {

		this.repositorio = repositorio;
		this.repositorioTicket = repositorioTicket;
		this.userRepository = userRepository;
	}

	@Override
	public Interaction create(Interaction interaction, Long ticketId) {
		Ticket ticket = repositorioTicket.findOne(ticketId);
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String logado = authentication.getName();
		User username = userRepository.findByEmail(logado);

		interaction.setTicket(ticket);
		interaction.setUserInteraction(username);

		return this.repositorio.save(interaction);
	}

	@Override
	public List<Interaction> listAll() {

		return this.repositorio.findAll();
	}

	@Override
	public Boolean delete(Long id, Long ticketId) {
		Interaction interaction = repositorio.findOne(id);
		if (interaction != null) {
			this.repositorio.delete(id);
			return true;
		}
		return false;
	}

	@Override
	public Interaction findByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

}
