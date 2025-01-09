package com.ll.chatApp.domain.chat.chatMessage.service;

import com.ll.chatApp.domain.chat.chatMessage.entity.ChatMessage;
import com.ll.chatApp.domain.chat.chatMessage.repository.ChatMessageRepository;
import com.ll.chatApp.domain.chat.chatRoom.entity.ChatRoom;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ChatMessageService {
    private final ChatMessageRepository chatMessageRepository;

    public ChatMessage create(ChatRoom chatRoom, String writerName, String content) {
        ChatMessage chatMessage = ChatMessage.builder()
                .chatRoom(chatRoom)
                .writerName(writerName)
                .content(content)
                .build();

        chatMessageRepository.save(chatMessage);

        return chatMessage;
    }

    public List<ChatMessage> getById(Iterable<Long> id){
        return chatMessageRepository.findAllById(id);
    }
}
