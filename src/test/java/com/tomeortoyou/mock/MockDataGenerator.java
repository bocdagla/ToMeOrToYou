package com.tomeortoyou.mock;

import com.tomeortoyou.dto.response.ConversationDto;
import com.tomeortoyou.dto.response.MessageDto;
import com.tomeortoyou.dto.response.UserDto;
import com.tomeortoyou.entities.Conversation;
import com.tomeortoyou.entities.Message;
import com.tomeortoyou.entities.User;

import java.util.List;
import java.util.stream.Collectors;

public class MockDataGenerator {

    private int count;

    public MockDataGenerator() {
        count = 0;
    }

    private int getCount() {
        return count++;
    }

    private void setCount(int count) {
        this.count = count;
    }

    public User getUser() {
        User result = new User();
        result.setId(MongoIds.get());
        result.setUsername("User " + getCount());
        return result;
    }

    public UserDto getUserDto(User user) {
        return UserDto.builder()
                .id(user.getId())
                .userName(user.getUsername())
                .build();
    }

    public Conversation getConversation() {
        Conversation conversation = new Conversation();
        conversation.setId(MongoIds.get());
        return conversation;
    }

    public ConversationDto getConversationDto(Conversation conversation) {
        List<MessageDto> messageDtoList = conversation.getMessages().stream()
                .map(this::getMessageDto)
                .collect(Collectors.toList());

        return ConversationDto.builder()
                .id(conversation.getId())
                .messages(messageDtoList)
                .build();
    }

    public Message getMessage() {
        return new Message(MongoIds.get(), "Message Content " + getCount());
    }

    public MessageDto getMessageDto(Message message) {
        return MessageDto.builder().content(message.getContent()).build();
    }
}
