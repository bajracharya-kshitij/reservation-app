package com.kshitij.reservation.controller;

import com.kshitij.reservation.dto.request.LoginRequest;
import com.kshitij.reservation.dto.response.JwtResponse;
import com.kshitij.reservation.security.JwtProvider;
import com.kshitij.reservation.security.UserPrincipal;
import com.kshitij.reservation.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtProvider jwtProvider;

    @Autowired
    private UserService userService;

    private static final Logger LOG = LoggerFactory.getLogger(AuthController.class);

    @PostMapping("/login")
    public JwtResponse login(@Valid @RequestBody LoginRequest loginRequest) {
        LOG.debug("Logging in user >>>");
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = jwtProvider.generateJwtToken(authentication);
        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();

        LOG.debug("<<< Successfully logged in user");
        return new JwtResponse(jwt, userPrincipal.getName(), userPrincipal.getEmail(),
                userPrincipal.getAuthorities().stream().collect(Collectors.toList()));
    }
}
