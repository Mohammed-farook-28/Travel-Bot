package com.bot.travel.repository.chat;

import com.bot.travel.model.chat.ChatRoom;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Date;

@Repository
public interface ChatRoomRepository extends MongoRepository<ChatRoom, String> {

    List<ChatRoom> findByMembersContaining(String userId);
    List<ChatRoom> findByIsGroup(Boolean isGroup);
    List<ChatRoom> findByCreatedBy(String userId);
    List<ChatRoom> findByLastActivityAfter(Date date);
    Long countMembersById(String Id);
}