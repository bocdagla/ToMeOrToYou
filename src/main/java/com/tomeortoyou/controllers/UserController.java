package com.tomeortoyou.controllers;


import com.tomeortoyou.dto.request.CreateUserDto;
import com.tomeortoyou.dto.response.ConversationListDto;
import com.tomeortoyou.dto.response.UserDto;
import com.tomeortoyou.dto.response.UserListDto;
import com.tomeortoyou.repositories.UserRepository;
import com.tomeortoyou.services.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private IUserService userService;

    @GetMapping("/all")
    public UserListDto allUsers() {
        UserListDto result = userService.getAllUsers();

        return result;
    }

    //TODO Change it so it gets the userId from the headers
    @GetMapping("/{username}")
    public UserDto getUser(@PathVariable String username) {
        UserDto result = userService.getUser(username);

        return result;
    }

    @PostMapping("/create")
    public void createUser(@RequestBody CreateUserDto createUserDto) {
        userService.createUser(createUserDto.getUsername());
    }

    @GetMapping("/{userid}/conversations")
    public ConversationListDto getUserConversations(@PathVariable String userid) {
        ConversationListDto conversations = userService.getUserConversations(userid);

        return conversations;
    }
}
