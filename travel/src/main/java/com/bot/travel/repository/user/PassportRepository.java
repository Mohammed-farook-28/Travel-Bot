package com.bot.travel.repository.user;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.bot.travel.model.user.Passport;

import java.util.List;
import java.util.Date;

@Repository
public interface PassportRepository extends MongoRepository<Passport, String> {
    List<Passport> findByCountry(String country);
    List<Passport> findByExpiryDateBefore(Date date);
}