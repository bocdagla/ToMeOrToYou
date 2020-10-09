package com.tomeortoyou.controllers;


import com.tomeortoyou.dto.request.CreateUserDto;
import com.tomeortoyou.dto.response.ConversationListDto;
import com.tomeortoyou.dto.response.UserDto;
import com.tomeortoyou.dto.response.UserListDto;
import com.tomeortoyou.services.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private IUserService userService;

    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    public UserListDto allUsers() {
        return userService.getAllUsers();
    }

    //TODO Change it so it gets the userId from the headers or session
    @GetMapping("/{username}")
    @ResponseStatus(HttpStatus.OK)
    public UserDto getUser(@PathVariable String userId) {
        return userService.getUser(userId);
    }

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public UserDto createUser(@RequestBody CreateUserDto createUserDto) {
        return userService.createUser(createUserDto);
    }

    @GetMapping("/{userid}/conversations")
    @ResponseStatus(HttpStatus.OK)
    public ConversationListDto getUserConversations(@PathVariable String userid) {
        return userService.getUserConversations(userid);
    }
}
