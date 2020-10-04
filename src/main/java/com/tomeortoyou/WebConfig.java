package com.tomeortoyou;

import com.tomeortoyou.converters.ConversationToConversationDtoConverter;
import com.tomeortoyou.converters.UserToUserDtoConverter;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addFormatters(FormatterRegistry registry) {

        registry.addConverter(new UserToUserDtoConverter());
        registry.addConverter(new ConversationToConversationDtoConverter());

    }
}
