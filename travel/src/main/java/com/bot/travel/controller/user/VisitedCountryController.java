// com.bot.travel.controller.user.VisitedCountryController.java
package com.bot.travel.controller.user;

import com.bot.travel.model.user.VisitedCountry;
import com.bot.travel.service.user.VisitedCountryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/visited-countries")
public class VisitedCountryController {

    private final VisitedCountryService visitedCountryService;

    public VisitedCountryController(VisitedCountryService visitedCountryService) {
        this.visitedCountryService = visitedCountryService;
    }

    @GetMapping
    public ResponseEntity<List<VisitedCountry>> getAllVisitedCountries() {
        return ResponseEntity.ok(visitedCountryService.getAllVisitedCountries());
    }

    @GetMapping("/{id}")
    public ResponseEntity<VisitedCountry> getVisitedCountryById(@PathVariable String id) {
        return visitedCountryService.getVisitedCountryById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> softDeleteVisitedCountry(@PathVariable String id, @RequestParam String deletedBy) {
        visitedCountryService.softDeleteVisitedCountry(id, deletedBy);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}/restore")
    public ResponseEntity<Void> restoreVisitedCountry(@PathVariable String id) {
        visitedCountryService.restoreVisitedCountry(id);
        return ResponseEntity.noContent().build();
    }
}
