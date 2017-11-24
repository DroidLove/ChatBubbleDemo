package com.chatbubbledemo.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;
import android.content.Context;

import com.chatbubbledemo.db.dao.ChatDao;
import com.chatbubbledemo.db.entity.ChatEntity;

/**
 * Contains the config for the actual database
 */
@Database(entities = {ChatEntity.class}, version = 1, exportSchema = false)
@TypeConverters(DateConverter.class)
public abstract class AppDatabase extends RoomDatabase {

    public static final String DATABASE_NAME = "chatbubble-db";

    private static AppDatabase INSTANCE;

    public static AppDatabase getDatabase(Context context) {
        if (INSTANCE == null) {
            INSTANCE =
                    Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, DATABASE_NAME)
                            .build();
        }
        return INSTANCE;
    }

    public abstract ChatDao chatDao();

}
