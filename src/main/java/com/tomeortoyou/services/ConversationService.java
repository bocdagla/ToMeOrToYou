package com.tomeortoyou.services;

import com.tomeortoyou.dto.request.CreateConversationDto;
import com.tomeortoyou.dto.request.SendMessageDto;
import com.tomeortoyou.dto.response.ConversationDto;
import com.tomeortoyou.dto.response.ConversationListDto;
import com.tomeortoyou.entities.Conversation;
import com.tomeortoyou.entities.Message;
import com.tomeortoyou.entities.User;
import com.tomeortoyou.repositories.ConversationRepository;
import com.tomeortoyou.repositories.UserRepository;
import org.springframework.core.convert.ConversionService;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

public class ConversationService implements IConversationService {

    private final ConversationRepository conversationRepository;
    private final UserRepository userRepository;
    private final ConversionService conversionService;

    public ConversationService(ConversationRepository conversationRepository, UserRepository userRepository, ConversionService conversionService) {
        this.conversationRepository = conversationRepository;
        this.userRepository = userRepository;
        this.conversionService = conversionService;
    }

    public ConversationListDto getAllConversations() {
        List<Conversation> conversationList = conversationRepository.findAll();
        List<ConversationDto> conversationDtoList = conversationList.stream()
                .map(this::conversationToDto)
                .collect(Collectors.toList());

        return ConversationListDto.builder()
                .conversations(conversationDtoList)
                .build();
    }

    @Override
    public ConversationDto createConversation(CreateConversationDto createConversationDto) {
        //TODO Control this case in the ControllerAdvice
        String senderId = createConversationDto.getSenderId();
        String receiverId = createConversationDto.getReceiverId();

        User sender = userRepository
                .findById(senderId)
                .orElseThrow(this::createUserNotFoundException);
        User receiver = userRepository
                .findById(receiverId)
                .orElseThrow(this::createUserNotFoundException);

        //TODO Check if the conversation is already created, if not throw exception of type Conversation does not exist
        //TODO Make this so it doesn't need to create users one by one (maybe one method apart?)
        Conversation conversation = new Conversation();
        conversation.addUser(sender);
        conversation.addUser(receiver);
        conversationRepository.save(conversation);

        sender.addConversation(conversation);
        receiver.addConversation(conversation);
        userRepository.save(sender);
        userRepository.save(receiver);

        return conversionService.convert(conversation, ConversationDto.class);
    }

    @Override
    public ConversationDto addMessage(SendMessageDto messageDto) {
        String userId = messageDto.getSenderId();
        String conversationId = messageDto.getConversationId();
        String content = messageDto.getContent();

        //TODO Create a ControllerAdvice and control the throws there
        User user = userRepository.findById(userId).orElseThrow(this::createUserNotFoundException);
        Conversation conversation = conversationRepository
                .findById(conversationId)
                .orElseThrow(this::createConversationNotFoundException);

        Message message = new Message(user, content);
        conversation.addMessage(message);
        conversationRepository.save(conversation);

        return conversionService.convert(conversation, ConversationDto.class);
    }


    private ConversationDto conversationToDto(Conversation conversation) {
        return conversionService.convert(conversation, ConversationDto.class);
    }

    //TODO Create a ExceptionFactory so we don't use exception on the service and we can reuse them
    private ResponseStatusException createUserNotFoundException() {
        return new ResponseStatusException(
                HttpStatus.NOT_FOUND, "User not found"
        );
    }

    private ResponseStatusException createConversationNotFoundException() {
        return new ResponseStatusException(
                HttpStatus.NOT_FOUND, "Conversation not found"
        );
    }
}
