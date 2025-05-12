// com.bot.travel.repository.user.CompletedBucketListItemRepository.java
package com.bot.travel.repository.user;

import com.bot.travel.model.user.CompletedBucketListItem;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CompletedBucketListItemRepository extends MongoRepository<CompletedBucketListItem, String> {
    List<CompletedBucketListItem> findByUserIdAndIsDeletedFalse(String userId);
    Optional<CompletedBucketListItem> findByUserIdAndBucketListItemIdAndIsDeletedFalse(String userId, String bucketListItemId);
    List<CompletedBucketListItem> findAllByIsDeletedFalse();
}
