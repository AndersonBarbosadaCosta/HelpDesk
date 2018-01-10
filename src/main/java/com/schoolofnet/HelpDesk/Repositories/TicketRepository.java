package com.schoolofnet.HelpDesk.Repositories;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.schoolofnet.HelpDesk.model.Ticket;

public interface TicketRepository extends PagingAndSortingRepository<Ticket, Long> {

}
