package com.kshitij.reservation.dto.request;

import lombok.*;

import java.math.BigDecimal;

@Builder(toBuilder = true)
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@NoArgsConstructor(access = AccessLevel.PACKAGE)
@Setter
@Getter
public class TicketCreateRequest {

    private int numberOfTickets;

    private BigDecimal price;
}
