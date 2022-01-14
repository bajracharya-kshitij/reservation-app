package com.kshitij.reservation.model;

import com.kshitij.reservation.util.ConstantsUtil;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

@Builder(toBuilder = true)
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@NoArgsConstructor(access = AccessLevel.PACKAGE)
@Setter
@Getter
@Entity(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Email
    @Column(name = "email", nullable = false, unique = true)
    @Size(max = ConstantsUtil.MAX_ALLOWABLE_EMAIL_LENGTH)
    private String email;

    @Column(name = "password", nullable = false)
    @Size(min = ConstantsUtil.MIN_ALLOWABLE_PASSWORD_LENGTH, max = ConstantsUtil.MAX_ALLOWABLE_PASSWORD_LENGTH)
    private String password;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;
}
