package com.kshitij.reservation.util;

import com.kshitij.reservation.model.Ticket;

import java.math.BigDecimal;
import java.util.List;

public class TicketUtil {

    public static BigDecimal getTotalPrice(List<Ticket> tickets) {
        BigDecimal total = BigDecimal.ZERO;
        for (int i = 0; i < tickets.size(); i++) {
            total = total.add(tickets.get(i).getPrice());
        }
        return total;
    }
}
