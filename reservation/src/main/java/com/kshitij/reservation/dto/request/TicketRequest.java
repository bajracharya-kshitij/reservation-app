package com.kshitij.reservation.dto.request;

import lombok.*;

import javax.persistence.Column;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Email;
import java.math.BigDecimal;

@Builder(toBuilder = true)
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@NoArgsConstructor(access = AccessLevel.PACKAGE)
@Setter
@Getter
public class TicketRequest {

    private String name;

    @Email(message = "Email format must be valid")
    private String email;

    private String contactNumber;

    @DecimalMin(value = "0.0", inclusive = false, message = "Cost cannot be negative")
    private BigDecimal price;

    private String status;
}
