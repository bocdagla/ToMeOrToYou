package com.tomeortoyou.entities;

import lombok.Data;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.IndexDirection;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Data
@Document(collection = "user")
public final class User {
    @Id
    private String id;

    //TODO Make restriction in BDD so its keyinsensitive, do not accept accents
    @Indexed(name = "username_index", direction = IndexDirection.DESCENDING, unique = true)
    private String username;

    @Getter(lazy=true)
    private final List<String> conversations = new ArrayList<>();
}
