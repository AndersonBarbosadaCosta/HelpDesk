package com.schoolofnet.HelpDesk.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.schoolofnet.HelpDesk.model.Role;
import com.schoolofnet.HelpDesk.services.RolesService;

@Controller
@RequestMapping("/roles")
@PreAuthorize("hasAuthority('ADMIN')")
public class RoleController {

	@Autowired
	private RolesService service;

	public RoleController(RolesService service) {

		this.service = service;
	}

	@GetMapping
	public String index(Model model) {
		model.addAttribute("list", this.service.listAll());

		return "roles/index";
	}

	@GetMapping("/new")
	public String create(Model model) {
		model.addAttribute("role", new Role());
		return "roles/create";
	}

	@PostMapping
	public String save(@Valid @ModelAttribute("role") Role role, BindingResult result, Model model) {
		if (result.hasErrors()) {
			return "roles/create";
		}
		Role roleCreated = this.service.create(role);
		return "redirect:/roles";
	}

	@DeleteMapping("{id}")
	public String delete(@PathVariable("id") Long id,Model model) {
	this.service.delete(id);
	return"redirect:/roles"; 
	}

}
