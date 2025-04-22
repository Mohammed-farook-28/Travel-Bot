package com.bot.travel.repository.geography;

import com.bot.travel.model.geography.Country;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface CountryRepository extends MongoRepository<Country, String> {
    Optional<Country> findByName(String name);
    List<Country> findByContinentId(String continentId);
    List<Country> findByLanguagesContaining(String language);
}