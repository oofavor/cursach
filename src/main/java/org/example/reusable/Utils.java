package org.example.reusable;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.Calendar;
import java.util.Locale;

public class Utils {
    //gets current day of the week
    public static String getCurrentDay() {

        LocalDate date = LocalDate.now();
        Locale localeRu = new Locale("ru");

        DayOfWeek day = date.getDayOfWeek();
        String dayOfWeek = day.getDisplayName(TextStyle.FULL, localeRu);

        return dayOfWeek.substring(0, 1).toUpperCase() + dayOfWeek.substring(1);
    }
}
