package com.bot.travel.service.geography;

import com.bot.travel.model.geography.Country;
import com.bot.travel.repository.geography.CountryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CountryService {

    @Autowired
    private final CountryRepository countryRepository;

    @Transactional(readOnly = true)
    public List<Country> getAllCountries() {
        return countryRepository.findAllByIsDeletedFalse();
    }

    @Transactional(readOnly = true)
    public Optional<Country> getCountryByName(String name) {
        return countryRepository.findByNameAndIsDeletedFalse(name);
    }

    @Transactional(readOnly = true)
    public List<Country> getCountriesByContinent(String continentId) {
        return countryRepository.findAllByContinentIdAndIsDeletedFalse(continentId);
    }

    @Transactional
    public Country createCountry(Country country) {
        country.setCreatedAt(Instant.now());
        return countryRepository.save(country);
    }

    @Transactional
    public void deleteCountry(String id, String deletedBy) {
        countryRepository.findById(id).ifPresent(country -> {
            country.setIsDeleted(true);
            country.setDeletedBy(deletedBy);
            country.setDeletedAt(Instant.now());
            countryRepository.save(country);
        });
    }

    @Transactional
    public Country updateCountry(String id, Country updatedData) {
        return countryRepository.findById(id).map(country -> {
            country.setName(updatedData.getName());
            country.setFlagUrl(updatedData.getFlagUrl());
            country.setDescription(updatedData.getDescription());
            country.setVisaLink(updatedData.getVisaLink());
            country.setMobileCode(updatedData.getMobileCode());
            country.setLanguages(updatedData.getLanguages());
            country.setCurrency(updatedData.getCurrency());
            country.setTimezone(updatedData.getTimezone());
            country.setCapital(updatedData.getCapital());
            country.setUpdatedAt(Instant.now());
            return countryRepository.save(country);
        }).orElseThrow(() -> new IllegalArgumentException("Country not found"));
    }
}
