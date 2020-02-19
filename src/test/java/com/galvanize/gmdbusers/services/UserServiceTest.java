package com.galvanize.gmdbusers.services;

import com.galvanize.gmdbusers.models.User;
import com.galvanize.gmdbusers.repositories.UserRepository;
import com.galvanize.gmdbusers.v1.controllers.ConflictException;
import com.galvanize.gmdbusers.v1.controllers.UserNotFoundException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class UserServiceTest {

    @Autowired
    UserRepository userRepository;
    @Autowired
    UserService userService;
    User user;

    @BeforeEach
    void setUp() {
        user = new User("phyotalim@gmail.com", "password");
    }

    @Test
    void testNewUserCreation() {
        userService.register(user);
        assertNotNull(user.getId());
    }

    @Test
    void testCreateDuplicateUser(){
        userService.register(user);
        assertThrows(ConflictException.class, ()-> userService.register(user));
    }

    @Test
    void testValidUserLogin(){
        userService.register(user);
        User returnedUser = userService.logIn(user);
        assertEquals(user, returnedUser);
    }

    @Test
    void testInvalidUserLogin(){
        assertThrows(UserNotFoundException.class, ()-> userService.logIn(user));
    }

    @AfterEach
    void tearDown() {
    }
}