package com.kshitij.reservation.repository;

import com.kshitij.reservation.enums.TicketStatus;
import com.kshitij.reservation.model.Event;
import com.kshitij.reservation.model.Ticket;
import com.kshitij.reservation.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TicketRepository extends JpaRepository<Ticket, Long> {

    Optional<Ticket> findByTicketNumber(String ticketNumber);

    Long countByStatus(TicketStatus status);

    Long countByEventAndStatus(Event event, TicketStatus ticketStatus);

    Page<Ticket> findAllByStatus(TicketStatus status, Pageable pageable);

    List<Ticket> findAllByUser(User user);
}
