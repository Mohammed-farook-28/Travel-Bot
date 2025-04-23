package com.bot.travel.repository.points;

import com.bot.travel.model.points.PointHistory;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface PointHistoryRepository extends MongoRepository<PointHistory, String> {
    List<PointHistory> findByUserId(String userId);
    List<PointHistory> findByActionType(String actionType);
    List<PointHistory> findByUserIdAndActionType(String userId, String actionType);
    List<PointHistory> findByUserIdAndTimestampBetween(String userId, Date startDate, Date endDate);
    List<PointHistory> findByUserIdAndCountryId(String userId, String countryId);
    List<PointHistory> findByCountryId(String countryId);
    List<PointHistory> findByItemId(String itemId);
}