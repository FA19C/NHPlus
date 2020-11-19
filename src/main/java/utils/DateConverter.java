package utils;

import java.time.LocalDate;
import java.time.LocalTime;

public class DateConverter {
    /**
     * Konvertiert String zu datum
     * @param date das Datum als String geteilt ueber -
     * @return das Datum dals LocalDate
     */
    public static LocalDate convertStringToLocalDate(String date) {
        String[] array = date.split("-");
        LocalDate result = LocalDate.of(Integer.parseInt(array[0]), Integer.parseInt(array[1]),
                Integer.parseInt(array[2]));
        return result;
    }

    /**
     * Konvertiert String zu datum
     * @param time die Zeil als String geteilt ueber :
     * @return die Zeit als LocalTime
     */
    public static LocalTime convertStringToLocalTime(String time) {
        String[] array = time.split(":");
        LocalTime result = LocalTime.of(Integer.parseInt(array[0]), Integer.parseInt(array[1]));
        return result;
    }
}
