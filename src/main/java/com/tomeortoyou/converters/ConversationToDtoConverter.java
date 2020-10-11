package com.tomeortoyou.converters;

import com.tomeortoyou.dto.response.ConversationDto;
import com.tomeortoyou.dto.response.MessageDto;
import com.tomeortoyou.entities.Conversation;
import com.tomeortoyou.entities.Message;
import org.springframework.core.convert.converter.Converter;

import java.util.List;
import java.util.stream.Collectors;

public class ConversationToDtoConverter implements Converter<Conversation, ConversationDto> {

    @Override
    public ConversationDto convert(Conversation source) {
        List<MessageDto> messageDtoList = source.getMessages().stream()
                .map(this::convertMessageToMessageDto)
                .collect(Collectors.toList());
        return ConversationDto.builder()
                .id(source.getId())
                .messages(messageDtoList)
                .build();
    }

    private MessageDto convertMessageToMessageDto(Message message) {
        return MessageDto.builder()
                .content(message.getContent())
                .build();
    }
}
