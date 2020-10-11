package com.tomeortoyou.conf;

import com.tomeortoyou.repositories.IConversationRepository;
import com.tomeortoyou.repositories.IUserRepository;
import com.tomeortoyou.services.ConversationService;
import com.tomeortoyou.services.IConversationService;
import com.tomeortoyou.services.IUserService;
import com.tomeortoyou.services.UserService;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.core.convert.ConversionService;

@TestConfiguration
public class ServicesTestConfig {
    @Bean
    public IUserService userService(IConversationRepository conversationRepository,
                                    IUserRepository userRepository,
                                    ConversionService conversionService) {
        return new UserService(conversationRepository, userRepository, conversionService);
    }

    @Bean
    public IConversationService conversationService(IConversationRepository conversationRepository,
                                                    IUserRepository userRepository,
                                                    ConversionService conversionService) {
        return new ConversationService(conversationRepository, userRepository, conversionService);
    }
}