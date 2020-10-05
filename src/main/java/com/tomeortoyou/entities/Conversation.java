package com.tomeortoyou.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;

@Document(collection = "conversation")
public final class Conversation {

    @Id
    private String id;
    private ArrayList<String> users;
    private ArrayList<Message> messages;

    public Conversation() {
        this.users = new ArrayList<>();
        this.messages = new ArrayList<>();
    }

    public String getId() {
        return id;
    }

    public ArrayList<String> getUsers() {
        return users;
    }

    public void setUsers(ArrayList<String> users) {
        this.users = users;
    }

    public void addUser(User user) {
        this.users.add(user.getId());
    }

    public ArrayList<Message> getMessages() {
        return messages;
    }

    public void setMessages(ArrayList<Message> messages) {
        this.messages = messages;
    }

    public void addMessage(Message message) {
        this.messages.add(message);
    }

    @Override
    public String toString() {
        return "Conversation{" +
                "id=" + id +
                ", users=" + users +
                '}' +
                "{{MESSAGES}}" + messages;
    }
}
