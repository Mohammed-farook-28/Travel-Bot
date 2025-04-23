package com.bot.travel.repository.social;

import com.bot.travel.model.social.Friendship;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FriendshipRepository extends MongoRepository<Friendship, String> {
    Optional<Friendship> findByRequesterAndRecipient(String requesterId, String recipientId);
    List<Friendship> findByRequesterAndStatus(String requesterId, String status);
    List<Friendship> findByRecipientAndStatus(String recipientId, String status);
}