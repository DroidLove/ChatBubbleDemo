package com.chatbubbledemo.db;

import android.arch.persistence.room.TypeConverter;

import java.util.Date;

/**
 * Created by komalgaikwad on 24/11/17.
 */

public class DateConverter {

    @TypeConverter
    public Date fromTimestamp(Long value) {
        return value == null ? null : new Date(value);
    }

    @TypeConverter
    public Long dateToTimestamp(Date date) {
        if (date == null) {
            return null;
        } else {
            return date.getTime();
        }
    }
}
