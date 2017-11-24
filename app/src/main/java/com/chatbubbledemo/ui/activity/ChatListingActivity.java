package com.chatbubbledemo.ui.activity;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.SimpleItemAnimator;
import android.view.View;

import com.chatbubbledemo.R;
import com.chatbubbledemo.databinding.ActivityChatListingBinding;
import com.chatbubbledemo.db.AppDatabase;
import com.chatbubbledemo.db.DatabaseUtil;
import com.chatbubbledemo.db.entity.ChatEntity;
import com.chatbubbledemo.ui.adapter.MessageAdapter;
import com.chatbubbledemo.utils.AppUtils;
import com.chatbubbledemo.utils.Constants;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class ChatListingActivity extends AppCompatActivity {

    ActivityChatListingBinding binding;
    MessageAdapter adapter;
    List<ChatEntity> mChatList;
    AppDatabase appDatabase;
    private LiveData<List<ChatEntity>> mObservableChats;
    private LinearLayoutManager layoutManager;
    private boolean receiverMessageFlag = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_chat_listing);

        appDatabase = AppDatabase.getDatabase(this.getApplication());

        initRecyclerView();
        getChatHistory();
        clickListeners();
    }

    private void clickListeners() {

        binding.buttonChatSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // adding the sender and the receiver data
                if (!binding.editTextChat.getText().toString().equalsIgnoreCase("")) {
                    addSenderMessage();

                    // clear edit text
                    binding.editTextChat.setText("");
                } else {
                    AppUtils.toastMessage(ChatListingActivity.this, "Please enter some message");
                }
            }
        });
    }

    private void addSenderMessage() {
        ChatEntity chatEntitySender = new ChatEntity();
        chatEntitySender.setChatType(Constants.MESSAGE_SENDER);
        chatEntitySender.setChatContent(binding.editTextChat.getText().toString());
        chatEntitySender.setDate(new java.util.Date());
        mChatList.add(chatEntitySender);
        receiverMessageFlag = true;

        DatabaseUtil.addSenderChatToDataBase(appDatabase, chatEntitySender);
    }

    private void addReceiverMessage() {

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                ChatEntity chatEntityReceiver = new ChatEntity();
                chatEntityReceiver.setChatType(Constants.MESSAGE_RECEIVER);
                chatEntityReceiver.setChatContent(DatabaseUtil.generateRandomReceiverMessage());
                chatEntityReceiver.setDate(new java.util.Date());
                mChatList.add(chatEntityReceiver);
                receiverMessageFlag = false;

                DatabaseUtil.addReceiverChatToDataBase(appDatabase, chatEntityReceiver);

            }
        },1000);

    }

    private void initRecyclerView() {

        layoutManager = new LinearLayoutManager(this);
        SimpleItemAnimator itemAnimator = new DefaultItemAnimator();
        itemAnimator.setSupportsChangeAnimations(false);

        binding.recyclerviewMessageView.setHasFixedSize(true);
        binding.recyclerviewMessageView.setLayoutManager(layoutManager);
        binding.recyclerviewMessageView.setItemAnimator(itemAnimator);
        binding.recyclerviewMessageView.setItemViewCacheSize(20);
        binding.recyclerviewMessageView.setDrawingCacheEnabled(true);
        binding.recyclerviewMessageView.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_HIGH);

        mChatList = new ArrayList<>();
        adapter = new MessageAdapter(ChatListingActivity.this, mChatList);
        binding.recyclerviewMessageView.setAdapter(adapter);
    }

    private void getChatHistory() {

        mObservableChats = appDatabase.chatDao().loadAllChatHistory();
        mObservableChats.observe(this, new Observer<List<ChatEntity>>() {
            @Override
            public void onChanged(@Nullable List<ChatEntity> chatsHistoryList) {
                if (chatsHistoryList != null) {

                    mChatList = chatsHistoryList;
                    adapter.refresh(mChatList);
                    adapter.notifyItemInserted(chatsHistoryList.size() - 1);

                    if (chatsHistoryList.size() > 0)
                        layoutManager.scrollToPosition(chatsHistoryList.size() - 1);

                    if(receiverMessageFlag){
                        addReceiverMessage();
                    }
                }
            }
        });

    }
}