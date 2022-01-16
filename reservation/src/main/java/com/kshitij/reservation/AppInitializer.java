package com.kshitij.reservation;

import com.kshitij.reservation.dto.request.EventCreateRequest;
import com.kshitij.reservation.dto.request.TicketCreateRequest;
import com.kshitij.reservation.dto.request.UserRequest;
import com.kshitij.reservation.service.EventService;
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
    private EventService eventService;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        UserRequest adminRequest = UserRequest.builder()
                .name("Admin")
                .email("admin@email.com")
                .password("admin123")
                .role("Admin")
                .build();
        userService.create(adminRequest);

        UserRequest userRequest = UserRequest.builder()
                .name("User")
                .email("user@email.com")
                .password("user123")
                .build();
        userService.create(userRequest);

        TicketCreateRequest ticketRequest = TicketCreateRequest.builder()
                .numberOfTickets(10)
                .price(new BigDecimal(100.25))
                .build();

        EventCreateRequest eventRequest = EventCreateRequest.builder()
                .name("Concert")
                .location("Durbar Marg")
                .tickets(ticketRequest)
                .build();

        eventService.create(eventRequest);
    }
}
