package com.modis.acciaback.repository;

import com.modis.acciaback.model.Ticket;
import com.modis.acciaback.model.TicketStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface TicketRepository extends JpaRepository<Ticket, Integer> {
    public List<Ticket> findAllByStatus(TicketStatus status);
}
