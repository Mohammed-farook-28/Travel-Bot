package com.bot.travel.service.chat;

import com.bot.travel.model.chat.ChatRoom;
import com.bot.travel.repository.chat.ChatRoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ChatRoomService {

    @Autowired
    private ChatRoomRepository chatRoomRepository;

    public ChatRoom createChatRoom(ChatRoom chatRoom) {
        return chatRoomRepository.save(chatRoom);
    }

    public List<ChatRoom> getAllChatRooms() {
        return chatRoomRepository.findAll();
    }

    public Optional<ChatRoom> getChatRoomById(String id) {
        return chatRoomRepository.findById(id);
    }

    public void deleteChatRoom(String id) {
        chatRoomRepository.deleteById(id);
    }
}
