package com.tomeortoyou.services;

import com.tomeortoyou.dto.response.ConversationDto;
import com.tomeortoyou.dto.response.ConversationListDto;
import com.tomeortoyou.dto.response.UserDto;
import com.tomeortoyou.dto.response.UserListDto;
import com.tomeortoyou.entities.Conversation;
import com.tomeortoyou.entities.User;
import com.tomeortoyou.repositories.ConversationRepository;
import com.tomeortoyou.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

public class UserService implements IUserService {

    @Autowired
    private ConversationRepository conversationRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ConversionService conversionService;

    @Override
    public UserDto getUser(String username) {
        User user = userRepository.findByUsername(username);
        return convertUserToDto(user);
    }

    @Override
    public UserListDto getAllUsers() {
        UserListDto result = new UserListDto();
        List<User> users = userRepository.findAll();
        List<UserDto> userDtos = users.stream()
                .map(this::convertUserToDto)
                .collect(Collectors.toList());
        result.setUserList(userDtos);
        return result;
    }

    @Override
    public UserDto createUser(String username) {
        User user = new User(username);
        userRepository.save(user);
        return conversionService.convert(user, UserDto.class);
    }

    //TODO Check if user exists if not throw custom error
    public ConversationListDto getUserConversations(String username) {
        ConversationListDto result = new ConversationListDto();
        User user = userRepository.findByUsername(username);
        List<ConversationDto> userConversations = user.getConversations().stream()
                .map(this::getConversationAndConvertToDto)
                .collect(Collectors.toList());
        result.setConversations(userConversations);
        return result;
    }


    private UserDto convertUserToDto(User user) {
        return conversionService.convert(user, UserDto.class);
    }

    private ConversationDto getConversationAndConvertToDto(String conversationId) {
        //TODO Remove the conversation from the user and simply log it, this shouldn't stop the process
        Conversation conversation = conversationRepository
                .findById(conversationId)
                .orElseThrow(this::createConversationNotFoundException);
        return conversionService.convert(conversation, ConversationDto.class);
    }


    private ResponseStatusException createConversationNotFoundException() {
        return new ResponseStatusException(
                HttpStatus.NOT_FOUND, "Conversation not found"
        );
    }
}
