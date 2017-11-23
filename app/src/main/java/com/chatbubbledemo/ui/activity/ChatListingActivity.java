package com.chatbubbledemo.ui.activity;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.chatbubbledemo.R;
import com.chatbubbledemo.databinding.ActivityChatListingBinding;

public class ChatListingActivity extends AppCompatActivity {

    ActivityChatListingBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_chat_listing);
    }
}
