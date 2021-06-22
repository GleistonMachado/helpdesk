package com.schoolofnet.HelpDesk.services;

import java.util.List;

import org.springframework.ui.Model;

import com.schoolofnet.HelpDesk.models.Ticket;

public interface TicketService {

	List<Ticket> findAll();
	
	Ticket create(Ticket ticket);
	
	Model createTemplate(Model model);
	
	Boolean update(Long id, Ticket ticket);
	
	Boolean delete(Long id);

	Ticket show(Long id);
}
