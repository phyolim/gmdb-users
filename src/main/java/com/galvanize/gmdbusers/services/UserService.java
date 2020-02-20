package com.galvanize.gmdbusers.services;

import com.galvanize.gmdbusers.models.User;
import com.galvanize.gmdbusers.repositories.UserRepository;
import com.galvanize.gmdbusers.exceptions.ConflictException;
import com.galvanize.gmdbusers.exceptions.UserNotFoundException;
import com.galvanize.gmdbusers.v1.controllers.UserController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    final UserRepository userRepository;

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User register(User user) {
        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            throw new ConflictException("There is already an account associated with that email.");
        } else {
            userRepository.save(user);
        }
        return user;
    }

    public User login(User user) {
        logger.debug(user.toString());
        return userRepository
                .findByEmailAndPassword(user.getEmail(), user.getPassword())
                .orElseThrow(() -> new UserNotFoundException("User Not Found"));
    }

    public int logout(User user) {
        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            return 200;
        } else {
            throw new UserNotFoundException("User Not Found");
        }
    }
}
