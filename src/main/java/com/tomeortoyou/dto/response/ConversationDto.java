package com.tomeortoyou.dto.response;

import lombok.Builder;
import lombok.Value;

import java.util.List;

@Builder
@Value
public class ConversationDto {
    String id;
    List<MessageDto> messages;
}
