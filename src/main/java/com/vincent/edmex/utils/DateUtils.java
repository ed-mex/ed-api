package com.vincent.edmex.utils;

import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

@Component
public class DateUtils {

    private Locale locale = Locale.ITALY;


    public SimpleDateFormat formatData() {
        return new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss", locale);
    }

    public SimpleDateFormat formatYear() {
        return new SimpleDateFormat("dd-MMM-yyyy", locale);
    }

    public SimpleDateFormat formatHours() {
        return new SimpleDateFormat("HH:mm:ss", locale);
    }

    public boolean isToday(Date dateToCompare) {
        return formatYear().format(new Date()).equals(formatYear().format(dateToCompare));
    }

}
