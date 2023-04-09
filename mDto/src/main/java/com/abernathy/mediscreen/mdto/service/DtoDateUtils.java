package com.abernathy.mediscreen.mdto.service;

import com.abernathy.mediscreen.mdto.exception.DateFormatException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DtoDateUtils {

    private DtoDateUtils() {}

    public static LocalDate stringToDate(String strDate) throws DateFormatException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        try {
            return LocalDate.parse(strDate, formatter);
        }
        catch(DateTimeParseException exception) {
            throw new DateFormatException();
        }
    }

    public static String dateToString(LocalDate date) {
        return date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }


}
