package com.kshitij.reservation.dto.request;

import lombok.*;

import javax.validation.constraints.Email;

@Builder(toBuilder = true)
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@NoArgsConstructor(access = AccessLevel.PACKAGE)
@Setter
@Getter
public class TicketBuyRequest {

    private long eventId;

    private String name;

    @Email(message = "Email format must be valid")
    private String email;

    private String contactNumber;

    private int numberOfTickets;

    private String status; //save or reserve

}
