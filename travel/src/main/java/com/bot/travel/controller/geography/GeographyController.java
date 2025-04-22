package com.bot.travel.controller.geography;

import com.bot.travel.model.geography.Continent;
import com.bot.travel.model.geography.Country;
import com.bot.travel.service.geography.GeographyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class GeographyController {

    private final GeographyService geographyService;

    @Autowired
    public GeographyController(GeographyService geographyService) {
        this.geographyService = geographyService;
    }

    @GetMapping("/continents")
    public ResponseEntity<List<Continent>> getAllContinents() {
        return ResponseEntity.ok(geographyService.getAllContinents());
    }

    @GetMapping("/continents/{id}")
    public ResponseEntity<Continent> getContinentById(@PathVariable String id) {
        return geographyService.getContinentById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    
    @PostMapping("/continents")
    public ResponseEntity<Continent> createContinent(@RequestBody Continent continent) {
        return new ResponseEntity<>(geographyService.saveContinent(continent), HttpStatus.CREATED);
    }
    
    @PutMapping("/continents/{id}")
    public ResponseEntity<Continent> updateContinent(@PathVariable String id, @RequestBody Continent continent) {
        return geographyService.getContinentById(id)
                .map(existingContinent -> {
                    continent.setId(id);
                    return ResponseEntity.ok(geographyService.saveContinent(continent));
                })
                .orElse(ResponseEntity.notFound().build());
    }
    
    @DeleteMapping("/continents/{id}")
    public ResponseEntity<Void> deleteContinent(@PathVariable String id) {
        return geographyService.getContinentById(id)
                .map(continent -> {
                    geographyService.deleteContinent(id);
                    return ResponseEntity.noContent().<Void>build();
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/countries")
    public ResponseEntity<List<Country>> getAllCountries() {
        return ResponseEntity.ok(geographyService.getAllCountries());
    }

    @GetMapping("/countries/{id}")
    public ResponseEntity<Country> getCountryById(@PathVariable String id) {
        return geographyService.getCountryById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/continents/{continentId}/countries")
    public ResponseEntity<List<Country>> getCountriesByContinent(@PathVariable String continentId) {
        return ResponseEntity.ok(geographyService.getCountriesByContinent(continentId));
    }

    @GetMapping("/countries/search")
    public ResponseEntity<List<Country>> searchCountries(@RequestParam String name) {
        return ResponseEntity.ok(geographyService.searchCountriesByName(name));
    }

    @PostMapping("/countries")
    public ResponseEntity<Country> createCountry(@RequestBody Country country) {
        return new ResponseEntity<>(geographyService.saveCountry(country), HttpStatus.CREATED);
    }

    @PutMapping("/countries/{id}")
    public ResponseEntity<Country> updateCountry(@PathVariable String id, @RequestBody Country country) {
        return geographyService.getCountryById(id)
                .map(existingCountry -> {
                    country.setId(id);
                    return ResponseEntity.ok(geographyService.saveCountry(country));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/countries/{id}")
    public ResponseEntity<Void> deleteCountry(@PathVariable String id) {
        return geographyService.getCountryById(id)
                .map(country -> {
                    geographyService.deleteCountry(id);
                    return ResponseEntity.noContent().<Void>build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
}