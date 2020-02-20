package com.galvanize.gmdbusers.v1.controllers;

import com.galvanize.gmdbusers.models.User;
import com.galvanize.gmdbusers.services.UserService;
import com.galvanize.gmdbusers.v1.dto.LoginRequest;
import com.galvanize.gmdbusers.v1.dto.LogoutRequest;
import com.galvanize.gmdbusers.v1.dto.RegisterRequest;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static com.galvanize.gmdbusers.util.Mapper.mapAll;
import static javax.servlet.http.HttpServletResponse.SC_CONFLICT;
import static javax.servlet.http.HttpServletResponse.SC_OK;

@Api(tags = "Users controller")
@CrossOrigin(origins = "*")
@RestController("UsersControllerV1")
@RequestMapping("/v1/users")

public class UserController {
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);
    final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }


    @ApiOperation("Register new user")
    @ApiResponses(value = {
            @ApiResponse(code = SC_OK, message = "ok"),
            @ApiResponse(code = SC_CONFLICT, message = "There is already an account associated with that email.")
    })

    @PostMapping("/register")
    public ResponseEntity<User> register(@ApiParam("New User")
                                         @RequestBody RegisterRequest registerRequest) {
        User user = this.userService.register(mapAll(registerRequest, User.class));
        return ResponseEntity.ok(user);
    }

    @ApiOperation("Login with existing user")
    @PostMapping("/login")
    public ResponseEntity<User> login(@Valid @RequestBody LoginRequest loginRequest) {
        User user = this.userService.login(mapAll(loginRequest, User.class));
        return ResponseEntity.ok(user);
    }

    @ApiOperation("Logout User")
    @PostMapping("/logout")
    public ResponseEntity logoutPost(@RequestBody LogoutRequest logoutRequest) {
        this.userService.logout(mapAll(logoutRequest, User.class));
        return ResponseEntity.ok().build();
    }

    @ApiOperation("Logout User - in development")
    @GetMapping("/logout")
    public void logoutGet(@RequestParam(required = false) String email) {
        this.userService.logout(mapAll(email, User.class));
    }
}
