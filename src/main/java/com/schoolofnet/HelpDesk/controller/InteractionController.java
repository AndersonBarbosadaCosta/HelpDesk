package com.schoolofnet.HelpDesk.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.schoolofnet.HelpDesk.model.Interaction;
import com.schoolofnet.HelpDesk.services.InteractionService;

@Controller
@RequestMapping("/tickets/{ticketId}/interactions")
public class InteractionController {

	@Autowired
	private InteractionService service;

	public InteractionController(InteractionService service) {
		this.service = service;
	}

	@PostMapping
	public String save(@PathVariable("ticketId") Long ticketId,
			@Valid @ModelAttribute("interaction") Interaction interaction, BindingResult result) {
		if (result.hasErrors()) {
			return "tickets/show";
		}
		this.service.create(interaction, ticketId);

		return "redirect:/tickets/" + ticketId;
	}

	@DeleteMapping("{id}")
	public String delete(@PathVariable("ticketId")Long ticketId,@PathVariable("id")Long id, Model model) {

		  this.service.delete(id,ticketId);
		return "redirect:/tickets/" + ticketId;
	}

}
