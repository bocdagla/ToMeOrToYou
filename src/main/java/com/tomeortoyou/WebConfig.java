package com.tomeortoyou;

import com.tomeortoyou.converters.ConversationToDtoConverter;
import com.tomeortoyou.converters.UserToDtoConverter;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addFormatters(FormatterRegistry registry) {

        registry.addConverter(new UserToDtoConverter());
        registry.addConverter(new ConversationToDtoConverter());

    }
}
