package com.kshitij.reservation.controller;

import com.kshitij.reservation.dto.request.TicketBuyRequest;
import com.kshitij.reservation.dto.request.TicketCreateRequest;
import com.kshitij.reservation.dto.request.TicketRequest;
import com.kshitij.reservation.dto.response.MessageResponse;
import com.kshitij.reservation.dto.response.TicketResponse;
import com.kshitij.reservation.model.Event;
import com.kshitij.reservation.model.Ticket;
import com.kshitij.reservation.service.EventService;
import com.kshitij.reservation.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/ticket")
public class TicketController {

    @Autowired
    private TicketService ticketService;

    @Autowired
    private EventService eventService;

    @PostMapping("/")
    public MessageResponse generate(@Valid @RequestBody TicketCreateRequest request) {
        ticketService.generate(request);
        return MessageResponse.builder()
                .message(new StringBuilder(Integer.toString(request.getNumberOfTickets()))
                        .append(" new tickets created").toString())
                .build();
    }

    @GetMapping("/myTickets")
    public List<TicketResponse> listMyTickets() throws Exception {
        List<TicketResponse> response = new ArrayList<>();
        List<Ticket> tickets = ticketService.listMyTickets();
        tickets.forEach(ticket -> {
            response.add(prepareResponse(ticket));
        });
        return response;
    }

    @GetMapping("/count")
    public Long count() {
        return ticketService.countAvailable();
    }

    @GetMapping("/{ticketNumber}")
    public TicketResponse get(@PathVariable("ticketNumber") String ticketNumber) throws Exception {
        Ticket ticket = ticketService.findByTicketNumber(ticketNumber);
        return prepareResponse(ticket);
    }

    @PutMapping("/{ticketNumber}")
    public TicketResponse update(@PathVariable("ticketNumber") String ticketNumber,
                                 @Valid @RequestBody TicketRequest request) throws Exception {
        Ticket ticket = ticketService.update(ticketNumber, request);
        return prepareResponse(ticket);
    }

    @DeleteMapping("/{ticketNumber}")
    public MessageResponse delete(@PathVariable("ticketNumber") String ticketNumber) throws Exception {
        ticketService.delete(ticketNumber);
        return MessageResponse.builder()
                .message(new StringBuilder("Ticket with id ").append(ticketNumber).append(" deleted").toString())
                .build();
    }

    @PostMapping("/buy")
    public MessageResponse buy(@Valid @RequestBody TicketBuyRequest request) throws Exception {
        Event event = eventService.findById(request.getEventId());
        ticketService.buy(request, event);
        return MessageResponse.builder()
                .message(new StringBuilder(request.getNumberOfTickets()).append(" tickets bought").toString())
                .build();
    }

    private TicketResponse prepareResponse(Ticket ticket) {
        return TicketResponse.builder()
                .ticketNumber(ticket.getTicketNumber())
                .name(ticket.getName())
                .email(ticket.getEmail())
                .contactNumber(ticket.getContactNumber())
                .price(ticket.getPrice())
                .status(ticket.getStatus().getFormattedName())
                .event(ticket.getEvent().getName())
                .build();
    }
}
