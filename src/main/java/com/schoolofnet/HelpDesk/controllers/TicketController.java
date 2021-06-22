package com.schoolofnet.HelpDesk.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.schoolofnet.HelpDesk.models.Ticket;
import com.schoolofnet.HelpDesk.services.RoleService;
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
	private RoleService roleService;
	
	public TicketController(TicketService ticketService, UserService userService) {
		this.ticketService = ticketService;
		this.userService = userService;
	}
	
	@GetMapping
	public String index(Model model) {
		model.addAttribute("list", this.ticketService.findAll());
		return "tickets/index";
	}
	
	
	@GetMapping("/new")
	public String create(Model model) {
		model = this.ticketService.createTemplate(model);
		return "tickets/create";
	}
	
	
	@PostMapping
	public String save(@Valid @ModelAttribute("ticket") Ticket ticket, BindingResult bindingResult, Model model) {
		
		if(bindingResult.hasErrors()) {
			return "tickets/create";
		}
		
		this.ticketService.create(ticket);
		
		return "redirect:/tickets";
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
