package com.kshitij.reservation.enums;

public enum TicketStatus {

    AVAILABLE("Available"),
    SAVED("Saved"),
    RESERVED("Reserved"),
    BOOKED("Booked");

    private String formattedName;

    TicketStatus(String formattedName) {
        this.formattedName = formattedName;
    }

    public String getFormattedName() {
        return formattedName;
    }

    public static TicketStatus getEnumByString(String code) {
        for (TicketStatus ts : TicketStatus.values()) {
            if (ts.formattedName.equals(code)) {
                return ts;
            }
        }
        return AVAILABLE;
    }
}
