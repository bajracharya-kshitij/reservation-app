package com.kshitij.reservation.repository;

import com.kshitij.reservation.model.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketRepository extends JpaRepository<Ticket, Long> {
}
