package com.bot.travel.controller.chat;

import com.bot.travel.model.chat.Message;
import com.bot.travel.service.chat.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/messages")
public class MessageController {

    @Autowired
    private MessageService messageService;

    @PostMapping
    public Message saveMessage(@RequestBody Message message) {
        return messageService.saveMessage(message);
    }

    @GetMapping("/room/{roomId}")
    public List<Message> getMessagesByRoomId(@PathVariable String roomId) {
        return messageService.getMessagesByRoomId(roomId);
    }

    @DeleteMapping("/{id}")
    public void deleteMessage(@PathVariable String id) {
        messageService.deleteMessage(id);
    }
}
