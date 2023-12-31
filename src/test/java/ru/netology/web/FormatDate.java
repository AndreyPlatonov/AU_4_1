package ru.netology.web;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class FormatDate {
    public String currentPlusDays(int days) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        LocalDate date = LocalDate.now();
        date = date.plusDays(days);
        String format = dtf.format(date);
        return format;
    }
}
