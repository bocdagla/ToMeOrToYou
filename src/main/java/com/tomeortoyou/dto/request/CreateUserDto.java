package com.tomeortoyou.dto.request;


import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.Builder;
import lombok.NonNull;
import lombok.Value;

import java.io.Serializable;

@Builder
@Value
@JsonDeserialize(builder = CreateUserDto.CreateUserDtoBuilder.class)
public class CreateUserDto implements Serializable {
    @NonNull
    String username;

    @JsonPOJOBuilder(withPrefix = "")
    public static class CreateUserDtoBuilder {
        // Lombok will add constructor, setters, build method
    }
}
