package com.chatbubbledemo.ui.helper;

import java.util.Date;

/*
 * Created by troy379 on 04.04.17.
 */
public class Message {

    private String id;
    private String text;
    private Date createdAt;
    private User user;
    private String userType;
    private boolean isSender;

    public Message(String id, String userType, String text,boolean isSender) {
        this.id = id;
        this.text = text;
        this.userType = userType;
        this.isSender = isSender;
    }

    public Message(String id, User user, String text, Date createdAt) {
        this.id = id;
        this.text = text;
        this.user = user;
        this.createdAt = createdAt;
    }

    public String getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public User getUser() {
        return this.user;
    }

    public String getStatus() {
        return "Sent";
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public boolean isSender() {
        return isSender;
    }

    public void setSender(boolean sender) {
        isSender = sender;
    }
}
