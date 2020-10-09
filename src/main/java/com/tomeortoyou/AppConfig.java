package com.tomeortoyou;

import com.tomeortoyou.repositories.IConversationRepository;
import com.tomeortoyou.repositories.IUserRepository;
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
    public IConversationService conversationService(IConversationRepository conversationRepository,
                                                    IUserRepository userRepository,
                                                    ConversionService conversionService) {
        return new ConversationService(conversationRepository, userRepository, conversionService);
    }

    @Bean
    public IUserService userService(IConversationRepository conversationRepository,
                                    IUserRepository userRepository,
                                    ConversionService conversionService) {
        return new UserService(conversationRepository, userRepository, conversionService);
    }

    //TODO Add MongoDB Config
}
