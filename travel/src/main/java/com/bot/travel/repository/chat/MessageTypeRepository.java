package com.bot.travel.repository.chat;

import com.bot.travel.model.chat.MessageType;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface MessageTypeRepository extends MongoRepository<MessageType, String> {
    List<MessageType> findAll();
}