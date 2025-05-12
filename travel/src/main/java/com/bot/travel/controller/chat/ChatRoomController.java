package com.bot.travel.controller.chat;

import com.bot.travel.model.chat.ChatRoom;
import com.bot.travel.service.chat.ChatRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/chatrooms")
public class ChatRoomController {

    @Autowired
    private ChatRoomService chatRoomService;

    @PostMapping
    public ChatRoom createChatRoom(@RequestBody ChatRoom chatRoom) {
        return chatRoomService.createChatRoom(chatRoom);
    }

    @GetMapping
    public List<ChatRoom> getAllChatRooms() {
        return chatRoomService.getAllChatRooms();
    }

    @GetMapping("/{id}")
    public Optional<ChatRoom> getChatRoomById(@PathVariable String id) {
        return chatRoomService.getChatRoomById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteChatRoom(@PathVariable String id) {
        chatRoomService.deleteChatRoom(id);
    }
}
