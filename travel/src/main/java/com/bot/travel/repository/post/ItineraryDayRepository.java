package com.bot.travel.repository.post;

import com.bot.travel.model.post.ItineraryDay;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ItineraryDayRepository extends MongoRepository<ItineraryDay, String> {
    List<ItineraryDay> findByTransportationMode(String transportationMode);
    List<ItineraryDay> findByAccommodationContaining(String accommodation);
}