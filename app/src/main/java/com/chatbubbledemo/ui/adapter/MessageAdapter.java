package com.chatbubbledemo.ui.adapter;

import android.content.Context;
import android.content.res.ColorStateList;
import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.chatbubbledemo.R;
import com.chatbubbledemo.databinding.MessageDateTimeBinding;
import com.chatbubbledemo.databinding.MessageViewLeftBinding;
import com.chatbubbledemo.databinding.MessageViewRightBinding;
import com.chatbubbledemo.ui.helper.Message;

import java.util.ArrayList;

/**
 * Custom list adapter for the chat timeline
 * Created by Yuvraj.
 */
public class MessageAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private LayoutInflater mLayoutInflater;
    private ArrayList<Message> mMessagesList;
    Context activity;
    public final int VIEW_DATE_TIME = 1, VIEW_RIGHT_USER = 2, VIEW_LEFT_USER = 3;

    private Message.OnIconClickListener mOnIconClickListener;
    private Message.OnBubbleClickListener mOnBubbleClickListener;
    private Message.OnIconLongClickListener mOnIconLongClickListener;
    private Message.OnBubbleLongClickListener mOnBubbleLongClickListener;

    private int mUsernameTextColor;
    private int mSendTimeTextColor;
    private int mDateSeparatorColor;
    private int mRightMessageTextColor = Color.WHITE;
    private int mLeftMessageTextColor = Color.BLACK;
    private int mLeftBubbleColor;
    private int mRightBubbleColor;
    private int mStatusColor;
    /**
     * Default message item margin top
     */
    private int mMessageTopMargin = 5;
    /**
     * Default message item margin bottom
     */
    private int mMessageBottomMargin = 5;

    public MessageAdapter(Context context, ArrayList<Message> objects) {
        mLayoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mMessagesList = objects;
        activity = context;
        mLeftBubbleColor = ContextCompat.getColor(context, R.color.textWhite);
        mRightBubbleColor = ContextCompat.getColor(context, R.color.chat_adoption_bubble);

        mUsernameTextColor = ContextCompat.getColor(activity, R.color.blueGray500);
        mSendTimeTextColor = ContextCompat.getColor(activity, R.color.blueGray500);
        mDateSeparatorColor = ContextCompat.getColor(activity, R.color.blueGray500);
        mStatusColor = ContextCompat.getColor(activity, R.color.blueGray500);
    }

    public void refresh(ArrayList<Message> data) {
        mMessagesList.clear();
        mMessagesList.addAll(data);
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

            final Message message = mMessagesList.get(position);
//            String date = DateUtils.convertDateFormat(message.getMessageDate(), DateUtils.yyyy_MM_dd_HH_mm_ss_ampm, DateUtils.hh_mm_a);

//            String mDate = DateUtils.getChatFormattedDate(activity, message.getMessageDate());
//            ((MessageDateTimeHolder) holder).binding.messageDateTime.setText(mDate);

        } else if (holder instanceof MessageRightViewHolder) {

//            final Message message = mMessagesList.get(position);
//            ((MessageRightViewHolder) holder).binding.cardview.setCardBackgroundColor(ContextCompat.getColor(activity, message.getmRightBubbleColor()));
//            ((MessageRightViewHolder) holder).binding.messageText.setText(message.getMessageText());
//            String date = DateUtils.getLocalTimeString(message.getMessageDate(), DateUtils.yyyy_MM_dd_HH_mm_ss_ampm, DateUtils.hh_mm_a);
//            ((MessageRightViewHolder) holder).binding.messageTime.setText(date);
//            ((MessageRightViewHolder) holder).binding.messageTime.setTextColor(ContextCompat.getColor(activity, message.getmRightDateColor()));

        } else if (holder instanceof MessageLeftViewHolder) {

//            final Message message = mMessagesList.get(position);
//            ((MessageLeftViewHolder) holder).binding.cardview.setCardBackgroundColor(ContextCompat.getColor(activity, message.getmLeftBubbleColor()));
//            ((MessageLeftViewHolder) holder).binding.messageText.setText(message.getMessageText());
//            String date = DateUtils.getLocalTimeString(message.getMessageDate(), DateUtils.yyyy_MM_dd_HH_mm_ss_ampm, DateUtils.hh_mm_a);
//            ((MessageLeftViewHolder) holder).binding.messageTime.setText(date);
//            ((MessageLeftViewHolder) holder).binding.messageTime.setTextColor(ContextCompat.getColor(activity, message.getmLeftDateColor()));

        }
    }

    @Override
    public int getItemViewType(int position) {
//        Message item = mMessagesList.get(position);

//        if (item.getMessageType().equalsIgnoreCase(DATE_TIME)) {
//            return VIEW_DATE_TIME;
//        } else {
//            return item.isRightMessage() ? VIEW_RIGHT_USER : VIEW_LEFT_USER;
            return (position % 2 == 0) ? VIEW_RIGHT_USER : VIEW_LEFT_USER;
//        }
    }

    @Override
    public int getItemCount() {
        return 10;
    }

    /**
     * Add color to drawable
     *
     * @param color    setting color
     * @param drawable which be set color
     */
    public void setColorDrawable(int color, Drawable drawable) {
        if (drawable == null) {
            return;
        }
        ColorStateList colorStateList = ColorStateList.valueOf(color);
        Drawable wrappedDrawable = DrawableCompat.wrap(drawable);
        DrawableCompat.setTintList(wrappedDrawable, colorStateList);
    }

    /**
     * Set left bubble background color
     *
     * @param color left bubble color
     */
    public void setLeftBubbleColor(int color) {
        mLeftBubbleColor = color;
        notifyDataSetChanged();
    }

    /**
     * Set right bubble background color
     *
     * @param color right bubble color
     */
    public void setRightBubbleColor(int color) {
        mRightBubbleColor = color;
        notifyDataSetChanged();
    }

    public void setOnIconClickListener(Message.OnIconClickListener onIconClickListener) {
        mOnIconClickListener = onIconClickListener;
    }

    public void setOnBubbleClickListener(Message.OnBubbleClickListener onBubbleClickListener) {
        mOnBubbleClickListener = onBubbleClickListener;
    }

    public void setOnIconLongClickListener(Message.OnIconLongClickListener onIconLongClickListener) {
        mOnIconLongClickListener = onIconLongClickListener;
    }

    public void setOnBubbleLongClickListener(Message.OnBubbleLongClickListener onBubbleLongClickListener) {
        mOnBubbleLongClickListener = onBubbleLongClickListener;
    }

    public void setUsernameTextColor(int usernameTextColor) {
        mUsernameTextColor = usernameTextColor;
        notifyDataSetChanged();
    }

    public void setSendTimeTextColor(int sendTimeTextColor) {
        mSendTimeTextColor = sendTimeTextColor;
        notifyDataSetChanged();
    }

    public void setDateSeparatorColor(int dateSeparatorColor) {
        mDateSeparatorColor = dateSeparatorColor;
        notifyDataSetChanged();
    }

    public void setRightMessageTextColor(int rightMessageTextColor) {
        mRightMessageTextColor = rightMessageTextColor;
        notifyDataSetChanged();
    }

    public void setLeftMessageTextColor(int leftMessageTextColor) {
        mLeftMessageTextColor = leftMessageTextColor;
        notifyDataSetChanged();
    }

    public void setMessageTopMargin(int messageTopMargin) {
        mMessageTopMargin = messageTopMargin;
    }

    public void setMessageBottomMargin(int messageBottomMargin) {
        mMessageBottomMargin = messageBottomMargin;
    }

    public void setStatusColor(int statusTextColor) {
        mStatusColor = statusTextColor;
        notifyDataSetChanged();
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
