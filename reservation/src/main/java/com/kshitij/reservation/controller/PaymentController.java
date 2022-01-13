package com.kshitij.reservation.controller;

import com.kshitij.reservation.dto.request.PaymentRequest;
import com.kshitij.reservation.dto.response.MessageResponse;
import com.kshitij.reservation.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/payment")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @PostMapping("/pay")
    public MessageResponse pay(@Valid @RequestBody PaymentRequest request) throws Exception {
        paymentService.pay(request);
        return MessageResponse.builder()
                .message(new StringBuilder(Integer.toString(request.getTicketNumbers().size()))
                        .append(" tickets booked").toString())
                .build();
    }
}
