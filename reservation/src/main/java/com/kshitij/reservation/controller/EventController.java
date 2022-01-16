package com.kshitij.reservation.controller;

import com.kshitij.reservation.dto.request.EventCreateRequest;
import com.kshitij.reservation.dto.request.EventRequest;
import com.kshitij.reservation.dto.response.EventResponse;
import com.kshitij.reservation.dto.response.MessageResponse;
import com.kshitij.reservation.model.Event;
import com.kshitij.reservation.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/event")
public class EventController {

    @Autowired
    private EventService eventService;

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/")
    public MessageResponse create(@Valid @RequestBody EventCreateRequest request) {
        eventService.create(request);
        return MessageResponse.builder()
                .message(new StringBuilder("Event ").append(request.getName()).append(" created").toString())
                .build();
    }

    @GetMapping("/{id}")
    public EventResponse get(@PathVariable("id") Long id) throws Exception {
        Event event = eventService.findById(id);
        return prepareResponse(event);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public EventResponse update(@PathVariable("id") Long id,
                                @Valid @RequestBody EventRequest request) throws Exception {
        Event event = eventService.update(id, request);
        return prepareResponse(event);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public MessageResponse delete(@PathVariable("id") Long id) throws Exception {
        eventService.delete(id);
        return MessageResponse.builder()
                .message(new StringBuilder("Event with id ").append(id).append(" deleted").toString())
                .build();
    }

    private EventResponse prepareResponse(Event event) {
        return EventResponse.builder()
                .name(event.getName())
                .location(event.getLocation())
                .numberOfTickets(event.getTickets().size())
                .numberOfTicketsSold(event.getTickets().size())
                .numberOfTicketsAvailable(event.getTickets().size())
                .build();
    }
}
