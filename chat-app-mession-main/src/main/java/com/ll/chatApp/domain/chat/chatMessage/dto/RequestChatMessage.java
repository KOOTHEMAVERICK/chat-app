package com.ll.chatApp.domain.chat.chatMessage.dto;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class RequestChatMessage {
    private String writeName;
    private String content;
}
