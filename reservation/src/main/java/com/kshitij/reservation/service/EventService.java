package com.kshitij.reservation.service;

import com.kshitij.reservation.dto.request.EventCreateRequest;
import com.kshitij.reservation.dto.request.EventRequest;
import com.kshitij.reservation.dto.request.TicketRequest;
import com.kshitij.reservation.model.Event;

import java.util.List;

public interface EventService {

    void create(EventCreateRequest request);

    List<Event> list();

    Event findById(Long id) throws Exception;

    Event update(Long id, EventRequest request) throws Exception;

    void delete(Long id) throws Exception;
}
