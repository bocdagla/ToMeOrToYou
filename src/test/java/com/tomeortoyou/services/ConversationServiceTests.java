package com.tomeortoyou.services;


import com.tomeortoyou.conf.UserServiceTestConfig;
import com.tomeortoyou.dto.request.CreateConversationDto;
import com.tomeortoyou.dto.request.SendMessageDto;
import com.tomeortoyou.dto.response.ConversationDto;
import com.tomeortoyou.dto.response.ConversationListDto;
import com.tomeortoyou.dto.response.MessageDto;
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
public class ConversationServiceTests {


    @MockBean
    private IUserRepository userRepository;
    @MockBean
    private IConversationRepository conversationRepository;
    @MockBean
    private ConversionService conversionService;


    @Autowired
    private IConversationService conversationService;

    @BeforeEach
    public void setUp() {
        ConversionServiceMockDataGenerator conversionServiceMockDataGenerator = new ConversionServiceMockDataGenerator(conversionService);
        RepositoryMockDataGenerator repositoryMockDataGenerator = new RepositoryMockDataGenerator(userRepository, conversationRepository);
        conversionServiceMockDataGenerator.generate();
        repositoryMockDataGenerator.generate();
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void getAllConversations_thenGetAllConversations() {
        // given
        ConversationListDto expected = ConversationListDto.builder()
                .conversation(ConversationDto.builder()
                        .id(MongoIds.get("Conversation1"))
                        .message(MessageDto.builder().content("Hello, Mike how are you doing?").build())
                        .message(MessageDto.builder().content("Hey Alex, I'm doing great, thanks").build())
                        .build())
                .build();

        // when
        ConversationListDto allConversations = conversationService.getAllConversations();

        // then
        assertThat(allConversations, is(expected));
    }

    @Test
    void createConversation_thenGetConversation() {
        // given
        ConversationDto expected = ConversationDto.builder()
                .id(MongoIds.get("Conversation1"))
                .message(MessageDto.builder().content("Hello, Mike how are you doing?").build())
                .message(MessageDto.builder().content("Hey Alex, I'm doing great, thanks").build())
                .build();

        // when
        CreateConversationDto createConversationDto = CreateConversationDto.builder()
                .senderId(MongoIds.get("Alex"))
                .receiverId(MongoIds.get("Mike"))
                .build();
        ConversationDto conversationDto = conversationService.createConversation(createConversationDto);

        // then
        assertThat(conversationDto, is(expected));
    }

    @Test
    void addMessage_thenGetConversation() {
        // given
        ConversationDto expected = ConversationDto.builder()
                .id(MongoIds.get("Conversation1"))
                .message(MessageDto.builder().content("Hello, Mike how are you doing?").build())
                .message(MessageDto.builder().content("Hey Alex, I'm doing great, thanks").build())
                .build();
        // when
        SendMessageDto messageDto = SendMessageDto.builder()
                .senderId(MongoIds.get("Alex"))
                .conversationId(MongoIds.get("Conversation1"))
                .content("Something that wont save")
                .build();
        ConversationDto conversationDto = conversationService.addMessage(messageDto);

        // then
        assertThat(conversationDto, is(expected));
    }
}
