package com.bot.travel.controller.geography;

import com.bot.travel.model.geography.Continent;
import com.bot.travel.service.geography.ContinentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/continents")
@RequiredArgsConstructor
public class ContinentController {

    private final ContinentService continentService;

    @GetMapping
    public ResponseEntity<List<Continent>> getAllContinents() {
        List<Continent> continents = continentService.getAllContinents();
        return ResponseEntity.ok(continents);
    }

    @GetMapping("/{name}")
    public ResponseEntity<Continent> getContinentByName(@PathVariable String name) {
        Optional<Continent> continent = continentService.getContinentByName(name);
        return continent.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Continent> createContinent(@RequestBody Continent continent) {
        Continent created = continentService.createContinent(continent);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Continent> updateContinent(@PathVariable String id, @RequestBody Continent continent) {
        Continent updated = continentService.updateContinent(id, continent);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteContinent(@PathVariable String id, @RequestParam String deletedBy) {
        continentService.deleteContinent(id, deletedBy);
        return ResponseEntity.noContent().build();
    }
}

