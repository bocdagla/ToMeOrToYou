package com.tomeortoyou.services;

import com.tomeortoyou.dto.request.CreateUserDto;
import com.tomeortoyou.dto.response.ConversationListDto;
import com.tomeortoyou.dto.response.UserDto;
import com.tomeortoyou.dto.response.UserListDto;

public interface IUserService {
    UserListDto getAllUsers();
    UserDto getUser(String username);
    UserDto createUser(CreateUserDto createUserDto);
    ConversationListDto getUserConversations(String username);
}
