/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Helper;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Admin
 */
public class DateHelper {

    public static String convertDateToDateMonthYearHourMinString(Date date) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyy HH:mm");
            return sdf.format(date);
        } catch (Exception e) {
            return "";
        }
    }
}
