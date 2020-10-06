package com.tomeortoyou.controllers;


import com.tomeortoyou.dto.request.CreateConversationDto;
import com.tomeortoyou.dto.request.SendMessageDto;
import com.tomeortoyou.dto.response.ConversationDto;
import com.tomeortoyou.dto.response.ConversationListDto;
import com.tomeortoyou.services.IConversationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.net.http.HttpResponse;

@RestController
@RequestMapping("/conversations")
public class ConversationController {

    @Autowired
    private IConversationService conversationService;

    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    public ConversationListDto getConversations() {
        return conversationService.getAllConversations();
    }

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public ConversationDto createConversation(@RequestBody CreateConversationDto createConversationDto) {
        return conversationService.createConversation(createConversationDto);
    }

    @PutMapping("/message/send")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ConversationDto sendMessage(@RequestBody SendMessageDto messageDto) {
        return conversationService.addMessage(messageDto);
    }
}
