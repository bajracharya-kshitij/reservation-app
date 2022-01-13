package com.kshitij.reservation.service;

import com.kshitij.reservation.dto.request.PaymentRequest;

import java.util.List;

public interface PaymentService {

    void pay(PaymentRequest request);
}
