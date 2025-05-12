package com.bot.travel.service.geography;

import com.bot.travel.model.geography.Continent;
import com.bot.travel.repository.geography.ContinentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ContinentService {

    @Autowired
    private final ContinentRepository continentRepository;

    @Transactional(readOnly = true)
    public List<Continent> getAllContinents() {
        return continentRepository.findAllByIsDeletedFalse();
    }

    @Transactional(readOnly = true)
    public Optional<Continent> getContinentByName(String name) {
        return continentRepository.findByNameAndIsDeletedFalse(name);
    }

    @Transactional
    public Continent createContinent(Continent continent) {
        continent.setCreatedAt(Instant.now());
        return continentRepository.save(continent);
    }

    @Transactional
    public void deleteContinent(String id, String deletedBy) {
        continentRepository.findById(id).ifPresent(continent -> {
            continent.setIsDeleted(true);
            continent.setDeletedBy(deletedBy);
            continent.setDeletedAt(Instant.now());
            continentRepository.save(continent);
        });
    }

    @Transactional
    public Continent updateContinent(String id, Continent updatedData) {
        return continentRepository.findById(id).map(continent -> {
            continent.setName(updatedData.getName());
            continent.setDescription(updatedData.getDescription());
            continent.setImageUrl(updatedData.getImageUrl());
            continent.setUpdatedAt(Instant.now());
            return continentRepository.save(continent);
        }).orElseThrow(() -> new IllegalArgumentException("Continent not found"));
    }
}
