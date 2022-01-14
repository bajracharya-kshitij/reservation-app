package com.kshitij.reservation.dto.request;

import lombok.*;

@Builder(toBuilder = true)
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@NoArgsConstructor(access = AccessLevel.PACKAGE)
@Setter
@Getter
public class UserRequest {

    private String name;

    private String email;

    private String password;

    private String role;
}
