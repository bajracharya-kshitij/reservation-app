package com.kshitij.reservation.service;

import com.kshitij.reservation.dto.request.TicketCreateRequest;
import com.kshitij.reservation.dto.request.TicketRequest;
import com.kshitij.reservation.model.Payment;
import com.kshitij.reservation.model.Ticket;

import java.util.List;

public interface TicketService {

    void generate(TicketCreateRequest request);

    Ticket findByTicketNumber(String ticketNumber) throws Exception;

    Ticket update(String ticketNumber, TicketRequest request) throws Exception;

    void delete(String ticketNumber) throws Exception;

    List<Ticket> getTickets(List<String> ticketNumbers);

    void book(List<Ticket> tickets, Payment payment);
}
