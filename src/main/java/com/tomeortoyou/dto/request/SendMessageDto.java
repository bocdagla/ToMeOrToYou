package com.tomeortoyou.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class SendMessageDto {
    //TODO Include the user authentication in the request
    private String senderId;
    private String conversationId;
    private String content;
}
