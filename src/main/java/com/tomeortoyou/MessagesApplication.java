package com.tomeortoyou;

import com.tomeortoyou.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MessagesApplication {

    @Autowired
    private UserRepository repository;

    public static void main(String[] args) {
        SpringApplication.run(MessagesApplication.class, args);
    }

}
