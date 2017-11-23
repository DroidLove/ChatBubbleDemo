package com.chatbubbledemo.utils;

import android.content.Context;
import android.text.format.DateFormat;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

public class DateUtils {

    public static final String yyyy = "yyyy";
    public static final String MM = "MM";
    public static final String dd_mmm_yyyy_hh_mm_ss = "dd-mmm-yyyy hh:mm:ss";
    public static final String dd_mmm_yyyy_hh_mm_a = "dd-mmm-yyyy hh:mm a";
    public static final String yyyy_MM_dd = "yyyy-MM-dd";
    public static final String dd_MM_yyyy_HH_mm_ss = "dd-MM-yyyy hh:mm:ss";
    public static final String yyyy_MM_dd_HH_mm_ss_ampm = "yyyy-MM-dd hh:mm:ss a";
    public static final String dd_MM_yyyy_HH_mm_ss_ampm = "dd-MM-yyyy hh:mm:ss a";
    public static final String dd_MM_yyyy_comma_HH_mm_ampm = "dd-MM-yyyy, hh:mm a";
    public static final String dd_slash_MM_yyyy_HH_mm_ss_ampm = "dd/MM/yyyy hh:mm:ss a";
    public static final String dd_slash_MMM_yyyy_HH_mm_ss_ampm = "dd MMM yyyy hh:mm:ss a";
    public static final String dd_slash_MMM_yyyy_HH_mm_ampm = "dd MMM yyyy hh:mm a";
    public static final String dd_slash_MMM_yyyy_HH_mm = "dd MMM yyyy hh:mm";
    public static final String dd_MMMM_yyyy = "dd MMMM yyyy";
    public static final String dd_MMM_yyyy = "dd MMM yyyy";
    public static final String dd_MMM_yy = "dd MMM yy";
    public static final String dd_MM_yyyy = "dd-MM-yyyy";
    public static final String yyyy_MM_dd_hh_mm_ss = "yyyy-MM-dd hh:mm:ss";
    public static final String hh_mm_ss = "hh:mm:ss";
    public static final String hh_a = "hh a";
    public static final String HH = "HH";
    public static final String hh = "hh";
    public static final String HH_mm_ss = "HH:mm:ss";
    public static final String mm = "mm";
    public static final String HH_mm_ss_a = "HH:mm:ss a";
    public static final String dd__MM__yyyy = "dd/MM/yyyy";
    public static final String EEE_d_MMM = "EEE, d MMM";

    public static final String EEE_MMM_d_h_mm_a = "EEE, MMM d 'at' h:mm a";
    public static final String EEE_MMM_d = "EEE, MMM d";

    public static final String yyyy_MM_dd_HH_mm_ss = "yyyy-MM-dd HH:mm:ss";
    public static final String MMM = "MMM";
    public static final String dd = "dd";
    public static final String eee_h_mm_a = "EEE h:mm a";
    public static final String hh_mm_a = "hh:mm a";
    public static final String hh_mm_aaa = "hh:mm aaa";
    public static final String HH_mm_a = "HH:mm a";
    public static final String hh_mm = "hh:mm";
    public static final String HH_mm = "HH:mm";
    public static final String hh_mm_ss_a = "hh:mm:ss a";

    public static final String dd_MM_yyyy_comma_HH_mm_ampm_noseparator = "dd MMM yyyy, hh:mm a";
    public static final String dd_MM_yyyy_comma_HH_mm_ampm_noseparator_month = "dd MMM, yyyy hh:mm a";

    public static String getLocalTimeString(String strUTCTime, String oldFormat, String newFormat) {
        String strLocalTime = null;
        SimpleDateFormat UTCTimeFormatter = new SimpleDateFormat(oldFormat, Locale.getDefault());
        SimpleDateFormat LocalTimeFormatter = new SimpleDateFormat(newFormat, Locale.getDefault());

        try {
            UTCTimeFormatter.setTimeZone(TimeZone.getTimeZone("UTC"));
            Date date = UTCTimeFormatter.parse(strUTCTime);
            LocalTimeFormatter.setTimeZone(TimeZone.getDefault());
            strLocalTime = LocalTimeFormatter.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return strLocalTime;
    }

    public static String getUTCTimeString(String strLocalTime, String oldFormat, String newFormat) {
        String s = "";
        SimpleDateFormat LocalTimeFormatter = new SimpleDateFormat(oldFormat, Locale.getDefault());
        SimpleDateFormat UTCTimeFormatter = new SimpleDateFormat(newFormat, Locale.getDefault());

        try {
            LocalTimeFormatter.setTimeZone(TimeZone.getDefault());
            // millisecond = LocalTimeFormatter.parse(strLocalTime).getTime();
            Date d = LocalTimeFormatter.parse(strLocalTime);
            UTCTimeFormatter.setTimeZone(TimeZone.getTimeZone("UTC"));
            s = UTCTimeFormatter.format(d);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return s;
    }

    public static String changeDateFormat(String selectedDate, String oldFormat, String newFormat) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(oldFormat, Locale.getDefault());
        simpleDateFormat = convertDateToUTC(simpleDateFormat);
        SimpleDateFormat sdfTarget = new SimpleDateFormat(newFormat, Locale.getDefault());
        String StrDateNew = "";
        AppUtils.logMe("Testing", "inside");

        try {
            Date datenw = simpleDateFormat.parse(selectedDate);
            StrDateNew = sdfTarget.format(datenw);
        } catch (ParseException e) {
            StrDateNew = "";
        }
        return StrDateNew;
    }

    public static String getDate(String requestedFormat, long time) {
        Calendar cal = Calendar.getInstance(Locale.US);
        cal.setTimeInMillis(time);
        return DateFormat.format(requestedFormat, cal).toString();
    }

    public static String getDateFormat(String requestedFormat, String date) {
        int week;
        String weekDay;
        Date formattedDate = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(requestedFormat, Locale.getDefault());
        try {
            formattedDate = simpleDateFormat.parse(date);
            Calendar cal = Calendar.getInstance(Locale.US);
            cal.setTime(formattedDate);
            week = cal.get(Calendar.DAY_OF_WEEK);

            if (week == 1) {
                weekDay = "7";
            } else {
                weekDay = String.valueOf(week - 1);
            }
        } catch (ParseException e) {
            weekDay = "100";
        }
        return weekDay;
    }

    public static String getCurrentDate(Context activity) {
        String formattedDate = "";
        try {
            Calendar c = Calendar.getInstance();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(yyyy_MM_dd_HH_mm_ss_ampm, Locale.getDefault());
            formattedDate = simpleDateFormat.format(c.getTime());

            String[] split = formattedDate.split(" ");
            String amPm = split[2], time = "";
            if (amPm.equalsIgnoreCase("p.m.")) {
                formattedDate = formattedDate.replace("p.m.", "PM");
            } else if ((amPm.equalsIgnoreCase("a.m."))) {
                formattedDate = formattedDate.replace("a.m.", "AM");
            }

        } catch (Exception e) {
            formattedDate = "";
        }
        return formattedDate;
    }

    public static String getChatCurrentDate() {
        String formattedDate = "";
        try {
            Calendar c = Calendar.getInstance();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(yyyy_MM_dd_HH_mm_ss, Locale.getDefault());
            formattedDate = simpleDateFormat.format(c.getTime());
        } catch (Exception e) {
            formattedDate = "";
        }
        return formattedDate;
    }

    public static String getTomorrowDate() {
        String formattedDate = "";
        try {
            Calendar c = Calendar.getInstance();
            c.add(Calendar.DAY_OF_YEAR, 1);
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(yyyy_MM_dd_HH_mm_ss_ampm, Locale.getDefault());
            formattedDate = simpleDateFormat.format(c.getTime());
        } catch (Exception e) {
            formattedDate = "";
        }
        return formattedDate;
    }

    public static String getCurrentTime(String pattern) {
        String formattedDate = "";
        try {
            Calendar c = Calendar.getInstance();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern, Locale.getDefault());
            formattedDate = simpleDateFormat.format(c.getTime());
        } catch (Exception e) {
            formattedDate = "";
        }
        return formattedDate;
    }

    public static int getCurrentMinutes() {
        int formattedTime;
        try {
            Calendar c = Calendar.getInstance();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(mm, Locale.getDefault());
            formattedTime = Integer.parseInt(simpleDateFormat.format(c.getTime()));
        } catch (Exception e) {
            formattedTime = 0;
        }
        return formattedTime;
    }

    public static int getCurrentHour() {
        int formattedTime;
        try {
            String time = getCurrentTime(HH_mm_ss);
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(HH_mm_ss, Locale.getDefault());
            SimpleDateFormat simpleDateForm = new SimpleDateFormat(HH, Locale.getDefault());
            Date date = simpleDateFormat.parse(time);
            formattedTime = Integer.parseInt(simpleDateForm.format(date));
        } catch (Exception e) {
            formattedTime = 0;
        }
        return formattedTime;
    }

    public static int getSelectedHour(String time) {
        int formattedTime;
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(HH_mm_ss, Locale.getDefault());
            SimpleDateFormat simpleDateFormatNew = new SimpleDateFormat(HH, Locale.getDefault());
            Date date = simpleDateFormat.parse(time);
            formattedTime = Integer.parseInt(simpleDateFormatNew.format(date));
        } catch (Exception e) {
            formattedTime = 0;
        }
        return formattedTime;
    }

    public static int getSelectedMinute(String time) {
        int formattedTime;
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(HH_mm_ss, Locale.getDefault());
            SimpleDateFormat simpleDateFormatNew = new SimpleDateFormat(mm, Locale.getDefault());
            Date date = simpleDateFormat.parse(time);
            formattedTime = Integer.parseInt(simpleDateFormatNew.format(date));
        } catch (Exception e) {
            formattedTime = 0;
        }
        return formattedTime;
    }

    public static Date parseDateFormat(String selectedDate, String format) {
        Date formattedDate = new Date();
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format, Locale.getDefault());
            simpleDateFormat = convertDateToUTC(simpleDateFormat);
            formattedDate = simpleDateFormat.parse(selectedDate);
        } catch (ParseException e) {
            formattedDate = new Date();
        }
        return formattedDate;
    }

    public String convertTimeToString(Date selectedDate) {
        return selectedDate.getHours() + ":" + selectedDate.getMinutes() + ":" + selectedDate.getSeconds();
    }

    public String convertDateeeToString(Date selectedDate) {
        return selectedDate.getYear() + "-" + selectedDate.getMonth() + "-" + selectedDate.getDate();
    }

    public static SimpleDateFormat convertDateToUTC(SimpleDateFormat simpleDateFormat) {
//        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
        return simpleDateFormat;
    }

    public static String getTimeFromVideoDuration(long duration) {
        return String.format(Locale.US, "%02d:%02d", TimeUnit.MILLISECONDS.toMinutes(duration),
                TimeUnit.MILLISECONDS.toSeconds(duration) - TimeUnit.MINUTES.toSeconds(
                        TimeUnit.MILLISECONDS.toMinutes(duration)));
    }

    public static String convertDateFormat(String strLocalTime, String oldFormat, String newFormat) {
        String s = "";
        SimpleDateFormat LocalTimeFormatter = new SimpleDateFormat(oldFormat, Locale.getDefault());
        SimpleDateFormat TimeFormatter = new SimpleDateFormat(newFormat, Locale.getDefault());

        try {
            LocalTimeFormatter.setTimeZone(TimeZone.getDefault());
            // millisecond = LocalTimeFormatter.parse(strLocalTime).getTime();
            Date d = LocalTimeFormatter.parse(strLocalTime);
            TimeFormatter.setTimeZone(TimeZone.getDefault());
            s = TimeFormatter.format(d);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return s;
    }

    public static boolean getTimeDifference(Date start, Date end) {


        long diff = start.getTime() - end.getTime();
        int diffHours = (int) (diff / (60 * 60 * 1000));
        //if time is less than 1 hour it will send false else true
        if (diffHours == 0) {
            return false;
        } else {
            return true;
        }
    }

    public static boolean isSameDay(Date start, Date end) {
        Calendar cal1 = Calendar.getInstance();
        Calendar cal2 = Calendar.getInstance();
        cal1.setTime(start);
        cal2.setTime(end);
        return cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR) &&
                cal1.get(Calendar.DAY_OF_YEAR) == cal2.get(Calendar.DAY_OF_YEAR);
    }

    public static String getFormatedDate(Context context, String data, String oldFormat, String newFormat) {
        long milliseconds = 0;
        SimpleDateFormat f = new SimpleDateFormat(oldFormat, Locale.getDefault());

        try {
            Date d = new SimpleDateFormat(oldFormat, Locale.getDefault()).parse(data);
            milliseconds = d.getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        /*String date = android.text.format.DateUtils.getRelativeDateTimeString(context, milliseconds,
                android.text.format.DateUtils.SECOND_IN_MILLIS, android.text.format.DateUtils.FORMAT_ABBREV_RELATIVE, 0).toString();*/

//        String date = DateUtils.convertDateFormat(data, DateUtils.yyyy_MM_dd_HH_mm_ss_ampm, DateUtils.dd_MM_yyyy_comma_HH_mm_ampm);

        String date = DateUtils.convertDateFormat(data, oldFormat, newFormat);

        String trydate = android.text.format.DateUtils.getRelativeDateTimeString(context, milliseconds,
                android.text.format.DateUtils.DAY_IN_MILLIS, android.text.format.DateUtils.WEEK_IN_MILLIS,
                android.text.format.DateUtils.FORMAT_SHOW_YEAR).toString();
        AppUtils.logMe("date", trydate);

        if (trydate.contains("Today") || trydate.contains("Yesterday") || trydate.contains("Tomorrow")) {
            return trydate;
        } else {
            return date;
        }
    }

    public static String getChatFormattedDate(Context context, String data) {
        long milliseconds = 0;
        SimpleDateFormat f = new SimpleDateFormat(yyyy_MM_dd_HH_mm_ss_ampm, Locale.getDefault());
        f.setTimeZone(TimeZone.getDefault());
        try {
            Date d = f.parse(data);
            milliseconds = d.getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }

//        String date = android.text.format.DateUtils.getRelativeDateTimeString(context, milliseconds,
//                android.text.format.DateUtils.SECOND_IN_MILLIS, android.text.format.DateUtils.FORMAT_ABBREV_RELATIVE, 0).toString();
        String date = DateUtils.convertDateFormat(data, DateUtils.yyyy_MM_dd_HH_mm_ss_ampm, DateUtils.EEE_d_MMM);
        String trydate = android.text.format.DateUtils.getRelativeDateTimeString(context, milliseconds,
                android.text.format.DateUtils.DAY_IN_MILLIS, android.text.format.DateUtils.WEEK_IN_MILLIS,
                android.text.format.DateUtils.FORMAT_SHOW_YEAR).toString();
        AppUtils.logMe("date", trydate);

        if (trydate.contains("Today") || trydate.contains("Yesterday") || trydate.contains("Tomorrow")) {
            return trydate.split(",")[0];
        } else {
            return date;
        }
    }

    public static boolean checkAfterTimings(String time, String endtime) {

        try {
            Date date1 = new SimpleDateFormat(HH_mm, Locale.getDefault()).parse(time);
            Date date2 = new SimpleDateFormat(HH_mm_ss, Locale.getDefault()).parse(endtime);

            if (date1.after(date2)) {
                return true;
            } else {

                return false;
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean checkLastHourTimings(String time, String endtime) {
        String pattern = "HH:mm";
        SimpleDateFormat sdf = new SimpleDateFormat(pattern, Locale.getDefault());
        try {
            Date date1 = sdf.parse(time);
            Date date2 = sdf.parse(endtime);
            long difference = date1.getTime() - date2.getTime();

            int hours = (int) (difference / (1000 * 60 * 60));
            int Mins = (int) (difference / (1000 * 60)) % 60;
            long Secs = (int) (difference / 1000) % 60;

            if (hours == 0 && Mins <= 30) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean checkTimeDifference(String time, String endtime) {
        SimpleDateFormat sdf = new SimpleDateFormat(dd_slash_MMM_yyyy_HH_mm_ampm, Locale.getDefault());
        SimpleDateFormat sdf1 = new SimpleDateFormat(yyyy_MM_dd_HH_mm_ss_ampm, Locale.getDefault());
        try {
            Date date1 = sdf.parse(time);
            Date date2 = sdf1.parse(endtime);
            long difference = date1.getTime() - date2.getTime();
//            int days = (int) (difference / (1000 * 60 * 60 * 24));
//            int hours = (int) ((difference - (1000 * 60 * 60 * 24) * days) / (1000 * 60 * 60)) * -1;
//            int min = (int) (difference - (1000 * 60 * 60 * 24) * days - (1000 * 60 * 60 * hours)) / (1000 * 60) * -1;

            int hours = (int) (difference / (1000 * 60 * 60));
            int Mins = (int) (difference / (1000 * 60)) % 60;
            long Secs = (int) (difference / 1000) % 60;

            if (hours == 0 && Mins >= 30) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean checkBeforeTimings(String time, String endtime) {
        try {
            Date date1 = new SimpleDateFormat(HH_mm, Locale.getDefault()).parse(time);
            Date date2 = new SimpleDateFormat(HH_mm_ss, Locale.getDefault()).parse(endtime);

            if (date1.before(date2)) {
                return true;
            } else {

                return false;
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean checkIsValidDateTime(String time, String endtime) {
        try {
            Date date1 = new SimpleDateFormat(dd_slash_MMM_yyyy_HH_mm_ampm, Locale.getDefault()).parse(time);
            Date date2 = new SimpleDateFormat(yyyy_MM_dd_HH_mm_ss_ampm, Locale.getDefault()).parse(endtime);

            return !date1.before(date2);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean checkEqualDateTime(String time, String endtime) {
        try {
            Date date1 = new SimpleDateFormat(HH_mm_ss, Locale.getDefault()).parse(time);
            Date date2 = new SimpleDateFormat(HH_mm, Locale.getDefault()).parse(endtime);

            return !date1.equals(date2);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean isTomorrowDate(String time, String endtime) {
        try {
            Date date1 = new SimpleDateFormat(dd_slash_MMM_yyyy_HH_mm_ampm, Locale.getDefault()).parse(time);
            Date date2 = new SimpleDateFormat(yyyy_MM_dd_HH_mm_ss_ampm, Locale.getDefault()).parse(endtime);

            return date1.getDate() > date2.getDate();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean isTomorrowNewDate(String time, String endtime) {
        try {
            Date date1 = new SimpleDateFormat(dd_MMM_yyyy, Locale.getDefault()).parse(time);
            Date date2 = new SimpleDateFormat(yyyy_MM_dd_HH_mm_ss_ampm, Locale.getDefault()).parse(endtime);

            return date1.getDate() > date2.getDate();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return false;
    }


    public static boolean checkDayDifference(String time, String endtime) {
        try {
            Date date1 = new SimpleDateFormat(dd_slash_MMM_yyyy_HH_mm_ampm, Locale.getDefault()).parse(time);
            Date date2 = new SimpleDateFormat(yyyy_MM_dd_HH_mm_ss_ampm, Locale.getDefault()).parse(endtime);

            long difference = date1.getTime() - date2.getTime();
            int days = (int) (difference / (1000 * 60 * 60 * 24));

            if (days == 1) {
                return true;
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static String getWeekDay() {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("EEE", Locale.getDefault());
            Date d = new Date();
            return sdf.format(d);
        } catch (Exception e) {
            return "";
        }
    }

//    public static int getMinOfHour(int time) {
//        if (time > 0 && time <= 15) {
//            return 15 - time > 7 ? 15 : 0;
//        } else if (time > 15 && time <= 30) {
//            return 30 - time > 22 ? 30 : 15;
//        } else if (time > 30 && time <= 45) {
//            return 45 - time > 37 ? 45 : 30;
//        } else if (time > 45 && time <= 60) {
//            return 30 - time > 52 ? 60 : 45;
//        } /*else if (time > 60) {
//            int leftTime = time - 60;
//            return getMinOfHour(leftTime);
//        }*/ else {
//            return time;
//        }
//    }

    public static int getMinOfHour(int time) {
        if (time > 0 && time <= 15) {
            return 15;
        } else if (time > 15 && time <= 30) {
            return 30;
        } else if (time > 30 && time <= 45) {
            return 45;
        } else if (time > 45 && time <= 60) {
            return 60;
        } /*else if (time > 60) {
            int leftTime = time - 60;
            return getMinOfHour(leftTime);
        }*/ else {
            return time;
        }
    }

    public static int getMinOfHourOfMinute(int time) {
        if (time > 0 && time <= 15) {
            return 15;
        } else if (time > 15 && time <= 30) {
            return 30;
        } else if (time > 30 && time <= 45) {
            return 45;
        } else if (time > 45 && time <= 60) {
            return 0;
        } /*else if (time > 60) {
            int leftTime = time - 60;
            return getMinOfHour(leftTime);
        }*/ else {
            return 0;
        }
    }

    public static int getHourIn12Format(int time) {
        return time <= 12 ? 12 : time - 12;
    }

    public static String getWeekOffDay(String week) {
        String weekDay = "";
        int dayOfWeek = Integer.parseInt(week);
        if (dayOfWeek == 1) {
            weekDay = "Monday";
        } else if (dayOfWeek == 2) {
            weekDay = "Tuesday";
        } else if (dayOfWeek == 3) {
            weekDay = "Wednesday";
        } else if (dayOfWeek == 4) {
            weekDay = "Thursday";
        } else if (dayOfWeek == 5) {
            weekDay = "Friday";
        } else if (dayOfWeek == 6) {
            weekDay = "Saturday";
        } else if (dayOfWeek == 7) {
            weekDay = "Sunday";
        }
        return weekDay;
    }
}
