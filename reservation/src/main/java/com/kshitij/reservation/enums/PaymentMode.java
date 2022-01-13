package com.kshitij.reservation.enums;

public enum PaymentMode {

    CASH("Cash"),
    CHEQUE("Cheque"),
    DEBIT_CARD("Debit Card"),
    CREDIT_CARD("Credit Card");

    private String formattedName;

    PaymentMode(String formattedName) {
        this.formattedName = formattedName;
    }

    public String getFormattedName() {
        return formattedName;
    }

    public static PaymentMode getEnumByString(String code) {
        for (PaymentMode pm : PaymentMode.values()) {
            if (pm.formattedName.equals(code)) {
                return pm;
            }
        }
        return CASH;
    }
}
