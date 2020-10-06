package com.tomeortoyou.dto.response;

import lombok.Builder;
import lombok.Value;

@Builder
@Value
public class MessageDto {
    String content;
}
