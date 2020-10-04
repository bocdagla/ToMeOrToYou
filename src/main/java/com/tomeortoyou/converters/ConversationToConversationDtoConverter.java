package com.tomeortoyou.converters;

import com.tomeortoyou.dto.response.ConversationDto;
import com.tomeortoyou.dto.response.MessageDto;
import com.tomeortoyou.entities.Conversation;
import com.tomeortoyou.entities.Message;
import org.springframework.core.convert.converter.Converter;

import java.util.List;
import java.util.stream.Collectors;

public class ConversationToConversationDtoConverter implements Converter<Conversation, ConversationDto> {

    @Override
    public ConversationDto convert(Conversation source) {
        ConversationDto result = new ConversationDto();
        List<Message> messageList = source.getMessages();
        List<MessageDto> messageDtoList = source.getMessages().stream()
                .map(this::convertMessageToMessageDto)
                .collect(Collectors.toList());
        result.setId(source.getId());
        result.setMessages(messageDtoList);
        return result;
    }

    private MessageDto convertMessageToMessageDto(Message message) {
        MessageDto messageDto = new MessageDto();
        messageDto.setContent(message.getContent());
        return messageDto;
    }
}
