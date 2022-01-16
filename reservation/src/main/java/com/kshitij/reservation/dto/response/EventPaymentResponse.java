package com.kshitij.reservation.dto.response;

import lombok.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Builder(toBuilder = true)
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@NoArgsConstructor(access = AccessLevel.PACKAGE)
@Setter
@Getter
public class EventPaymentResponse {

    private Long id;

    private String name;

    private List<String> ticketNumbers = new ArrayList<>();

    private long totalTickets;

    private BigDecimal totalPrice;

}
