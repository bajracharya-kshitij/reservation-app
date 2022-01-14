package com.kshitij.reservation;

import com.kshitij.reservation.dto.request.TicketCreateRequest;
import com.kshitij.reservation.dto.request.UserRequest;
import com.kshitij.reservation.service.TicketService;
import com.kshitij.reservation.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class AppInitializer implements ApplicationRunner {

    @Autowired
    private UserService userService;

    @Autowired
    private TicketService ticketService;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        UserRequest adminRequest = UserRequest.builder()
                .name("Admin User")
                .email("admin@email.com")
                .password("admin123")
                .role("Admin")
                .build();
        userService.create(adminRequest);

        UserRequest userRequest = UserRequest.builder()
                .name("Test User")
                .email("test@email.com")
                .password("test123")
                .build();
        userService.create(userRequest);

        TicketCreateRequest ticketRequest = TicketCreateRequest.builder()
                .numberOfTickets(10)
                .price(new BigDecimal(100.25))
                .build();
        ticketService.generate(ticketRequest);
    }
}
