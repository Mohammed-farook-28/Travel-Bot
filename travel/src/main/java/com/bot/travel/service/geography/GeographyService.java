package com.bot.travel.service.geography;

import com.bot.travel.model.geography.Continent;
import com.bot.travel.model.geography.Country;
import com.bot.travel.repository.geography.ContinentRepository;
import com.bot.travel.repository.geography.CountryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GeographyService {

    private final ContinentRepository continentRepository;
    private final CountryRepository countryRepository;

    public GeographyService(ContinentRepository continentRepository, CountryRepository countryRepository) {
        this.continentRepository = continentRepository;
        this.countryRepository = countryRepository;
    }

    public List<Continent> getAllContinents() {
        return continentRepository.findAll();
    }

    public Optional<Continent> getContinentById(String id) {
        return continentRepository.findById(id);
    }

    public Continent saveContinent(Continent continent) {
        return continentRepository.save(continent);
    }

    public void deleteContinent(String id) {
        continentRepository.deleteById(id);
    }

    public List<Country> getAllCountries() {
        return countryRepository.findAll();
    }

    public Optional<Country> getCountryById(String id) {
        return countryRepository.findById(id);
    }

    public List<Country> getCountriesByContinent(String continentId) {
        return countryRepository.findByContinentId(continentId);
    }

    public List<Country> searchCountriesByName(String name) {
        return countryRepository.findByNameContainingIgnoreCase(name);
    }

    public Country saveCountry(Country country) {
        return countryRepository.save(country);
    }

    public void deleteCountry(String id) {
        countryRepository.deleteById(id);
    }
}