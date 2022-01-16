package com.kshitij.reservation.dto.request;

import lombok.*;

import java.util.List;

@Builder(toBuilder = true)
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@NoArgsConstructor(access = AccessLevel.PACKAGE)
@Setter
@Getter
public class EventCreateRequest {

    private String name;

    private String location;

    private TicketCreateRequest tickets;
}
