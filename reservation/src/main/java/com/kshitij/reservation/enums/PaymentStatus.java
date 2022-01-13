package com.kshitij.reservation.enums;

public enum PaymentStatus {

    PENDING("Pending"),
    PAID("Paid");

    private String formattedName;

    PaymentStatus(String formattedName) {
        this.formattedName = formattedName;
    }

    public String getFormattedName() {
        return formattedName;
    }

    public static PaymentStatus getEnumByString(String code) {
        for (PaymentStatus ps : PaymentStatus.values()) {
            if (ps.formattedName.equals(code)) {
                return ps;
            }
        }
        return PENDING;
    }
}
