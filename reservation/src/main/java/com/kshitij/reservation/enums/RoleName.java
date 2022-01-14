package com.kshitij.reservation.enums;

public enum RoleName {

    ROLE_ADMIN("Admin"), ROLE_USER("User");

    private String formattedName;

    RoleName(String formattedName) {
        this.formattedName = formattedName;
    }

    public String getFormattedName() {
        return formattedName;
    }

    public static RoleName getEnumByString(String code) {
        for (RoleName rn : RoleName.values()) {
            if (rn.formattedName.equals(code)) {
                return rn;
            }
        }
        return ROLE_USER;
    }
}
