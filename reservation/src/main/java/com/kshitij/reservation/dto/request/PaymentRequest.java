package com.kshitij.reservation.dto.request;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Builder(toBuilder = true)
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@NoArgsConstructor(access = AccessLevel.PACKAGE)
@Setter
@Getter
public class PaymentRequest {

    List<String> ticketNumbers = new ArrayList<String>();

    String mode;
}
