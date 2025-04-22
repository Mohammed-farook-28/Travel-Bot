package com.bot.travel.repository.post;

import com.bot.travel.model.post.Itinerary;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ItineraryRepository extends MongoRepository<Itinerary, String> {
    List<Itinerary> findByTravelType(String travelType);
    List<Itinerary> findByTravelStyle(String travelStyle);
    List<Itinerary> findByDifficulty(String difficulty);
    List<Itinerary> findByDurationDaysLessThan(Integer days);
}