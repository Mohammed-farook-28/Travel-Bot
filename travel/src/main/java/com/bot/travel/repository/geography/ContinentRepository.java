package com.bot.travel.repository.geography;

import com.bot.travel.model.geography.Continent;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface ContinentRepository extends MongoRepository<Continent, String> {
    Optional<Continent> findByName(String name);
}