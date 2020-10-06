package com.tomeortoyou.services;

import com.tomeortoyou.dto.request.CreateUserDto;
import com.tomeortoyou.dto.response.ConversationDto;
import com.tomeortoyou.dto.response.ConversationListDto;
import com.tomeortoyou.dto.response.UserDto;
import com.tomeortoyou.dto.response.UserListDto;
import com.tomeortoyou.entities.Conversation;
import com.tomeortoyou.entities.User;
import com.tomeortoyou.repositories.ConversationRepository;
import com.tomeortoyou.repositories.UserRepository;
import org.springframework.core.convert.ConversionService;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

public class UserService implements IUserService {

    private final ConversationRepository conversationRepository;
    private final UserRepository userRepository;
    private final ConversionService conversionService;

    public UserService(ConversationRepository conversationRepository, UserRepository userRepository, ConversionService conversionService) {
        this.conversationRepository = conversationRepository;
        this.userRepository = userRepository;
        this.conversionService = conversionService;
    }

    @Override
    public UserDto getUser(String username) {
        User user = userRepository.findByUsername(username);
        return convertUserToDto(user);
    }

    @Override
    public UserListDto getAllUsers() {
        List<User> users = userRepository.findAll();
        List<UserDto> userDtos = users.stream()
                .map(this::convertUserToDto)
                .collect(Collectors.toList());
        return UserListDto.builder()
                .userList(userDtos)
                .build();
    }

    @Override
    public UserDto createUser(CreateUserDto userDto) {
        User user = new User(userDto.getUsername());
        userRepository.save(user);
        return conversionService.convert(user, UserDto.class);
    }

    //TODO Check if user exists if not throw custom error
    public ConversationListDto getUserConversations(String username) {
        User user = userRepository.findByUsername(username);
        List<ConversationDto> userConversations = user.getConversations().stream()
                .map(this::getConversationAndConvertToDto)
                .collect(Collectors.toList());

        return ConversationListDto.builder()
                .conversations(userConversations)
                .build();
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
