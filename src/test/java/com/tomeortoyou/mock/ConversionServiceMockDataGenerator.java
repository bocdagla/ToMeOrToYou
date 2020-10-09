package com.tomeortoyou.mock;

import com.tomeortoyou.dto.response.ConversationDto;
import com.tomeortoyou.dto.response.MessageDto;
import com.tomeortoyou.dto.response.UserDto;
import com.tomeortoyou.entities.Conversation;
import com.tomeortoyou.entities.Message;
import com.tomeortoyou.entities.User;
import org.mockito.Mockito;
import org.springframework.core.convert.ConversionService;

//TODO Create a generic file that reads from a json
public class ConversionServiceMockDataGenerator {
    private ConversionService conversionService;

    public ConversionServiceMockDataGenerator(ConversionService conversionService) {
        this.conversionService = conversionService;
    }

    public void generate() {
        User alex = new User();
        alex.setId(MongoIds.get("Alex"));
        alex.setUsername("Alex");

        User mike = new User();
        mike.setId(MongoIds.get("Mike"));
        mike.setUsername("Mike");

        User bob = new User();
        bob.setUsername("Bob");

        UserDto alexDto = UserDto.builder().id(MongoIds.get("Alex")).userName("Alex").build();
        UserDto mikeDto = UserDto.builder().id(MongoIds.get("Mike")).userName("Mike").build();
        UserDto bobDto = UserDto.builder().id(MongoIds.get("Bob")).userName("Bob").build();

        Message message1 = new Message(
                mike.getId(),
                "Hello, Mike how are you doing?");
        Message message2 = new Message(
                alex.getId(),
                "Hey Alex, I'm doing great, thanks");
        Conversation conversation = new Conversation();
        conversation.setId(MongoIds.get("Conversation1"));
        conversation.getUsers().add(alex.getId());
        conversation.getUsers().add(mike.getId());
        conversation.getMessages().add(message1);
        conversation.getMessages().add(message2);

        alex.getConversations().add(conversation.getId());

        ConversationDto conversationDto = ConversationDto.builder()
                .id(MongoIds.get("Conversation1"))
                .message(MessageDto.builder().content("Hello, Mike how are you doing?").build())
                .message(MessageDto.builder().content("Hey Alex, I'm doing great, thanks").build())
                .build();

        //Bean Mock
        conversionServiceMock(alex, mike, bob, alexDto, mikeDto, bobDto, conversation, conversationDto);
    }

    private void conversionServiceMock(User alex, User mike, User bob,
                                       UserDto alexDto, UserDto mikeDto, UserDto bobDto,
                                       Conversation conversation,
                                       ConversationDto conversationDto) {
        Mockito.when(conversionService.convert(alex, UserDto.class))
                .thenReturn(alexDto);
        Mockito.when(conversionService.convert(mike, UserDto.class))
                .thenReturn(mikeDto);
        Mockito.when(conversionService.convert(bob, UserDto.class))
                .thenReturn(bobDto);
        Mockito.when(conversionService.convert(Mockito.any(Conversation.class), Mockito.eq(ConversationDto.class)))
                .thenReturn(conversationDto);
    }
}
