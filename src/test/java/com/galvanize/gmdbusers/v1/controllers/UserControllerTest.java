package com.galvanize.gmdbusers.v1.controllers;

import com.galvanize.gmdbusers.repositories.UserRepository;
import com.galvanize.gmdbusers.v1.dto.UserDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class UserControllerTest {

    @Autowired
    MockMvc mockMvc;
    @Autowired
    UserRepository userRepository;

    UserDto userDto;

    @BeforeEach
    void setUp() {
        userDto = new UserDto("phyotalim@gmail.com", "password");
    }

    //  Users can register for a new account (using email)
    //  Users can login using email
    //  Users can logout

    //  register user Test
    @Test
    void testUserRegistration() throws Exception {
        String url = "/v1/users/register";
        MockHttpServletRequestBuilder request = post(url).contentType(MediaType.APPLICATION_JSON)
                .content("{\"email\": \"phyotalim2@gmail.com\", \"password\": \"testPassword2\"}");
        mockMvc
                .perform(request)
                .andDo(print())
                .andExpect(status().isOk());
    }

    //  GIVEN I am a user
    //  WHEN I send a POST request to the URI to login
    //  THEN I receive a successful status code when I attempt to login
    @Test
    void testValidUserLogin() throws Exception {
        String url = "/v1/users/login";
        MockHttpServletRequestBuilder request = post(url).contentType(MediaType.APPLICATION_JSON)
                .content("{\"email\": \"phyotalim@gmail.com\", \"password\": \"testPassword\"}");
        mockMvc
                .perform(request)
                .andDo(print())
                .andExpect(status().isOk());
    }

    //  GIVEN I am not a registered user
    //  WHEN I send a POST request to the URI to login
    //  THEN I recieve a status code or json object indicating that I am not a registered user and therefore cannot be logged in

    @Test
    void testUnregisteredUserLogin() throws Exception {
        String url = "/v1/users/login";
        MockHttpServletRequestBuilder request = post(url).contentType(MediaType.APPLICATION_JSON)
                .content("{\"email\": \"phyotalim20@gmail.com\", \"password\": \"testPassword\"}");
        mockMvc
                .perform(request)
                .andDo(print())
                .andExpect(status().isNotFound());
    }
    //  GIVEN I am a user
    //  WHEN I send a POST request to the URI to logout
    //  THEN I recieve a successful status code when I attempt to logout

    @Test
    void testValidUserLogout() throws Exception {
        String url = "/v1/users/logout";
        MockHttpServletRequestBuilder request = post(url).contentType(MediaType.APPLICATION_JSON)
                .content("{\"email\": \"phyotalim@gmail.com\"}");
        mockMvc
                .perform(request)
                .andDo(print())
                .andExpect(status().isOk());
    }

    //  GIVEN I am a user
    //  WHEN I go to the endpoint associated with the action to "logout"
    //  THEN I recieve a json object or status code indicating that I have successfully been logged out
}

