package com.tomeortoyou.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.IndexDirection;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;

@Document(collection = "user")
public final class User {
    @Id
    private String id;

    //TODO Make restriction in BDD so its keyinsensitive, do not accept accents
    @Indexed(name = "username_index", direction = IndexDirection.DESCENDING, unique = true)
    private String username;

    private ArrayList<String> conversations;

    public User() {
    }

    public User(String username) {
        this.username = username;
        this.conversations = new ArrayList<>();
    }

    public String getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public ArrayList<String> getConversations() {
        return conversations;
    }

    public void addConversation(Conversation conversation) {
        conversations.add(conversation.getId());
    }

    @Override
    public String toString() {
        return String.format(
                "User[id=%s, username=%s']",
                id, username);
    }
}
