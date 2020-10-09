package com.tomeortoyou.services;

import com.tomeortoyou.dto.request.CreateUserDto;
import com.tomeortoyou.dto.response.ConversationDto;
import com.tomeortoyou.dto.response.ConversationListDto;
import com.tomeortoyou.dto.response.UserDto;
import com.tomeortoyou.dto.response.UserListDto;
import com.tomeortoyou.entities.Conversation;
import com.tomeortoyou.entities.User;
import com.tomeortoyou.repositories.IConversationRepository;
import com.tomeortoyou.repositories.IUserRepository;
import org.springframework.core.convert.ConversionService;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class UserService implements IUserService {

    private final IConversationRepository conversationRepository;
    private final IUserRepository userRepository;
    private final ConversionService conversionService;

    public UserService(IConversationRepository conversationRepository, IUserRepository userRepository, ConversionService conversionService) {
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
                .users(userDtos)
                .build();
    }

    @Override
    public UserDto createUser(CreateUserDto createUserDto) {
        User user = new User();
        user.setUsername(createUserDto.getUsername());
        userRepository.save(user);
        return conversionService.convert(user, UserDto.class);
    }

    //TODO Check if user exists if not throw custom error
    public ConversationListDto getUserConversations(String userId) {
        List<Conversation> userConversations = new ArrayList<>();
        User user = userRepository.findById(userId).orElseThrow(this::createUserNotFoundException);
        conversationRepository.findAllById(user.getConversations())
                .forEach(userConversations::add);

        List<ConversationDto> conversationDtos = userConversations.stream()
                .map(this::convertConversationToDto)
                .collect(Collectors.toList());

        return ConversationListDto.builder()
                .conversations(conversationDtos)
                .build();
    }


    private UserDto convertUserToDto(User user) {
        return conversionService.convert(user, UserDto.class);
    }

    private ConversationDto convertConversationToDto(Conversation conversation) {
        //TODO Remove the conversation from the user and simply log it, this shouldn't stop the process
        return conversionService.convert(conversation, ConversationDto.class);
    }

    //TODO Create a ExceptionFactory so we don't use exception on the service and we can reuse them
    private ResponseStatusException createUserNotFoundException() {
        return new ResponseStatusException(
                HttpStatus.NOT_FOUND, "User not found"
        );
    }
}
