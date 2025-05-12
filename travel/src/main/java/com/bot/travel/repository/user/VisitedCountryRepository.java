// com.bot.travel.repository.user.VisitedCountryRepository.java
package com.bot.travel.repository.user;

import com.bot.travel.model.user.VisitedCountry;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface VisitedCountryRepository extends MongoRepository<VisitedCountry, String> {
    List<VisitedCountry> findByUserIdAndIsDeletedFalse(String userId);
    Optional<VisitedCountry> findByUserIdAndCountryIdAndIsDeletedFalse(String userId, String countryId);
    List<VisitedCountry> findAllByIsDeletedFalse();
}
