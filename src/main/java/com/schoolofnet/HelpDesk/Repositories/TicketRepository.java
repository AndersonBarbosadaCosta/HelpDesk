package com.schoolofnet.HelpDesk.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.schoolofnet.HelpDesk.model.Ticket;

public interface TicketRepository extends PagingAndSortingRepository<Ticket, Long> {
	
	@Query(value="select ticket.* from tickets as ticket where ticket.created >= date(now())-interval (:day) day",nativeQuery=true)
	public List<Ticket> reportsTicketsByDays(@Param("day")Integer day);

}
