package com.ll.chatApp.domain.chat.chatRoom.controller;

import com.ll.chatApp.domain.chat.chatRoom.dto.RequestCreateRoom;
import com.ll.chatApp.domain.chat.chatRoom.entity.ChatRoom;
import com.ll.chatApp.domain.chat.chatRoom.service.ChatRoomService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/v1/chat/rooms")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
public class ApiV1ChatRoomController {
    private final ChatRoomService chatRoomService;

    @GetMapping
    public List<ChatRoom> getChatRooms(){
        List<ChatRoom> ChatRooms = chatRoomService.getAll();
        return ChatRooms;
    }

    @GetMapping("{roomId}")
    public ChatRoom getChatRoom(@PathVariable("roomId") Long roomId) {
        return chatRoomService.getById(roomId)
                .orElseThrow(() -> new RuntimeException("채팅방을 찾을 수 없습니다.")); // 예외 처리 추가
    }

    @PostMapping()
    public ChatRoom createChatRoom(@RequestBody RequestCreateRoom requestCreateRoom) {
        ChatRoom chatRoom =  chatRoomService.create(requestCreateRoom.getName());
        return chatRoom;
    }
}
