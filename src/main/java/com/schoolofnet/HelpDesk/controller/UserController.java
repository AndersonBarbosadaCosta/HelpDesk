package com.schoolofnet.HelpDesk.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
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

import com.schoolofnet.HelpDesk.model.User;
import com.schoolofnet.HelpDesk.services.UserService;

@Controller
@RequestMapping("/users")
public class UserController {
	
	@Autowired
	private UserService service;
	   
	public UserController(UserService service) {

		this.service = service;
	}

	@GetMapping
	public String index(Model model){
		model.addAttribute("list", this.service.findAll());
		return "users/index";
	}
	
	@GetMapping("/new")
	public String create(Model model){
		model.addAttribute("user", new User());
		return "users/create";
	}
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Long id, Model model){
	 User user = this.service.show(id);
	 model.addAttribute("user", user);
		return "users/edit";
	}
	
	@PostMapping
	public String save(@Valid @ModelAttribute("user") User user,BindingResult result,Model model){
		if(result.hasErrors()){
			return "users/create";
		}
		
		this.service.create(user);
		return "redirect:/users";
	}
	
	@DeleteMapping("{id}")
	public String delete(@PathVariable("id")Long id){
		this.service.delete(id);
		return "redirect:/users";
	}
	
	@PutMapping("{id}")
	public String update(@PathVariable("id")Long id,@Valid @ModelAttribute("user") User user,BindingResult result,Model model){
		if(result.hasErrors()){
			return "users/edit";
		}
		this.service.update(id, user);
		return "redirect:/users";
	}
}
