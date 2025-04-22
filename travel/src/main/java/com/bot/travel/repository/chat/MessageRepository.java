package com.bot.travel.repository.chat;

import com.bot.travel.model.chat.Message;
import com.bot.travel.model.chat.MessageType;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Date;

@Repository
public interface MessageRepository extends MongoRepository<Message, String> {
    List<Message> findByRoomId(String roomId);
    List<Message> findBySenderId(String senderId);
    List<Message> findByCreatedAtBetween(Date startDate, Date endDate);
    List<Message> findByMessageType(MessageType messageType);
    Long countByRoomIdAndReadByNotContaining(String roomId, String userId);
    List<Message> findByMediaUrlIsNotNull();
    Long countByRoomId(String roomId);
}