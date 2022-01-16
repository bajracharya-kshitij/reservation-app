package com.kshitij.reservation.dto.response;

import lombok.*;

import java.math.BigDecimal;

@Builder(toBuilder = true)
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@NoArgsConstructor(access = AccessLevel.PACKAGE)
@Setter
@Getter
public class EventResponse {

    private Long id;

    private String name;

    private String location;

    private long numberOfTickets;

    private long numberOfTicketsSold;

    private long numberOfTicketsReserved;

    private long numberOfTicketsAvailable;

}
