package com.tomeortoyou.controllers;

import com.tomeortoyou.conf.UserServiceTestConfig;
import com.tomeortoyou.dto.request.CreateUserDto;
import com.tomeortoyou.dto.response.*;
import com.tomeortoyou.entities.Conversation;
import com.tomeortoyou.entities.Message;
import com.tomeortoyou.entities.User;
import com.tomeortoyou.repositories.IConversationRepository;
import com.tomeortoyou.repositories.IUserRepository;
import com.tomeortoyou.services.IUserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.core.convert.ConversionService;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;


@ExtendWith(SpringExtension.class)
@Import({UserServiceTestConfig.class})
public class UserServiceTests {


    private final Map<String, String> mongoIds = Map.of(
            "Alex", "5f7ef16e593387395a7eb249",
            "Mike", "5f7ef19d9956885dace803ff",
            "Bob", "5f7ef1a4d16c1372cd9ad129",
            "Conversation1", "5f7ef1a9a0437323aa41a7b5"
    );

    @MockBean
    private IUserRepository userRepository;
    @MockBean
    private IConversationRepository conversationRepository;
    @MockBean
    private ConversionService conversionService;

    @Autowired
    private IUserService userService;

    @BeforeEach
    public void setUp() {
        User alex = new User();
        alex.setId(mongoIds.get("Alex"));
        alex.setUsername("Alex");

        User mike = new User();
        mike.setId(mongoIds.get("Mike"));
        mike.setUsername("Mike");

        User bob = new User();
        bob.setUsername("Bob");

        UserDto alexDto = UserDto.builder().id(mongoIds.get("Alex")).userName("Alex").build();
        UserDto mikeDto = UserDto.builder().id(mongoIds.get("Mike")).userName("Mike").build();
        UserDto bobDto = UserDto.builder().id(mongoIds.get("Bob")).userName("Bob").build();

        Message message1 = new Message(
                mike.getId(),
                "Hello, Mike how are you doing?");
        Message message2 = new Message(
                alex.getId(),
                "Hey Alex, I'm doing great, thanks");
        Conversation conversation = new Conversation();
        conversation.setId(mongoIds.get("Conversation1"));
        conversation.getUsers().add(alex.getId());
        conversation.getUsers().add(mike.getId());
        conversation.getMessages().add(message1);
        conversation.getMessages().add(message2);

        alex.getConversations().add(conversation.getId());

        ConversationDto conversationDto = ConversationDto.builder()
                .id(mongoIds.get("Conversation1"))
                .message(MessageDto.builder().content("Hello, Mike how are you doing?").build())
                .message(MessageDto.builder().content("Hey Alex, I'm doing great, thanks").build())
                .build();

        //Bean Mock
        conversionServiceMock(alex, mike, bob, alexDto, mikeDto, bobDto, conversation, conversationDto);
        userRepositoryMock(alex, mike);
        conversationRepositoryMock(alex, conversation);

        MockitoAnnotations.initMocks(this);
    }

    private void conversationRepositoryMock(User alex, Conversation conversation) {
        Mockito.when(conversationRepository.findById(mongoIds.get("Conversation1")))
                .thenReturn(Optional.of(conversation));
        Mockito.when(conversationRepository.findAllById(alex.getConversations()))
                .thenReturn(List.of(conversation));
        Mockito.when(conversationRepository.findAll())
                .thenReturn(List.of(conversation));
    }

    private void userRepositoryMock(User alex, User mike) {
        Mockito.when(userRepository.findByUsername(alex.getUsername()))
                .thenReturn(alex);
        Mockito.when(userRepository.findById(mongoIds.get("Alex")))
                .thenReturn(Optional.of(alex));
        Mockito.when(userRepository.findAll())
                .thenReturn(List.of(alex, mike));
    }

    private void conversionServiceMock(User alex, User mike, User bob, UserDto alexDto, UserDto mikeDto, UserDto bobDto, Conversation conversation, ConversationDto conversationDto) {
        Mockito.when(conversionService.convert(alex, UserDto.class))
                .thenReturn(alexDto);
        Mockito.when(conversionService.convert(mike, UserDto.class))
                .thenReturn(mikeDto);
        Mockito.when(conversionService.convert(bob, UserDto.class))
                .thenReturn(bobDto);
        Mockito.when(conversionService.convert(conversation, ConversationDto.class))
                .thenReturn(conversationDto);
    }

    @Test
    public void getAllUsers_thenReturnAllUsers() {
        // given
        UserListDto expected = UserListDto.builder()
                .user(UserDto.builder().id(mongoIds.get("Alex")).userName("Alex").build())
                .user(UserDto.builder().id(mongoIds.get("Mike")).userName("Mike").build())
                .build();

        // when
        UserListDto users = userService.getAllUsers();

        // then
        assertThat(users, is(expected));
    }

    @Test
    public void getUser_thenReturnUser() {
        // given
        UserDto expected = UserDto.builder().id(mongoIds.get("Alex")).userName("Alex").build();

        // when
        UserDto users = userService.getUser(expected.getUserName());

        // then
        assertThat(users, is(expected));
    }

    @Test
    public void getUserConversations_thenReturnUserConversations() {
        // given
        ConversationListDto expected = ConversationListDto.builder()
                .conversation(ConversationDto.builder()
                        .id(mongoIds.get("Conversation1"))
                        .message(MessageDto.builder().content("Hello, Mike how are you doing?").build())
                        .message(MessageDto.builder().content("Hey Alex, I'm doing great, thanks").build())
                        .build())
                .build();

        // when
        ConversationListDto userConversations = userService.getUserConversations(mongoIds.get("Alex"));

        // then
        assertThat(userConversations, is(expected));
    }

    @Test
    public void createUser_thenReturnCreatedUser() {
        // given
        UserDto expected = UserDto.builder().id(mongoIds.get("Bob")).userName("Bob").build();

        CreateUserDto userToCreate = CreateUserDto.builder().username("Bob").build();

        // when
        UserDto userCreated = userService.createUser(userToCreate);

        // then
        assertThat(userCreated, is(expected));
    }
}
