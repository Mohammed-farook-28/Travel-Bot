package com.bot.travel.repository.points;

import com.bot.travel.model.points.PointHistory;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Date;

@Repository
public interface PointHistoryRepository extends MongoRepository<PointHistory, String> {
    List<PointHistory> findByActionType(String actionType);
    List<PointHistory> findByTimestampBetween(Date startDate, Date endDate);
    List<PointHistory> findByCountryId(String countryId);
    List<PointHistory> findByItemId(String itemId);
}