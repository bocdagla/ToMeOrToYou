package com.tomeortoyou.converters;

import com.tomeortoyou.conf.ConvertersTestConfig;
import com.tomeortoyou.dto.response.UserDto;
import com.tomeortoyou.entities.Conversation;
import com.tomeortoyou.entities.User;
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

public class UserToDtoConverterTest {

    private MockDataGenerator mockDataGenerator;

    @Autowired
    private ConversionService conversionService;

    @BeforeEach
    public void setUp() {
        mockDataGenerator = new MockDataGenerator();
    }

    @Test
    void convertUserToDto_thenGetUserDto() {
        //given
        Conversation conversation1 = mockDataGenerator.getConversation();
        Conversation conversation2 = mockDataGenerator.getConversation();
        User user = mockDataGenerator.getUser();
        user.getConversations().add(conversation1.getId());
        user.getConversations().add(conversation2.getId());
        UserDto expected = mockDataGenerator.getUserDto(user);

        //when
        UserDto result = conversionService.convert(user, UserDto.class);

        //then
        assertThat(result, is(expected));
    }

}
