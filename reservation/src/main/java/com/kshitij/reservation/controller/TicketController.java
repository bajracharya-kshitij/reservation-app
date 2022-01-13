package com.kshitij.reservation.controller;

import com.kshitij.reservation.dto.request.TicketCreateRequest;
import com.kshitij.reservation.dto.request.TicketRequest;
import com.kshitij.reservation.dto.response.MessageResponse;
import com.kshitij.reservation.dto.response.TicketResponse;
import com.kshitij.reservation.enums.TicketStatus;
import com.kshitij.reservation.model.Ticket;
import com.kshitij.reservation.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/ticket")
public class TicketController {

    @Autowired
    private TicketService ticketService;

    @PostMapping("/")
    public MessageResponse generate(@Valid @RequestBody TicketCreateRequest request) {
        ticketService.generate(request);
        return MessageResponse.builder()
                .message(new StringBuilder(Integer.toString(request.getNumberOfTickets())).append(" new tickets " +
                        "created").toString())
                .build();
    }

    @GetMapping("/{id}")
    public TicketResponse get(@PathVariable("id") Long id) throws Exception {
        Ticket ticket = ticketService.getById(id);
        return prepareResponse(ticket);
    }

    @PutMapping("/{id}")
    public TicketResponse update(@PathVariable("id") Long id, @Valid @RequestBody TicketRequest request) throws Exception {
        Ticket ticket = ticketService.update(id, request);
        return prepareResponse(ticket);
    }

    @DeleteMapping("/{id}")
    public MessageResponse delete(@PathVariable("id") Long id) throws Exception {
        ticketService.delete(id);
        return MessageResponse.builder()
                .message(new StringBuilder("Ticket with id ").append(Long.toString(id)).toString())
                .build();
    }

    private TicketResponse prepareResponse(Ticket ticket) {
        return TicketResponse.builder()
                .name(ticket.getName())
                .email(ticket.getEmail())
                .contactNumber(ticket.getContactNumber())
                .price(ticket.getPrice())
                .status(ticket.getStatus().getFormattedName())
                .build();
    }
}
