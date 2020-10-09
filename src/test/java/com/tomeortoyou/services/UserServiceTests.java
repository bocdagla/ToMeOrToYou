package com.tomeortoyou.services;

import com.tomeortoyou.conf.UserServiceTestConfig;
import com.tomeortoyou.dto.request.CreateUserDto;
import com.tomeortoyou.dto.response.*;
import com.tomeortoyou.mock.ConversionServiceMockDataGenerator;
import com.tomeortoyou.mock.MongoIds;
import com.tomeortoyou.mock.RepositoryMockDataGenerator;
import com.tomeortoyou.repositories.IConversationRepository;
import com.tomeortoyou.repositories.IUserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.core.convert.ConversionService;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;


@ExtendWith(SpringExtension.class)
@Import({UserServiceTestConfig.class})
public class UserServiceTests {
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
        ConversionServiceMockDataGenerator conversionServiceMockDataGenerator = new ConversionServiceMockDataGenerator(conversionService);
        RepositoryMockDataGenerator repositoryMockDataGenerator = new RepositoryMockDataGenerator(userRepository, conversationRepository);
        conversionServiceMockDataGenerator.generate();
        repositoryMockDataGenerator.generate();
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getAllUsers_thenReturnAllUsers() {
        // given
        UserListDto expected = UserListDto.builder()
                .user(UserDto.builder().id(MongoIds.get("Alex")).userName("Alex").build())
                .user(UserDto.builder().id(MongoIds.get("Mike")).userName("Mike").build())
                .build();

        // when
        UserListDto users = userService.getAllUsers();

        // then
        assertThat(users, is(expected));
    }

    @Test
    public void getUser_thenReturnUser() {
        // given
        UserDto expected = UserDto.builder().id(MongoIds.get("Alex")).userName("Alex").build();

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
                        .id(MongoIds.get("Conversation1"))
                        .message(MessageDto.builder().content("Hello, Mike how are you doing?").build())
                        .message(MessageDto.builder().content("Hey Alex, I'm doing great, thanks").build())
                        .build())
                .build();

        // when
        ConversationListDto userConversations = userService.getUserConversations(MongoIds.get("Alex"));

        // then
        assertThat(userConversations, is(expected));
    }

    @Test
    public void createUser_thenReturnCreatedUser() {
        // given
        UserDto expected = UserDto.builder().id(MongoIds.get("Bob")).userName("Bob").build();

        CreateUserDto userToCreate = CreateUserDto.builder().username("Bob").build();

        // when
        UserDto userCreated = userService.createUser(userToCreate);

        // then
        assertThat(userCreated, is(expected));
    }
}
