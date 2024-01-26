package com.bcg.ebdashboardbackend.utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class EbDateFormatter {
    public static DateTimeFormatter indianDateTimeFormatter = DateTimeFormatter.ofPattern("d-M-yy");

//    public static void main(String[] args) {
//        LocalDate localDate = LocalDate.parse("18-03-2021", indianDateTimeFormatter);
//        System.out.println(localDate);
//    }

}
