/*
 * Copyright 2017, The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.chatbubbledemo.db.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.chatbubbledemo.db.entity.ChatEntity;

import java.util.List;


@Dao
public interface ChatDao {
    @Query("SELECT * FROM chats")
    LiveData<List<ChatEntity>> loadAllChatHistory();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<ChatEntity> chats);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(ChatEntity products);

    @Query("select * from chats where id = :productId")
    LiveData<ChatEntity> loadProduct(int productId);

    @Query("select * from chats where id = :productId")
    ChatEntity loadProductSync(int productId);

    @Delete
    void deleteProductTable(ChatEntity chatEntity);

    @Query("DELETE FROM chats where id = :productId")
    void deleteProduct(int productId);

}
