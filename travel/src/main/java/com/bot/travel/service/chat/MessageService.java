package com.bot.travel.service.chat;

import com.bot.travel.model.chat.Message;
import com.bot.travel.repository.chat.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageService {

    @Autowired
    private MessageRepository messageRepository;

    public Message saveMessage(Message message) {
        return messageRepository.save(message);
    }

    public List<Message> getMessagesByRoomId(String roomId) {
        return messageRepository.findByRoomId(roomId);
    }

    public void deleteMessage(String id) {
        messageRepository.deleteById(id);
    }
}
