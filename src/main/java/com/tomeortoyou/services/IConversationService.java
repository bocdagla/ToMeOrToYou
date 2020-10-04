package com.tomeortoyou.services;

import com.tomeortoyou.dto.response.ConversationDto;
import com.tomeortoyou.dto.response.ConversationListDto;

public interface IConversationService {
    public ConversationListDto getAllConversations();

    public ConversationDto createConversation(String senderUsername, String receiverUsername);

    public ConversationDto addMessage(String username, String conversationId, String content);
}
