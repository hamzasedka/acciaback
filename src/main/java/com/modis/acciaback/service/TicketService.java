package com.modis.acciaback.service;

import java.time.ZonedDateTime;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.modis.acciaback.model.Ticket;
import com.modis.acciaback.model.TicketStatus;
import com.modis.acciaback.model.User;
import com.modis.acciaback.repository.TicketRepository;
import com.modis.acciaback.service.serviceImpl.UserDetailsServiceImpl;

/**
 * 
 * @author nadege.tchuindem
 *
 */
@Service
public class TicketService {
	Logger log = LogManager.getLogger(TicketService.class);

	private TicketRepository ticketRepository;
	@Autowired
	private UserDetailsServiceImpl userDetailServ;

	@Autowired
	public TicketService(TicketRepository repository) {
		this.ticketRepository = repository;
	}

	@Scheduled(fixedDelay = 60000)
	public void autocloseTickets() {
		log.info("-------------------- Je suis autocloseTickets Service -------");
		ZonedDateTime cutoffDate = ZonedDateTime.now().minusDays(1);
		List<Ticket> openTickets = ticketRepository.findAllByStatus(TicketStatus.ACTIVE);

		for (Ticket ticket : openTickets) {
			if (ticket.getStartDate().isBefore(cutoffDate)) {
				ticket.setEndDate(ZonedDateTime.now());
				ticket.setStatus(TicketStatus.RESOLVED);
				ticketRepository.save(ticket);
			}
		}
	}

	public Ticket openTicket() {
		log.info("-------------------- Je suis openTicket Service -------");
		User admin = userDetailServ.findAvailableAdmin();
		if (admin != null) {
			Ticket newTicket = new Ticket();
			newTicket.setAssignedUser(admin);
			ticketRepository.save(newTicket);
			return newTicket;
		} else {
			log.info("-------------------- Ticket can not be open: No available admin -------");
			return null;
		}
	}
}
