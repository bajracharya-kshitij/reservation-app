package com.kshitij.reservation.dto.request;

import com.kshitij.reservation.util.ConstantsUtil;
import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Builder(toBuilder = true)
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@NoArgsConstructor(access = AccessLevel.PACKAGE)
@Setter
@Getter
public class LoginRequest {

    @NotBlank(message = "Email cannot be empty")
    @Size(max = ConstantsUtil.MAX_ALLOWABLE_EMAIL_LENGTH, message = "Valid email length is upto 50 characters")
    @Email(message = "Email must be a well-formed email address")
    private String email;

    @NotBlank(message = "Password cannot be empty")
    private String password;
}
