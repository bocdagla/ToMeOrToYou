package com.tomeortoyou.conf;

import com.tomeortoyou.converters.ConversationToDtoConverter;
import com.tomeortoyou.converters.UserToDtoConverter;
import org.springframework.boot.convert.ApplicationConversionService;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.core.convert.ConversionService;

@TestConfiguration
public class ConvertersTestConfig {
    @Bean
    public ConversionService conversionService() {
        ApplicationConversionService applicationConversionService = new ApplicationConversionService();
        applicationConversionService.addConverter(new UserToDtoConverter());
        applicationConversionService.addConverter(new ConversationToDtoConverter());
        return applicationConversionService;
    }
}
