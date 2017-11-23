package com.chatbubbledemo.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

/**
 * Default Time format that show hour and minute
 * Created by nakayama on 2017/02/18.
 */
public class DefaultTimeFormatter implements ITimeFormatter {
    @Override
    public String getFormattedTimeText(Calendar createdAt) {
        SimpleDateFormat sdf = new SimpleDateFormat("hh:mm a", Locale.getDefault());
        return sdf.format(createdAt.getTime());
    }
}
