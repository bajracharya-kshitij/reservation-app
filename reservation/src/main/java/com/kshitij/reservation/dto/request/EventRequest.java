package com.kshitij.reservation.dto.request;

import lombok.*;

@Builder(toBuilder = true)
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@NoArgsConstructor(access = AccessLevel.PACKAGE)
@Setter
@Getter
public class EventRequest {

    private String name;

    private String location;
}