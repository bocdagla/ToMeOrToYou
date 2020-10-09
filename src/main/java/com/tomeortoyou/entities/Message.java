package com.tomeortoyou.entities;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public final class Message {
    private String senderId;
    private String content;
}
