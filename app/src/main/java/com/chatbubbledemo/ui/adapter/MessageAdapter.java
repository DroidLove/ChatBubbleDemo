package com.chatbubbledemo.ui.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.chatbubbledemo.R;
import com.chatbubbledemo.databinding.MessageDateTimeBinding;
import com.chatbubbledemo.databinding.MessageViewLeftBinding;
import com.chatbubbledemo.databinding.MessageViewRightBinding;
import com.chatbubbledemo.db.entity.ChatEntity;
import com.chatbubbledemo.ui.helper.Constants;
import com.chatbubbledemo.utils.DateUtils;

import java.util.List;

/**
 * Custom list adapter for the chat timeline
 * Created by Yuvraj.
 */
public class MessageAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private LayoutInflater mLayoutInflater;
    private List<ChatEntity> mMessagesList;
    Context activity;
    private RecyclerView.LayoutManager layoutManager;
    public final int VIEW_DATE_TIME = 1, VIEW_RIGHT_USER = 2, VIEW_LEFT_USER = 3;

    public MessageAdapter(Context context, List<ChatEntity> objects) {
        mLayoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mMessagesList = objects;
        activity = context;
    }

    public void refresh(List<ChatEntity> mMessagesList) {
        this.mMessagesList = mMessagesList;
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder holder;
        switch (viewType) {
            case VIEW_DATE_TIME:
                MessageDateTimeBinding binding = DataBindingUtil.inflate(mLayoutInflater, R.layout.message_date_time, parent, false);
                return new MessageDateTimeHolder(binding);

            case VIEW_RIGHT_USER:
                MessageViewRightBinding binding1 = DataBindingUtil.inflate(mLayoutInflater, R.layout.message_view_right, parent, false);
                return new MessageRightViewHolder(binding1);

            case VIEW_LEFT_USER:
                MessageViewLeftBinding binding2 = DataBindingUtil.inflate(mLayoutInflater, R.layout.message_view_left, parent, false);
                return new MessageLeftViewHolder(binding2);
        }

        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        if (holder instanceof MessageDateTimeHolder) {

            final ChatEntity message = mMessagesList.get(position);
//            String date = DateUtils.convertDateFormat(message.getMessageDate(), DateUtils.yyyy_MM_dd_HH_mm_ss_ampm, DateUtils.hh_mm_a);

//            String mDate = DateUtils.getChatFormattedDate(activity, message.getMessageDate());
//            ((MessageDateTimeHolder) holder).binding.messageDateTime.setText(mDate);

        } else if (holder instanceof MessageRightViewHolder) {

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

//        if (item.getMessageType().equalsIgnoreCase(DATE_TIME)) {
//            return VIEW_DATE_TIME;
//        } else {

        if (itemMessage.getChatType().equalsIgnoreCase(Constants.MESSAGE_SENDER))
            return VIEW_RIGHT_USER;
        else
            return VIEW_LEFT_USER;
//            return (position % 2 == 0) ? VIEW_RIGHT_USER : VIEW_LEFT_USER;
//        }
    }

    @Override
    public int getItemCount() {
        return mMessagesList.size();
    }

    public void scrollToBottom() {
        if (layoutManager != null) {
            layoutManager.scrollToPosition(0);
        }
    }

    public void setLayoutManager(RecyclerView.LayoutManager layoutManager) {
        this.layoutManager = layoutManager;
    }

    private class MessageDateTimeHolder extends RecyclerView.ViewHolder {
        MessageDateTimeBinding binding;

        MessageDateTimeHolder(MessageDateTimeBinding binding) {
            super(binding.getRoot());
            this.binding = binding;

        }
    }

    private class MessageLeftViewHolder extends RecyclerView.ViewHolder {
        MessageViewLeftBinding binding;

        MessageLeftViewHolder(MessageViewLeftBinding binding) {
            super(binding.getRoot());
            this.binding = binding;

        }
    }

    private class MessageRightViewHolder extends RecyclerView.ViewHolder {
        MessageViewRightBinding binding;

        MessageRightViewHolder(MessageViewRightBinding binding) {
            super(binding.getRoot());
            this.binding = binding;

        }
    }
}