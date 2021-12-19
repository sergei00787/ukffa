package com.jbond.ukffa.service.core.utility;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class AgDateUtility {

    public static long getSecondFromStringDuration(String duration) {
        String[] arrDuration = duration.split(":");

        return Long.parseLong(arrDuration[0]) * 60 * 60
                + Long.parseLong(arrDuration[1]) * 60
                + Long.parseLong(arrDuration[2]);
    }

    public static String convertAgStrLocalTimeToAgStrGMTTime(String localTimeString) throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd-HHmm");
        Date localTime = simpleDateFormat.parse(localTimeString);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(localTime);
        calendar.add(Calendar.HOUR_OF_DAY, -7);
        return simpleDateFormat.format(calendar.getTime());
    }

}
