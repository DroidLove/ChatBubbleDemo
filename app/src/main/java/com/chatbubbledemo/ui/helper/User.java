package com.chatbubbledemo.ui.helper;

/*
 * Created by troy379 on 04.04.17.
 */
public class User {

    private String id;
    private String name;

    public User(String id, String name, String avatar, boolean online) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

}
