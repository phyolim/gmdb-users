package com.galvanize.gmdbusers.services;

import com.galvanize.gmdbusers.models.User;
import com.galvanize.gmdbusers.repositories.UserRepository;
import com.galvanize.gmdbusers.v1.controllers.ConflictException;
import com.galvanize.gmdbusers.v1.controllers.UserNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    final
    UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void register(User user) {
        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            throw new ConflictException("Email already Exists");
        } else {
            userRepository.save(user);
        }
    }

    public User logIn(User user) {
        return userRepository
                .findByEmailAndPassword(user.getEmail(), user.getPassword())
                .orElseThrow(UserNotFoundException::new);
    }

    public int logOut(User user) {
        final int responseCode = 200;
        return responseCode;
    }
}
