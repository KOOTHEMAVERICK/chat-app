package com.ll.chatApp.domain.chat.chatMessage.controller;

import com.ll.chatApp.domain.chat.chatMessage.dto.RequestChatMessage;
import com.ll.chatApp.domain.chat.chatMessage.entity.ChatMessage;
import com.ll.chatApp.domain.chat.chatMessage.service.ChatMessageService;
import com.ll.chatApp.domain.chat.chatRoom.entity.ChatRoom;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class ApiV1ChatMessageController {
    private final ChatMessageService chatMessageService;

    public ApiV1ChatMessageController(ChatMessageService chatMessageService) {
        this.chatMessageService = chatMessageService;
    }

    @GetMapping("/api/v1/chat/rooms/{roomId}/messages")
    public List<ChatMessage> getChatMessages(@PathVariable("roomId") Iterable<Long> roomId) {
        List<ChatMessage> ChatMessages = chatMessageService.getById(roomId);
        return  ChatMessages;
    }

    @PostMapping("/api/v1/chat/rooms/{roomId}/messages")
    public ChatMessage createChatMessage(ChatRoom chatRoom, @RequestBody RequestChatMessage requestChatMessage) {
        return chatMessageService.create(chatRoom, requestChatMessage.getWriteName(), requestChatMessage.getContent());
    }

}
