package com.chatbubbledemo.ui.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.chatbubbledemo.R;
import com.chatbubbledemo.databinding.MessageViewReceiverBinding;
import com.chatbubbledemo.databinding.MessageViewSenderBinding;
import com.chatbubbledemo.db.entity.ChatEntity;
import com.chatbubbledemo.utils.Constants;
import com.chatbubbledemo.utils.DateUtils;

import java.util.List;

public class MessageAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private LayoutInflater mLayoutInflater;
    private List<ChatEntity> mMessagesList;
    Context activity;
    public final int VIEW_RECEIVER_MESSAGE = 1, VIEW_SENDER_MESSAGE = 2;
    int lastPosition = 0;

    public MessageAdapter(Context context, List<ChatEntity> objects) {
        mLayoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mMessagesList = objects;
        activity = context;
    }

    public void refresh(List<ChatEntity> mMessagesList) {
        this.mMessagesList = mMessagesList;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {

            case VIEW_RECEIVER_MESSAGE:
                MessageViewSenderBinding binding1 = DataBindingUtil.inflate(mLayoutInflater, R.layout.message_view_sender, parent, false);
                return new MessageRightViewHolder(binding1);

            case VIEW_SENDER_MESSAGE:
                MessageViewReceiverBinding binding2 = DataBindingUtil.inflate(mLayoutInflater, R.layout.message_view_receiver, parent, false);
                return new MessageLeftViewHolder(binding2);
        }

        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        if (holder instanceof MessageRightViewHolder) {

            final ChatEntity message = mMessagesList.get(position);
            ((MessageRightViewHolder) holder).binding.textviewMessageSender.setText(message.getChatContent());
            ((MessageRightViewHolder) holder).binding.textviewMessageTimeSender.setText(DateUtils.getFormattedTime(message.getDate()));

        } else if (holder instanceof MessageLeftViewHolder) {

            final ChatEntity message = mMessagesList.get(position);
            ((MessageLeftViewHolder) holder).binding.textviewMessageReceiver.setText(message.getChatContent());
            ((MessageLeftViewHolder) holder).binding.textviewMessageReceiverTime.setText(DateUtils.getFormattedTime(message.getDate()));

        }
    }

    @Override
    public int getItemViewType(int position) {
        ChatEntity itemMessage = mMessagesList.get(position);

        if (itemMessage.getChatType().equalsIgnoreCase(Constants.MESSAGE_SENDER))
            return VIEW_RECEIVER_MESSAGE;
        else
            return VIEW_SENDER_MESSAGE;

    }

    @Override
    public int getItemCount() {
        return mMessagesList.size();
    }

    private class MessageLeftViewHolder extends RecyclerView.ViewHolder {
        MessageViewReceiverBinding binding;

        MessageLeftViewHolder(MessageViewReceiverBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

    private class MessageRightViewHolder extends RecyclerView.ViewHolder {
        MessageViewSenderBinding binding;

        MessageRightViewHolder(MessageViewSenderBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}