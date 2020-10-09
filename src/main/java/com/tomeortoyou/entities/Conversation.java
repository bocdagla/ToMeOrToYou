package com.tomeortoyou.entities;

import lombok.Data;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Data
@Document(collection = "conversation")
public final class Conversation {
    @Id
    private String id;

    @Getter(lazy = true)
    private final List<String> users = new ArrayList<>();

    @Getter(lazy = true)
    private final List<Message> messages = new ArrayList<>();
}
