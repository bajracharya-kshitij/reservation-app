package com.kshitij.reservation.dto.response;

import lombok.*;

import java.math.BigDecimal;

@Builder(toBuilder = true)
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@NoArgsConstructor(access = AccessLevel.PACKAGE)
@Setter
@Getter
public class EventResponse {

    private String name;

    private String location;

    private int numberOfTickets;

    private int numberOfTicketsSold;

    private int numberOfTicketsAvailable;

}
