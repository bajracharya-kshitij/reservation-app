package com.kshitij.reservation.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Optional;

public final class DateUtil {

    public static final String DEFAULT_DATE_FORMAT = "dd/MM/yyyy";
    public static final String DASHED_FULL_DATE_FORMAT = "yyyy-MM-dd HH:mm";
    public static final String FULL_DATE_FORMAT = "dd/MM/yyyy HH:mm:ss";
    public static final String DAY_AND_MONTH_NAME_FORMAT = "EEE, d MMM yyyy";
    public static final String DESCRIPTIVE_DATE_FORMAT = "EEE, d MMM yyyy HH:mm";

    private static final int LAST_HOUR_OF_DAY = 23;
    private static final int LAST_MINUTE_OF_HOUR = 59;
    private static final int LAST_SECOND_OF_MINUTE = 59;
    private static final int LAST_MILLISECOND_OF_SECOND = 999;
    public static final int NO_OF_MILLISECONDS_IN_A_SECOND = 1000;

    private DateUtil() {
        throw new IllegalStateException("Utility class");
    }

    public static Date getNewDateInstanceFromDate(Date date) {
        Optional<Date> optionalDate = Optional.ofNullable(date);
        if (optionalDate.isPresent()) {
            return new Date(date.getTime());
        }
        return null;
    }

    public static String formatDate(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat(DEFAULT_DATE_FORMAT);
        return sdf.format(date);
    }

    public static String formatDate(Date date, String dateFormat) {
        if (Optional.ofNullable(date).isPresent()) {
            SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
            return sdf.format(date);
        }
        return null;
    }

    public static Date getStartOfDate(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }

    public static Date getEndOfDate(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, LAST_HOUR_OF_DAY);
        calendar.set(Calendar.MINUTE, LAST_MINUTE_OF_HOUR);
        calendar.set(Calendar.SECOND, LAST_SECOND_OF_MINUTE);
        calendar.set(Calendar.MILLISECOND, LAST_MILLISECOND_OF_SECOND);
        return calendar.getTime();
    }

    public static Date getDateForOffsetDaysFromDate(Date date, int offset) {
        Calendar calendarWithoutTime = Calendar.getInstance();
        calendarWithoutTime.setTime(date);
        calendarWithoutTime.add(Calendar.DAY_OF_MONTH, offset);
        return calendarWithoutTime.getTime();
    }

    public static Date getDateForOffsetHoursFromDate(Date date, int offset) {
        Calendar calendarWithoutTime = Calendar.getInstance();
        calendarWithoutTime.setTime(date);
        calendarWithoutTime.add(Calendar.HOUR_OF_DAY, offset);
        return calendarWithoutTime.getTime();
    }

    public static Date getDateFromString(String date, String dateFormat) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
        return sdf.parse(date);
    }

    public static Date getDateFromString(String date) throws ParseException {
        return getDateFromString(date, DEFAULT_DATE_FORMAT);
    }

    public static String convertDateFormat(String dateString, String sourceDateFormat, String targetDateFormat,
                                           boolean isEnd) throws ParseException {
        Date date = getDateFromString(dateString, sourceDateFormat);
        if (isEnd) {
            date = getEndOfDate(date);
        }
        return formatDate(date, targetDateFormat);
    }

    public static boolean hasExpired(Date date, int allowedDurationInMinutes) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MINUTE, allowedDurationInMinutes);
        Date expiryDate = calendar.getTime();
        boolean expired = expiryDate.before(new Date());
        return expired;
    }
}

