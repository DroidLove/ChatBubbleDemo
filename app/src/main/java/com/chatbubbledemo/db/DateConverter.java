package com.chatbubbledemo.db;

import android.arch.persistence.room.TypeConverter;

import java.util.Date;

/**
 * Storing the custom data type object in the db using the TypeConverter
 * according to the data type of database
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
