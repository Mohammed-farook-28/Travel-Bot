package com.bot.travel.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.bot.travel.model.user.TriedLocalFood;

import java.util.List;
import java.util.Date;

@Repository
public interface TriedLocalFoodRepository extends MongoRepository<TriedLocalFood, String> {
    List<TriedLocalFood> findByCountryId(String countryId);
    List<TriedLocalFood> findByTriedDateBetween(Date startDate, Date endDate);
}