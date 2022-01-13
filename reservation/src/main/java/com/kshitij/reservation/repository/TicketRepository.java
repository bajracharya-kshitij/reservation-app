package com.kshitij.reservation.repository;

import com.kshitij.reservation.enums.TicketStatus;
import com.kshitij.reservation.model.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TicketRepository extends JpaRepository<Ticket, Long> {

    Optional<Ticket> findByTicketNumber(String ticketNumber);

    Long countByStatus(TicketStatus status);
}
