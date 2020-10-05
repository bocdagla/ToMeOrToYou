package com.tomeortoyou.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ConversationDto {
    private String id;
    private List<MessageDto> messages;
}
