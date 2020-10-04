package com.tomeortoyou.converters;

import com.tomeortoyou.dto.response.UserDto;
import com.tomeortoyou.entities.User;
import org.springframework.core.convert.converter.Converter;

public class UserToUserDtoConverter implements Converter<User, UserDto> {

    @Override
    public UserDto convert(User user) {
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setUserName(user.getUsername());
        return userDto;
    }
}
