package com.tomeortoyou.dto.response;

import lombok.Builder;
import lombok.Value;

//TODO Add Lombok validations

@Builder
@Value
public class UserDto {
    String id;
    String userName;
}
