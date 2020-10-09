package com.tomeortoyou.mock;

import org.bson.types.ObjectId;

import java.util.Map;

public class MongoIds {
    private static final Map<String, String> mongoIds = Map.of(
            "Alex", "5f7ef16e593387395a7eb249",
            "Mike", "5f7ef19d9956885dace803ff",
            "Bob", "5f7ef1a4d16c1372cd9ad129",
            "Conversation1", "5f7ef1a9a0437323aa41a7b5"
    );

    public static String get(String key) {
        return mongoIds.get(key);
    }

    public static String get() {
        return ObjectId.get().toString();
    }
}
