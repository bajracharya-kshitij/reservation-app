package com.kshitij.reservation.model;

import com.kshitij.reservation.enums.TicketStatus;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Email;
import java.math.BigDecimal;

@Builder(toBuilder = true)
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@NoArgsConstructor(access = AccessLevel.PACKAGE)
@Setter
@Getter
@Entity(name = "ticket")
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;

    @Column(name = "ticket_number")
    private String ticketNumber;

    @Column(name = "name")
    private String name;

    @Column(name = "email")
    @Email
    private String email;

    @Column(name = "contact_number")
    private String contactNumber;

    @DecimalMin(value = "0.0", inclusive = false)
    @Column(name = "price")
    private BigDecimal price;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private TicketStatus status;

}
