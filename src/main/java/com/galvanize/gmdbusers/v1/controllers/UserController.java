package com.galvanize.gmdbusers.v1.controllers;

import com.galvanize.gmdbusers.models.User;
import com.galvanize.gmdbusers.services.UserService;
import com.galvanize.gmdbusers.v1.dto.UserDto;
import io.swagger.annotations.*;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import static javax.servlet.http.HttpServletResponse.SC_BAD_REQUEST;
import static javax.servlet.http.HttpServletResponse.SC_OK;

@Api(tags = "Users controller")
@CrossOrigin(origins = "*")
@RestController("UsersControllerV1")
@RequestMapping(value = "/v1/users")

public class UserController {
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);
    final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    @ApiOperation(value = "Register new user")
    @ApiResponses(value = {
            @ApiResponse(code = SC_OK, message = "ok"),
            @ApiResponse(code = SC_BAD_REQUEST, message = "An unexpected error occurred")
    })
    public void register(@ApiParam("Claim external id")
                             @RequestBody UserDto userDto){
        this.userService.register(convertToEntity(userDto));
    }

    @PostMapping("/login")
    public void logIn(@RequestBody UserDto userDto){
        this.userService.logIn(convertToEntity(userDto));
    }

    private User convertToEntity(UserDto userDto) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(userDto, User.class);
    }

    private UserDto convertToModel(User user) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(user, UserDto.class);
    }

}
