package com.chatbubbledemo.db;

import android.os.AsyncTask;

import com.chatbubbledemo.db.entity.ChatEntity;

import java.util.Random;

/**
 * Generates dummy data and inserts them into the database
 */
public class DatabaseUtil {

    private static final String[] RECEIVER_MESSAGES = new String[]{
            "Hello!", "How is life going on!! Great na..", "All we have to decide is what to do with the time that is given to us",
            "What we do in life echoes in eternity ❤️",
            "I know what I have to do now. I’ve got to keep breathing because tomorrow the sun will rise. Who knows what the tide could bring?",
            "Get busy livin’, or get busy dyin",
    };

    public static String generateRandomReceiverMessage() {
        Random rnd = new Random();
        int commentsNumber = rnd.nextInt(5) + 1;

        return RECEIVER_MESSAGES[commentsNumber];
    }

    public static void addChatToDataBase(AppDatabase db, ChatEntity chatEntitySender, ChatEntity chatEntityReceiver) {
        new addAsyncTask(db).execute(chatEntitySender, chatEntityReceiver);
    }

    private static class addAsyncTask extends AsyncTask<ChatEntity, Void, Void> {

        private AppDatabase db;

        addAsyncTask(AppDatabase appDatabase) {
            db = appDatabase;
        }

        @Override
        protected Void doInBackground(final ChatEntity... params) {
            insertChatData(db, params[0], params[1]);
            return null;
        }

    }

    public static void insertChatData(AppDatabase db, ChatEntity chatEntitySender, ChatEntity chatEntityReceiver) {
        db.beginTransaction();
        try {
            db.chatDao().insert(chatEntitySender);
            db.chatDao().insert(chatEntityReceiver);
            db.setTransactionSuccessful();
        } finally {
            db.endTransaction();
        }
    }
}
