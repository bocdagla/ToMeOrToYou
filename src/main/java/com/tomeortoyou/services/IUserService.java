package com.tomeortoyou.services;

import com.tomeortoyou.dto.response.ConversationListDto;
import com.tomeortoyou.dto.response.UserDto;
import com.tomeortoyou.dto.response.UserListDto;

public interface IUserService {
    public UserListDto getAllUsers();
    public UserDto getUser(String username);
    public UserDto createUser(String username);
    public ConversationListDto getUserConversations(String username);
}
