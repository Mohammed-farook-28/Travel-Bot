package com.bot.travel.repository.social;

import com.bot.travel.model.social.Friendship;
import com.bot.travel.model.social.FriendshipStatus;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface FriendshipRepository extends MongoRepository<Friendship, String> {
    // Find friendships by user
    List<Friendship> findByRequester(String userId);
    List<Friendship> findByRecipient(String userId);

    // Find friendships by status
    List<Friendship> findByStatus(FriendshipStatus status);
    
    // Find specific friendship between two users
    Optional<Friendship> findByRequesterAndRecipient(String requesterId, String recipientId);

    // Find friendships with specific status for a user
    List<Friendship> findByRequesterAndStatus(String userId, FriendshipStatus status);
    List<Friendship> findByRecipientAndStatus(String userId, FriendshipStatus status);

    // Count friendships
    Long countByRequesterAndStatus(String userId, FriendshipStatus status);
    Long countByRecipientAndStatus(String userId, FriendshipStatus status);
}