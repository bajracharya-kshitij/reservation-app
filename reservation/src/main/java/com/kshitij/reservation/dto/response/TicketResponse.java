package com.kshitij.reservation.dto.response;

import lombok.*;

import java.math.BigDecimal;

@Builder(toBuilder = true)
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@NoArgsConstructor(access = AccessLevel.PACKAGE)
@Setter
@Getter
public class TicketResponse {

    private String name;

    private String email;

    private String contactNumber;

    private BigDecimal price;

    private String status;

}
