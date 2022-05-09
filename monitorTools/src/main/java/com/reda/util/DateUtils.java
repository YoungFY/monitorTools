package com.reda.util;

import com.reda.entry.MonitorEntry;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Title:
 * @Description:
 * @Author: guowl
 * @version： 1.0
 * @Date:2022/4/20
 * @Copyright: Copyright(c)2022 RedaFlight.com All Rights Reserved
 */

public class DateUtils {
    private static ThreadLocal<DateFormat> threadLocal = new ThreadLocal<DateFormat>() {
        @Override
        protected DateFormat initialValue() {
            return new SimpleDateFormat("yyyy-MM-dd");
        }

    };

    private static ThreadLocal<DateFormat> threadLocal2 = new ThreadLocal<DateFormat>() {
        @Override
        protected DateFormat initialValue() {
            return new SimpleDateFormat("yyyy/mm/dd mm:dd:ss");
        }
    };

    private static ThreadLocal<DateFormat> threadLocal3 = new ThreadLocal<DateFormat>() {
        @Override
        protected DateFormat initialValue() {
            return new SimpleDateFormat("yyyymmdd mmddss");
        }
    };

    public static Date parse(String dateStr) throws ParseException {
        Object o = new MonitorEntry();
        return threadLocal.get().parse(dateStr);

    }

    public static Date parse2(String dateStr) throws ParseException {
        Object o = new MonitorEntry();
        return threadLocal2.get().parse(dateStr);

    }

    public static Date parse3(String dateStr) throws ParseException {
        Object o = new MonitorEntry();
        return threadLocal3.get().parse(dateStr);

    }

    public static String format(Date date) {
        return threadLocal.get().format(date);
    }
}
