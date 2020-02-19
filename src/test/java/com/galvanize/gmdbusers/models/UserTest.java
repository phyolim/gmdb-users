package com.galvanize.gmdbusers.models;

import com.galvanize.gmdbusers.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@Transactional
class UserTest {

    Logger logger = LoggerFactory.getLogger(UserTest.class);

    String email;
    String password;

    @Autowired
    UserRepository userRepository;
    @Autowired
    User user;
    @Autowired
    User userFromDb;

    @BeforeEach
    void setUp() {
        email = "phyotalim@gmail.com";
        password = "password";
        user = new User(email, password);
    }

    @Test
    void testConstructorInSetup() {
        userRepository.saveAndFlush(user);
        userFromDb = userRepository.findByEmailAndPassword(email, password).get();
        assertEquals(password, userFromDb.getPassword());
        assertEquals(email, userFromDb.getEmail());
        assertNotNull(userFromDb.getId().toString());
    }

    @Test
    void testNoArgConstructor() {
        User user = new User();
        user.setEmail("phyotalim@gmail.com");
        user.setPassword("password");
        assertEquals(user, this.user);
    }
}