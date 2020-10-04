package com.tomeortoyou.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@Getter
@Setter
public class ConversationListDto {
    private List<ConversationDto> conversations;
}
