package com.tomeortoyou.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//TODO Add Lombok validations
@Setter
@Getter
@NoArgsConstructor
public class UserDto {
    private String id;
    private String userName;
}
