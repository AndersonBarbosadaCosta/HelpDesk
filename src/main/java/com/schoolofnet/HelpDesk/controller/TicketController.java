package com.schoolofnet.HelpDesk.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.schoolofnet.HelpDesk.model.Interaction;
import com.schoolofnet.HelpDesk.model.Role;
import com.schoolofnet.HelpDesk.model.Ticket;
import com.schoolofnet.HelpDesk.model.User;
import com.schoolofnet.HelpDesk.services.RolesService;
import com.schoolofnet.HelpDesk.services.TicketService;
import com.schoolofnet.HelpDesk.services.UserService;

@Controller
@RequestMapping("/tickets")
public class TicketController {

	@Autowired
	private TicketService ticketService;

	@Autowired
	private UserService userService;

	@Autowired
	private RolesService roleService;

	public TicketController(TicketService ticketService, UserService userService, RolesService roleService) {

		this.ticketService = ticketService;
		this.userService = userService;
		this.roleService = roleService;
	}

	@GetMapping
	public String index(Model model) {
		model.addAttribute("list", this.ticketService.findAll());
		model.addAttribute("userLogged", this.userService.findCurrentUser());
		return "tickets/index";
	}

	@GetMapping("/new")
	public String create(Model model) {
		model.addAttribute("ticket", new Ticket());
		model = this.ticketService.findAllTechicals(model);
		return "tickets/create";
	}

	@PostMapping
	public String save(@Valid @ModelAttribute("ticket") Ticket ticket, BindingResult result, Model model) {
		if (result.hasErrors()) {
			return "create";
		}
		this.ticketService.create(ticket);
		return "redirect:/tickets";
	}

	@DeleteMapping("/{id}")
	public String delete(@PathVariable("id") Long id) {
		this.ticketService.delete(id);
		return "redirect:/tickets";
	}

	@PutMapping("/{id}")
	public String update(@PathVariable("id") Long id, @Valid @ModelAttribute("ticket") Ticket ticket, Model model,
			BindingResult result) {
		if (result.hasErrors()) {
			return "tickets/edit";
		}
		this.ticketService.update(ticket, id);
		return "redirect:/tickets";
	}

	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Long id, Model model) {
		Ticket ticket= this.ticketService.show(id);
		List<Interaction>interactions = ticket.getInteractions();
		model = this.ticketService.findAllTechicals(model);
		model.addAttribute("ticket",ticket);
		model.addAttribute("interaction_size",interactions.size());
		model.addAttribute("userLogged", this.userService.findCurrentUser());
		
		return "/tickets/edit";
	}

	@GetMapping("{id}")
	public String show(@PathVariable("id") Long id, Model model) {
		Ticket ticket = this.ticketService.show(id);
		List<Interaction> interactions = ticket.getInteractions();
		model.addAttribute("ticket", ticket);
		model.addAttribute("interaction", new Interaction());
		model.addAttribute("interactions", interactions);
		model.addAttribute("userLogged", this.userService.findCurrentUser());
		return "/tickets/show";
	}
}
