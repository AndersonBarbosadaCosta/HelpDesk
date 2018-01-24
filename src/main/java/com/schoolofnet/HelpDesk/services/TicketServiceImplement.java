package com.schoolofnet.HelpDesk.services;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.schoolofnet.HelpDesk.Repositories.TicketRepository;
import com.schoolofnet.HelpDesk.Repositories.UserRepository;
import com.schoolofnet.HelpDesk.model.Role;
import com.schoolofnet.HelpDesk.model.Ticket;
import com.schoolofnet.HelpDesk.model.User;

@Service
public class TicketServiceImplement implements TicketService {

	@Autowired
	private TicketRepository ticketRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private UserService userService;

	@Autowired
	private RolesService rolesService;

	private final String ROLE_NAME = "ADMIN";

	public TicketServiceImplement(TicketRepository ticketRepository, UserService userService, RolesService rolesService,
			UserRepository userRepository) {

		this.ticketRepository = ticketRepository;
		this.userService = userService;
		this.rolesService = rolesService;
		this.userRepository = userRepository;
	}

	@Override
	public Ticket create(Ticket ticket) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String userName = auth.getName();
		User userLogged = this.userRepository.findByEmail(userName);

		ticket.setUserOpen(userLogged);
		return this.ticketRepository.save(ticket);
	}

	@Override
	public List<Ticket> findAll() {

		return (List<Ticket>) this.ticketRepository.findAll();
	}

	@Override
	public Boolean delete(Long id) {
		Ticket ticket = findById(id);
		if (ticket != null) {
			this.ticketRepository.delete(ticket.getId());
			return true;
		}

		return false;
	}

	@Override
	public Ticket show(Long id) {

		return findById(id);
	}

	@Override
	public Boolean update(Ticket ticket, Long id) {

		Ticket ticketUpdate = findById(id);
		if (ticketUpdate != null) {
			ticketUpdate.setId(ticket.getId());
			ticketUpdate.setNome(ticket.getNome());
			ticketUpdate.setDescription(ticket.getDescription());
			ticketUpdate.setFinished(ticket.getFinished());
			if(ticket.getFinished()){
				ticketUpdate.setClosed(new Date());
			}
			ticketUpdate.setTecnico(ticket.getTecnico());
					
			this.ticketRepository.save(ticketUpdate);
			return true;

		}

		return false;
	}

	@Override
	public Model findAllTechicals(Model model) {

		
		Role adminRole = this.rolesService.findByName(ROLE_NAME);
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String userName = auth.getName();

		User userLogged = this.userRepository.findByEmail(userName);

		model.addAttribute("techs", this.userService.findAllWhereRoleEquals(adminRole.getId(), userLogged.getId()));

		return model;
	}

	private Ticket findById(Long id) {
		return this.ticketRepository.findOne(id);
	}

	@Override
	public List<Ticket> reportsTicketsByDays(Integer day) {
	
		return this.ticketRepository.reportsTicketsByDays(day);
	}

}
