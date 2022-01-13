package com.kshitij.reservation.service.impl;

import com.kshitij.reservation.dto.request.TicketCreateRequest;
import com.kshitij.reservation.dto.request.TicketRequest;
import com.kshitij.reservation.enums.TicketStatus;
import com.kshitij.reservation.model.Payment;
import com.kshitij.reservation.model.Ticket;
import com.kshitij.reservation.repository.TicketRepository;
import com.kshitij.reservation.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
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
    public Ticket findByTicketNumber(String ticketNumber) throws Exception {
        Optional<Ticket> optionalTicket = ticketRepository.findByTicketNumber(ticketNumber);
        if(!optionalTicket.isPresent()) {
            throw new Exception("Ticket cannot be found");
        }
        return optionalTicket.get();
    }

    @Override
    public Ticket update(String ticketNumber, TicketRequest request) throws Exception {
        Ticket ticket = findByTicketNumber(ticketNumber);
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
    public void delete(String ticketNumber) throws Exception {
        Ticket ticket = findByTicketNumber(ticketNumber);
        if (ticket.getStatus().equals(TicketStatus.RESERVED) || ticket.getStatus().equals(TicketStatus.BOOKED)) {
            throw new Exception(new StringBuilder("Ticket is already ").append(ticket.getStatus().getFormattedName())
                    .append(". Unable to delete.").toString());
        }
        ticketRepository.delete(ticket);
    }

    @Override
    public List<Ticket> getTickets(List<String> ticketNumbers) {
        List<Ticket> tickets = new ArrayList<>();
        ticketNumbers.forEach(tn -> {
            try {
                tickets.add(findByTicketNumber(tn));
            } catch (Exception e) {
                System.out.println("One or more tickets couldn't be resolved");
            }
        });
        return tickets;
    }

    @Override
    public void book(List<Ticket> tickets, Payment payment) {
        tickets.forEach(ticket -> {
            ticket.setStatus(TicketStatus.BOOKED);
            ticket.setPayment(payment);
            ticketRepository.save(ticket);
        });
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
