package com.tomeortoyou.repositories;

import com.tomeortoyou.entities.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataMongoTest
@ExtendWith(SpringExtension.class)
public class UserRepositoryTests {

    @Autowired
    private IUserRepository userRepository;

    @Test
    public void whenFindByUsername_thenReturnUser() {
        // given
        User alex = new User();
        alex.setUsername("alex");
        userRepository.save(alex);

        // when
        User found = userRepository.findByUsername(alex.getUsername());

        // then
        assertEquals(found.getUsername(), alex.getUsername());
    }
}
