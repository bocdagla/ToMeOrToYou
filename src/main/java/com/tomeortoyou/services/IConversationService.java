package com.tomeortoyou.services;

import com.tomeortoyou.dto.request.CreateConversationDto;
import com.tomeortoyou.dto.request.SendMessageDto;
import com.tomeortoyou.dto.response.ConversationDto;
import com.tomeortoyou.dto.response.ConversationListDto;

public interface IConversationService {
    ConversationListDto getAllConversations();
    ConversationDto createConversation(CreateConversationDto createConversationDto);
    ConversationDto addMessage(SendMessageDto messageDto);
}
