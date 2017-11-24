package com.chatbubbledemo.db;

import android.os.AsyncTask;

import com.chatbubbledemo.db.entity.ChatEntity;

import java.util.List;
import java.util.Random;

/**
 * Generates dummy data and inserts them into the database
 */
public class DatabaseUtil {

    private static final String[] FIRST = new String[]{
            "Special edition", "New", "Cheap", "Quality", "Used"};
    private static final String[] SECOND = new String[]{
            "Three-headed Monkey", "Rubber Chicken", "Pint of Grog", "Monocle"};
    private static final String[] DESCRIPTION = new String[]{
            "is finally here", "is recommended by Stan S. Stanman",
            "is the best sold product on Mêlée Island", "is \uD83D\uDCAF", "is ❤️", "is fine"};
    private static final String[] COMMENTS = new String[]{
            "Comment 1", "Comment 2", "Comment 3", "Comment 4", "Comment 5", "Comment 6",
    };

    private static void generateData(List<ChatEntity> products) {
        Random rnd = new Random();
        for (int i = 0; i < FIRST.length; i++) {
            for (int j = 0; j < SECOND.length; j++) {
//                ProductEntity product = new ProductEntity();
//                product.setName(FIRST[i] + " " + SECOND[j]);
//                product.setDescription(product.getName() + " " + DESCRIPTION[j]);
//                product.setPrice(rnd.nextInt(240));
//                product.setId(FIRST.length * i + j + 1);
//                products.add(product);
            }
        }

//        for (Product product : products) {
//            int commentsNumber = rnd.nextInt(5) + 1;
//            for (int i = 0; i < commentsNumber; i++) {
//                CommentEntity comment = new CommentEntity();
//                comment.setProductId(product.getId());
//                comment.setText(COMMENTS[i] + " for " + product.getName());
//                comment.setPostedAt(new Date(System.currentTimeMillis()
//                        - TimeUnit.DAYS.toMillis(commentsNumber - i) + TimeUnit.HOURS.toMillis(i)));
//                comments.add(comment);
//            }
//        }
    }

//    public static void insertChatData(AppDatabase db, List<ChatEntity> products) {
//        db.beginTransaction();
//        try {
////            db.productDao().insertAll(products);
//            db.setTransactionSuccessful();
//        } finally {
//            db.endTransaction();
//        }
//    }

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
