// com.bot.travel.service.user.VisitedCountryService.java
package com.bot.travel.service.user;

import com.bot.travel.model.user.VisitedCountry;
import com.bot.travel.repository.user.VisitedCountryRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jakarta.validation.Valid;
import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.locks.ReentrantLock;

@Service
public class VisitedCountryService {

    private final VisitedCountryRepository visitedCountryRepository;
    private final ReentrantLock lock = new ReentrantLock();

    public VisitedCountryService(VisitedCountryRepository visitedCountryRepository) {
        this.visitedCountryRepository = visitedCountryRepository;
    }

    public List<VisitedCountry> getAllVisitedCountries() {
        return visitedCountryRepository.findAllByIsDeletedFalse();
    }

    public Optional<VisitedCountry> getVisitedCountryById(String id) {
        return visitedCountryRepository.findById(id)
                .filter(visitedCountry -> !visitedCountry.getIsDeleted());
    }

    @Transactional
    public VisitedCountry saveVisitedCountry(@Valid VisitedCountry visitedCountry) {
        visitedCountry.setCreatedAt(Instant.now());
        visitedCountry.setUpdatedAt(Instant.now());
        return visitedCountryRepository.save(visitedCountry);
    }

    @Transactional
    public VisitedCountry updateVisitedCountry(String id, @Valid VisitedCountry visitedCountry) {
        return visitedCountryRepository.findById(id)
            .map(existingCountry -> {
                visitedCountry.setId(id);
                visitedCountry.setUpdatedAt(Instant.now());
                return visitedCountryRepository.save(visitedCountry);
            })
            .orElseThrow(() -> new RuntimeException("Visited Country not found with id: " + id));
    }

    @Transactional
    public void softDeleteVisitedCountry(String id, String deletedBy) {
        visitedCountryRepository.findById(id).ifPresent(visitedCountry -> {
            visitedCountry.setIsDeleted(true);
            visitedCountry.setDeletedBy(deletedBy);
            visitedCountry.setDeletedAt(Instant.now());
            visitedCountryRepository.save(visitedCountry);
        });
    }

    @Transactional
    public void restoreVisitedCountry(String id) {
        visitedCountryRepository.findById(id).ifPresent(visitedCountry -> {
            visitedCountry.setIsDeleted(false);
            visitedCountry.setDeletedBy(null);
            visitedCountry.setDeletedAt(null);
            visitedCountryRepository.save(visitedCountry);
        });
    }
}
