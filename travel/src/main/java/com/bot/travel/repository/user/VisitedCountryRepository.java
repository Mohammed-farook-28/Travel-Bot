package com.bot.travel.repository.user;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.bot.travel.model.user.VisitedCountry;

import java.util.List;
import java.util.Date;

@Repository
public interface VisitedCountryRepository extends MongoRepository<VisitedCountry, String> {
    List<VisitedCountry> findByCountryId(String countryId);
    List<VisitedCountry> findByVisitDateBetween(Date startDate, Date endDate);
}