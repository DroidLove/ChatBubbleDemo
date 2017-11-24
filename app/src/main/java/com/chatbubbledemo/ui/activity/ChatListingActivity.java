package com.chatbubbledemo.ui.activity;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.chatbubbledemo.R;
import com.chatbubbledemo.databinding.ActivityChatListingBinding;
import com.chatbubbledemo.db.AppDatabase;
import com.chatbubbledemo.db.DatabaseUtil;
import com.chatbubbledemo.db.entity.ChatEntity;
import com.chatbubbledemo.ui.adapter.MessageAdapter;
import com.chatbubbledemo.ui.helper.Constants;
import com.chatbubbledemo.utils.AppUtils;

import java.util.ArrayList;
import java.util.List;

public class ChatListingActivity extends AppCompatActivity {

    ActivityChatListingBinding binding;
    MessageAdapter adapter;
    List<ChatEntity> mChatList;
    AppDatabase appDatabase;
    private LiveData<List<ChatEntity>> mObservableChats;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_chat_listing);
        mChatList = new ArrayList<>();
        appDatabase = AppDatabase.getDatabase(this.getApplication());
        adapter = new MessageAdapter(ChatListingActivity.this, mChatList);
        binding.recyclerviewMessageView.setAdapter(adapter);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        binding.recyclerviewMessageView.setHasFixedSize(true);
        binding.recyclerviewMessageView.setLayoutManager(layoutManager);
        binding.recyclerviewMessageView.setItemViewCacheSize(20);
        binding.recyclerviewMessageView.setDrawingCacheEnabled(true);
        binding.recyclerviewMessageView.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_HIGH);

        getChatHistory();

        binding.buttonChatSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // adding the sender and the receiver data
//                mChatList.add(new Message("1", Constants.MESSAGE_SENDER, binding.editTextChat.getText().toString(), true));
if(!binding.editTextChat.getText().toString().equalsIgnoreCase("")) {
    ChatEntity chatEntity = new ChatEntity();
    chatEntity.setChatType(Constants.MESSAGE_SENDER);
    chatEntity.setChatContent(binding.editTextChat.getText().toString());
    chatEntity.setDate("");
    mChatList.add(chatEntity);

//                mChatList.add(new Message("2", Constants.MESSAGE_RECEIVER, "Hello", false));

    ChatEntity chatEntity1 = new ChatEntity();
    chatEntity1.setChatType(Constants.MESSAGE_RECEIVER);
    chatEntity1.setChatContent("Hello");
    chatEntity1.setDate("");
    mChatList.add(chatEntity1);

    DatabaseUtil.addChatToDataBase(appDatabase, chatEntity, chatEntity1);

    // clear edit text
    binding.editTextChat.setText("");
} else{
    AppUtils.toastMessage(ChatListingActivity.this,"Please enter some message");
}
//                initAdapter(mChatList);
            }
        });
    }

    private void initAdapter(List<ChatEntity> messages) {
        //if (adapter == null) {
          //  adapter = new MessageAdapter(this, messages);
            binding.recyclerviewMessageView.setAdapter(adapter);
       // } else {

            if (mChatList != null && mChatList.size() > 0) {
                // adapter.notifyItemChanged(mChatList.size());
                adapter.notifyDataSetChanged();
            } else {
                adapter.refresh(messages);
            }
       // }

        try {
            binding.recyclerviewMessageView.getLayoutManager().scrollToPosition(mChatList.size() - 1);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void getChatHistory() {

        mObservableChats = appDatabase.chatDao().loadAllChatHistory();
        mObservableChats.observe(this, new Observer<List<ChatEntity>>() {
            @Override
            public void onChanged(@Nullable List<ChatEntity> chatsHistoryList) {
                if (chatsHistoryList != null) {

                    mChatList = chatsHistoryList;
                    // initAdapter(chatsHistoryList);
                    adapter.refresh(mChatList);
                    adapter.notifyDataSetChanged();
                }
            }
        });


    }

}