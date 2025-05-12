package com.bot.travel.repository.geography;

import com.bot.travel.model.geography.Country;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CountryRepository extends MongoRepository<Country, String> {
    Optional<Country> findByNameAndIsDeletedFalse(String name);
    List<Country> findAllByContinentIdAndIsDeletedFalse(String continentId);
    List<Country> findAllByIsDeletedFalse();
}
