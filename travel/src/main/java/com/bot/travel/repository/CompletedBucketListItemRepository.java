package com.bot.travel.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.bot.travel.model.user.CompletedBucketListItem;

import java.util.List;
import java.util.Date;

@Repository
public interface CompletedBucketListItemRepository extends MongoRepository<CompletedBucketListItem, String> {
    List<CompletedBucketListItem> findByCountryId(String countryId);
    List<CompletedBucketListItem> findByCompletionDateBetween(Date startDate, Date endDate);
    List<CompletedBucketListItem> findByApproved(Boolean approved);
}