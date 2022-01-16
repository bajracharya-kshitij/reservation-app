package com.kshitij.reservation.service.impl;

import com.kshitij.reservation.dto.request.EventCreateRequest;
import com.kshitij.reservation.dto.request.EventRequest;
import com.kshitij.reservation.enums.TicketStatus;
import com.kshitij.reservation.model.Event;
import com.kshitij.reservation.repository.EventRepository;
import com.kshitij.reservation.service.EventService;
import com.kshitij.reservation.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EventServiceImpl implements EventService {

    @Autowired
    private TicketService ticketService;

    @Autowired
    private EventRepository eventRepository;

    @Override
    public void create(EventCreateRequest request) {
        Event event = Event.builder()
                        .name(request.getName())
                        .location(request.getLocation())
                        .build();
        eventRepository.save(event);
        ticketService.create(request.getTickets(), event);
    }

    @Override
    public List<Event> list() {
        return eventRepository.findAll();
    }

    @Override
    public Event findById(Long id) throws Exception {
        Optional<Event> optionalEvent = eventRepository.findById(id);
        if(!optionalEvent.isPresent()) {
            throw new Exception("Event cannot be found");
        }
        return optionalEvent.get();
    }

    @Override
    public Event update(Long id, EventRequest request) throws Exception {
        Event event = findById(id);
        event.setName(request.getName());
        event.setLocation(request.getLocation());
        return eventRepository.save(event);
    }

    @Override
    public void delete(Long id) throws Exception {
        Event event = findById(id);
        if (event.getTickets().stream().anyMatch(
                ticket -> ticket.getStatus().equals(TicketStatus.BOOKED)
                        || ticket.getStatus().equals(TicketStatus.RESERVED))) {
            throw new Exception("One or more ticket already booked/reserved. Unable to delete.");
        }
        eventRepository.delete(event);
    }
}
