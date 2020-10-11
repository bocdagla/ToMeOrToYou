package com.tomeortoyou.converters;

import com.tomeortoyou.conf.ConvertersTestConfig;
import com.tomeortoyou.dto.response.ConversationDto;
import com.tomeortoyou.entities.Conversation;
import com.tomeortoyou.entities.Message;
import com.tomeortoyou.mock.MockDataGenerator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.core.convert.ConversionService;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

@ExtendWith(SpringExtension.class)
@Import({ConvertersTestConfig.class})

public class ConversationToDtoConverterTest {

    private MockDataGenerator mockDataGenerator;

    @Autowired
    private ConversionService conversionService;

    @BeforeEach
    public void setUp() {
        mockDataGenerator = new MockDataGenerator();
    }

    @Test
    void convertConversationToDto_thenGetConversationDto() {
        //given
        Conversation conversation = mockDataGenerator.getConversation();
        Message message1 = mockDataGenerator.getMessage();
        Message message2 = mockDataGenerator.getMessage();
        conversation.getMessages().add(message1);
        conversation.getMessages().add(message2);

        ConversationDto expected = mockDataGenerator.getConversationDto(conversation);

        //when
        ConversationDto result = conversionService.convert(conversation, ConversationDto.class);

        //then
        assertThat(result, is(expected));
    }

}
