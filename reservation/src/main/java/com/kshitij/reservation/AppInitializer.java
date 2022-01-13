package com.kshitij.reservation;

import com.kshitij.reservation.dto.request.TicketCreateRequest;
import com.kshitij.reservation.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class AppInitializer implements ApplicationRunner {

    @Autowired
    private TicketService ticketService;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        TicketCreateRequest request = TicketCreateRequest.builder()
                .numberOfTickets(10)
                .price(new BigDecimal(100.25))
                .build();
        ticketService.generate(request);
    }
}
