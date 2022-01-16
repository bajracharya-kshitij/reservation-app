package com.kshitij.reservation.service.impl;

import com.kshitij.reservation.dto.request.PaymentRequest;
import com.kshitij.reservation.enums.PaymentStatus;
import com.kshitij.reservation.model.Payment;
import com.kshitij.reservation.model.Ticket;
import com.kshitij.reservation.repository.PaymentRepository;
import com.kshitij.reservation.service.PaymentService;
import com.kshitij.reservation.service.TicketService;
import com.kshitij.reservation.util.TicketUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;

@Service
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private TicketService ticketService;

    @Override
    public void pay(PaymentRequest request) {
        List<Ticket> tickets = ticketService.getTickets(request.getTicketNumbers());
        Payment payment = Payment.builder()
                .status(PaymentStatus.PAID)
                .totalPrice(TicketUtil.getTotalPrice(tickets))
                .tickets(new HashSet<>(tickets))
                .build();

        paymentRepository.save(payment);
        ticketService.book(tickets, payment);
    }
}
