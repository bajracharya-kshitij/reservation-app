package com.kshitij.reservation.model;

import com.kshitij.reservation.enums.PaymentStatus;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Builder(toBuilder = true)
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@NoArgsConstructor(access = AccessLevel.PACKAGE)
@Setter
@Getter
@Entity(name = "payment")
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private PaymentStatus status;

    @OneToMany(targetEntity = Ticket.class, mappedBy = "payment", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Ticket> tickets = new HashSet<>();

    @DecimalMin(value = "0.0", inclusive = false)
    @Column(name = "total_price")
    private BigDecimal totalPrice;

}
