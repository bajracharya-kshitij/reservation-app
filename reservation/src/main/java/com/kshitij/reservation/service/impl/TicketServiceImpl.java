package com.kshitij.reservation.service.impl;

import com.kshitij.reservation.dto.request.TicketCreateRequest;
import com.kshitij.reservation.dto.request.TicketRequest;
import com.kshitij.reservation.enums.TicketStatus;
import com.kshitij.reservation.model.Ticket;
import com.kshitij.reservation.repository.TicketRepository;
import com.kshitij.reservation.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;
import java.util.stream.IntStream;

@Service
public class TicketServiceImpl implements TicketService {

    @Autowired
    private TicketRepository ticketRepository;

    @Override
    public void generate(TicketCreateRequest request) {
        IntStream.range(0, request.getNumberOfTickets()).forEach(n -> {
            Ticket ticket = Ticket.builder()
                    .ticketNumber(UUID.randomUUID().toString())
                    .price(request.getPrice())
                    .status(TicketStatus.AVAILABLE)
                    .build();
            ticketRepository.save(ticket);
        });
    }

    @Override
    public Ticket getById(Long id) throws Exception {
        Optional<Ticket> optionalTicket = ticketRepository.findById(id);
        if(!optionalTicket.isPresent()) {
            throw new Exception("Ticket cannot be found");
        }
        return optionalTicket.get();
    }

    @Override
    public Ticket update(Long id, TicketRequest request) throws Exception {
        Ticket ticket = getById(id);
        if (!isUpdatable(ticket, request)) {
            throw new Exception(new StringBuilder("Ticket is already ").append(ticket.getStatus().getFormattedName())
                    .append(". Unable to modify.").toString());
        }
        ticket.setName(request.getName());
        ticket.setEmail(request.getEmail());
        ticket.setContactNumber(request.getContactNumber());
        ticket.setStatus(TicketStatus.getEnumByString(request.getStatus()));
        return ticketRepository.save(ticket);
    }

    @Override
    public void delete(Long id) throws Exception {
        Ticket ticket = getById(id);
        if (ticket.getStatus().equals(TicketStatus.RESERVED) || ticket.getStatus().equals(TicketStatus.BOOKED)) {
            throw new Exception(new StringBuilder("Ticket is already ").append(ticket.getStatus().getFormattedName())
                    .append(". Unable to delete.").toString());
        }
        ticketRepository.delete(getById(id));
    }

    private boolean isUpdatable(Ticket ticket, TicketRequest request) {
        if (ticket.getStatus().equals(TicketStatus.BOOKED)) {
            return false;
        }
        if (ticket.getStatus().equals(TicketStatus.RESERVED)) {
            if (!ticket.getName().equals(request.getName()) ||
                    !ticket.getEmail().equals(request.getEmail()) ||
                    !ticket.getContactNumber().equals(request.getContactNumber())
            ) {
                return false;
            } else if (!ticket.getStatus().equals(TicketStatus.getEnumByString(request.getStatus()))) {
                return true;
            }
        }
        return true;
    }
}
