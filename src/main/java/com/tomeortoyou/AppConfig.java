package com.tomeortoyou;

import com.tomeortoyou.repositories.ConversationRepository;
import com.tomeortoyou.repositories.UserRepository;
import com.tomeortoyou.services.ConversationService;
import com.tomeortoyou.services.IConversationService;
import com.tomeortoyou.services.IUserService;
import com.tomeortoyou.services.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.ConversionService;

@Configuration
public class AppConfig {

    @Bean
    public IConversationService conversationService(ConversationRepository conversationRepository,
                                                    UserRepository userRepository,
                                                    ConversionService conversionService) {
        return new ConversationService(conversationRepository, userRepository, conversionService);
    }

    @Bean
    public IUserService userService(ConversationRepository conversationRepository,
                                    UserRepository userRepository,
                                    ConversionService conversionService) {
        return new UserService(conversationRepository, userRepository, conversionService);
    }

    //TODO Add MongoDB Config
}
