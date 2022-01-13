package com.kshitij.reservation.service;

import com.kshitij.reservation.dto.request.TicketCreateRequest;
import com.kshitij.reservation.dto.request.TicketRequest;
import com.kshitij.reservation.model.Ticket;

public interface TicketService {

    void generate(TicketCreateRequest request);

    Ticket getById(Long id) throws Exception;

    Ticket update(Long id, TicketRequest request) throws Exception;

    void delete(Long id) throws Exception;
}
