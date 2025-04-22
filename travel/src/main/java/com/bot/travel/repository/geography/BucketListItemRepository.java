package com.bot.travel.repository.geography;

import com.bot.travel.model.geography.BucketListItem;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface BucketListItemRepository extends MongoRepository<BucketListItem, String> {
    List<BucketListItem> findByPointsAwardedGreaterThan(Integer points);
    List<BucketListItem> findByLocationContaining(String location);
}