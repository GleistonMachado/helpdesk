package com.schoolofnet.HelpDesk.controllers;

import java.util.List;

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

import com.schoolofnet.HelpDesk.models.Role;
import com.schoolofnet.HelpDesk.models.User;
import com.schoolofnet.HelpDesk.services.RoleService;
import com.schoolofnet.HelpDesk.services.UserService;


@Controller
@RequestMapping("/users")
public class UserController {
	
	@Autowired
	private UserService userServive;
	
	@Autowired
	private RoleService roleService;
	
	public UserController(UserService userServive, RoleService roleService) {
		this.userServive = userServive;
		this.roleService = roleService;
	}

	@GetMapping
	public String index(Model model) {
		model.addAttribute("list", this.userServive.findAll());
		return "users/index";
	}
	
	/* Mostra o template de Create */
	
	@GetMapping("/new")
	public String create(Model model) {
		model.addAttribute("user", new User());
		return "users/create";
	}
	
	@PostMapping
	public String save(@Valid @ModelAttribute("user") User user, BindingResult bindingResult, Model model) {
		
		if(bindingResult.hasErrors()) {
			return "users/create";
		}
		
		 this.userServive.create(user);
		 
		 return "redirect:/users";
	}
	
	@PutMapping("{id}")
	public String update(@PathVariable("id") Long id, @Valid @ModelAttribute("user") User user, BindingResult bindingResult, Model model) {
		
		if(bindingResult.hasErrors()) {
			return "users/edit";
		}
		
		this.userServive.update(id, user);
		
		return "redirect:/users";
		
	}
	
	/* Mostra o template de Edição */
	
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Long id, Model model) {
		User user = this.userServive.show(id);
		List<Role> roles = this.roleService.findAll();
		
		model.addAttribute("user", user);
		model.addAttribute("roles", roles);
		
		return "users/edit";
	}
	
	@DeleteMapping("{id}")
	public String delete(@PathVariable("id") Long id, Model model) {
		
		this.userServive.delete(id);
		
		return "redirect:/users";
		
	}
	
	
	
}
