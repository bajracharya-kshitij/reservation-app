package com.kshitij.reservation.util;

public class ParserUtil {

    public static String parseText(String text) {
        if (text == null) {
            return null;
        }
        text = text.trim();
        if (text.equals("") || text.equals("null")) {
            return null;
        }
        return text;
    }
}
