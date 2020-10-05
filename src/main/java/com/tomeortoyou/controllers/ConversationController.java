package com.tomeortoyou.controllers;


import com.tomeortoyou.dto.request.CreateConversationDto;
import com.tomeortoyou.dto.request.SendMessageDto;
import com.tomeortoyou.dto.response.ConversationDto;
import com.tomeortoyou.dto.response.ConversationListDto;
import com.tomeortoyou.services.IConversationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/conversations")
public class ConversationController {

    @Autowired
    private IConversationService conversationService;

    @GetMapping("/all")
    public ConversationListDto getConversations() {
        return conversationService.getAllConversations();
    }

    @PostMapping("/create")
    public ConversationDto createConversation(@RequestBody CreateConversationDto createConversationDto) {
        String senderId = createConversationDto.getSenderId();
        String receiverId = createConversationDto.getReceiverId();
        return conversationService.createConversation(senderId, receiverId);
    }

    @PutMapping("/message/send")
    @ResponseStatus(HttpStatus.OK)
    public ConversationDto sendMessage(@RequestBody SendMessageDto messageDto) {
        String userId = messageDto.getSenderId();
        String conversationId = messageDto.getConversationId();
        String content = messageDto.getContent();
        return conversationService.addMessage(userId, conversationId, content);
    }
}
