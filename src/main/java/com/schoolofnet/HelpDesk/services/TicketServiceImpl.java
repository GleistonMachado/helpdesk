package com.schoolofnet.HelpDesk.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.schoolofnet.HelpDesk.models.Role;
import com.schoolofnet.HelpDesk.models.Ticket;
import com.schoolofnet.HelpDesk.models.User;
import com.schoolofnet.HelpDesk.repositories.TicketRepository;
import com.schoolofnet.HelpDesk.repositories.UserRepository;

@Service
public class TicketServiceImpl implements TicketService{
	
	// private static final Long ROLE_ID = (long) 4;
	private static final String ROLE_NAME = "ADMIN";
	
	@Autowired
	private TicketRepository ticketRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RoleService roleService;
	
	@Autowired
	private UserService userService;

	public TicketServiceImpl(TicketRepository ticketRepository, UserRepository userRepository, RoleService roleService, UserService userService) {
		super();
		this.ticketRepository = ticketRepository;
		this.userRepository = userRepository;
		this.roleService = roleService;
		this.userService = userService;
	}

	@Override
	public List<Ticket> findAll() {
		return (List<Ticket>) this.ticketRepository.findAll();
	}

	@Override
	public Ticket create(Ticket ticket) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();  // Capturando o usuario logado
		
		String userName = auth.getName();
		
		User userLogged = this.userRepository.findByEmail(userName);
		
		ticket.setUserOpen(userLogged);
		
		return this.ticketRepository.save(ticket);
	}

	@Override
	public Boolean update(Long id, Ticket ticket) {
		return null;
	}

	@Override
	public Boolean delete(Long id) {
		return null;
	}

	@Override
	public Ticket show(Long id) {
		return null;
	}

	@Override
	public Model createTemplate(Model model) {
		
		model.addAttribute("ticket", new Ticket());
		
		Role adminRole = this.roleService.findByName(ROLE_NAME);
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();  // Capturando o usuario logado
		
		String userName = auth.getName();
		
		User userLogged = this.userRepository.findByEmail(userName);
		
		model.addAttribute("techs", this.userService.findAllWhereRoleEquals(adminRole.getId(), userLogged.getId()));
		// model.addAttribute("techs", this.userService.findAllWhereRoleEquals(ROLE_ID));
		
		return model;
	}

	

}
