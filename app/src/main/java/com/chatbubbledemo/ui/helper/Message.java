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

    public Message(String id, User user, String text) {
        this(id, user, text, new Date());
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
}
