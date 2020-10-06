package com.tomeortoyou.dto.request;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.Builder;
import lombok.NonNull;
import lombok.Value;

import java.io.Serializable;

@Builder
@Value
@JsonDeserialize(builder = SendMessageDto.SendMessageDtoBuilder.class)
public class SendMessageDto  implements Serializable {
    //TODO Get the user from the headers or the session
    @NonNull
    String senderId;
    @NonNull
    String conversationId;
    @NonNull
    String content;

    @JsonPOJOBuilder(withPrefix = "")
    public static class SendMessageDtoBuilder {
        // Lombok will add constructor, setters, build method
    }
}
