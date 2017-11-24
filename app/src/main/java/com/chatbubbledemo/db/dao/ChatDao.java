package com.chatbubbledemo.db.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.chatbubbledemo.db.entity.ChatEntity;

import java.util.List;

/**
 * Have method for accessing the database
 */
@Dao
public interface ChatDao {
    @Query("SELECT * FROM chats")
    LiveData<List<ChatEntity>> loadAllChatHistory();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(ChatEntity products);
}
