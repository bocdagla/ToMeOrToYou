package com.tomeortoyou;

import com.tomeortoyou.services.ConversationService;
import com.tomeortoyou.services.IConversationService;
import com.tomeortoyou.services.IUserService;
import com.tomeortoyou.services.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public IConversationService conversationService() {
        return new ConversationService();
    }

    @Bean
    public IUserService userService() {
        return new UserService();
    }

    //TODO Add MongoDB Config
}
