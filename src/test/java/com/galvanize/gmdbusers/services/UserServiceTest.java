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
import org.springframework.http.HttpStatus;

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
    void testCreateDuplicateUser() {
        userService.register(user);
        assertThrows(ConflictException.class, () -> userService.register(user));
    }

    //  GIVEN I am a user
    //  WHEN I send a POST request to the URI to login
    //  THEN I receive a successful status code when I attempt to login
    @Test
    void testValidUserLogin() {
        userService.register(user);
        User returnedUser = userService.logIn(user);
        assertEquals(user, returnedUser);
    }

    //  GIVEN I am not a registered user
    //  WHEN I send a POST request to the URI to login
    //  THEN I recieve a status code or json object indicating that I am not a registered user and therefore cannot be logged in
    @Test
    void testUnregisteredUserLogin() {
        assertThrows(UserNotFoundException.class, () -> userService.logIn(user));
    }

    //  GIVEN I am a user
    //  WHEN I send a POST request to the URI to logout
    //  THEN I recieve a successful status code when I attempt to logout


    @Test
    void testValidUserLogout() {
        userService.register(user);
        int response = userService.logOut(user);
        assertEquals(HttpStatus.OK.value(), response);
    }

    //  GIVEN I am a user
    //  WHEN I go to the endpoint associated with the action to "logout"
    //  THEN I recieve a json object or status code indicating that I have successfully been logged out

}