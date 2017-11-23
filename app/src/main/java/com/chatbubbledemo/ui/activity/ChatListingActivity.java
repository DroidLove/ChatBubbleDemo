package com.chatbubbledemo.ui.activity;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.chatbubbledemo.R;
import com.chatbubbledemo.databinding.ActivityChatListingBinding;
import com.chatbubbledemo.ui.adapter.MessageAdapter;
import com.chatbubbledemo.ui.helper.Message;

import java.util.ArrayList;

public class ChatListingActivity extends AppCompatActivity {

    ActivityChatListingBinding binding;
    MessageAdapter adapter;
    ArrayList<Message> mChatList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_chat_listing);
        mChatList = new ArrayList<>();

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        binding.recyclerviewMessageView.setHasFixedSize(true);
        binding.recyclerviewMessageView.setLayoutManager(layoutManager);
        binding.recyclerviewMessageView.setItemViewCacheSize(20);
        binding.recyclerviewMessageView.setDrawingCacheEnabled(true);
        binding.recyclerviewMessageView.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_HIGH);

        initAdapter(mChatList);

        binding.btnChatSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

  //              mChatList = new Message()
            }
        });
    }

    private void initAdapter(ArrayList<Message> messages) {
        if (adapter == null) {
            adapter = new MessageAdapter(ChatListingActivity.this, messages);
            binding.recyclerviewMessageView.setAdapter(adapter);
        } else {
            adapter = new MessageAdapter(ChatListingActivity.this, messages);
            if (mChatList != null && mChatList.size() > 0) {
                adapter.notifyItemChanged(mChatList.size() - 1);
            } else {
                adapter.refresh(messages);
            }
        }

        try {
            binding.recyclerviewMessageView.getLayoutManager().scrollToPosition(mChatList.size() - 1);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}