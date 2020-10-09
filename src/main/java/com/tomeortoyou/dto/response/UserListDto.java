package com.tomeortoyou.dto.response;


import lombok.Builder;
import lombok.Singular;
import lombok.Value;

import java.util.List;

@Builder
@Value
public class UserListDto {
    @Singular
    List<UserDto> users;
}
