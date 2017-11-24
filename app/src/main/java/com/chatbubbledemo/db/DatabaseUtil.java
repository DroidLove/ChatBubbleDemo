package com.chatbubbledemo.db;

import android.os.AsyncTask;

import com.chatbubbledemo.db.entity.ChatEntity;

import java.util.Random;

/**
 * Generates dummy data and inserts them into the database.
 * Also have helper methods to do operation in a different thread
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

    public static void addSenderChatToDataBase(AppDatabase db, ChatEntity chatEntitySender) {
        new addAsyncTask(db).execute(chatEntitySender);
    }

    public static void addReceiverChatToDataBase(AppDatabase db, ChatEntity chatEntityReceiver) {
        new addAsyncTask(db).execute(chatEntityReceiver);
    }

    /**
     * The operations accessing the db should be performed in a different thread other than main UI thread
     */
    private static class addAsyncTask extends AsyncTask<ChatEntity, Void, Void> {

        private AppDatabase db;

        addAsyncTask(AppDatabase appDatabase) {
            db = appDatabase;
        }

        @Override
        protected Void doInBackground(final ChatEntity... params) {
            insertChatData(db, params[0]);
            return null;
        }

    }

    public static void insertChatData(AppDatabase db, ChatEntity chatEntity) {
        db.beginTransaction();
        try {
            db.chatDao().insert(chatEntity);
            db.setTransactionSuccessful();
        } finally {
            db.endTransaction();
        }
    }
}
