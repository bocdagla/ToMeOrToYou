package com.tomeortoyou.dto.request;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class CreateConversationDto {
    private String senderId;
    private String receiverId;
}
