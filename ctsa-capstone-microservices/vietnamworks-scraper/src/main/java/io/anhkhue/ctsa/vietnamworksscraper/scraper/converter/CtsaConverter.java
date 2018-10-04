package io.anhkhue.ctsa.vietnamworksscraper.scraper.converter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class CtsaConverter {

    public static LocalDate convertToLocalDate(String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        return LocalDate.parse(date, formatter);
    }
}
