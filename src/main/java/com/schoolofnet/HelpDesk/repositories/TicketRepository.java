package com.schoolofnet.HelpDesk.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.schoolofnet.HelpDesk.models.Ticket;

public interface TicketRepository extends PagingAndSortingRepository<Ticket, Long> {

}
