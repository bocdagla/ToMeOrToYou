package com.tomeortoyou.dto.request;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.Builder;
import lombok.NonNull;
import lombok.Value;

import java.io.Serializable;

@Builder
@Value
@JsonDeserialize(builder = CreateConversationDto.CreateConversationDtoBuilder.class)
public class CreateConversationDto implements Serializable {
    @NonNull
    String senderId;
    @NonNull
    String receiverId;

    @JsonPOJOBuilder(withPrefix = "")
    public static class CreateConversationDtoBuilder {
        // Lombok will add constructor, setters, build method
    }
}
