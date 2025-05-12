// com.bot.travel.service.user.TriedLocalFoodService.java
package com.bot.travel.service.user;

import com.bot.travel.model.user.TriedLocalFood;
import com.bot.travel.repository.user.TriedLocalFoodRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jakarta.validation.Valid;
import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.locks.ReentrantLock;

@Service
public class TriedLocalFoodService {

    private final TriedLocalFoodRepository triedLocalFoodRepository;
    private final ReentrantLock lock = new ReentrantLock();

    public TriedLocalFoodService(TriedLocalFoodRepository triedLocalFoodRepository) {
        this.triedLocalFoodRepository = triedLocalFoodRepository;
    }

    public List<TriedLocalFood> getAllTriedLocalFoods() {
        return triedLocalFoodRepository.findAllByIsDeletedFalse();
    }

    public Optional<TriedLocalFood> getTriedLocalFoodById(String id) {
        return triedLocalFoodRepository.findById(id)
                .filter(triedLocalFood -> !triedLocalFood.getIsDeleted());
    }

    @Transactional
    public TriedLocalFood saveTriedLocalFood(@Valid TriedLocalFood triedLocalFood) {
        triedLocalFood.setCreatedAt(Instant.now());
        triedLocalFood.setUpdatedAt(Instant.now());
        return triedLocalFoodRepository.save(triedLocalFood);
    }

    @Transactional
    public TriedLocalFood updateTriedLocalFood(String id, @Valid TriedLocalFood triedLocalFood) {
        return triedLocalFoodRepository.findById(id)
            .map(existingFood -> {
                triedLocalFood.setId(id);
                triedLocalFood.setUpdatedAt(Instant.now());
                return triedLocalFoodRepository.save(triedLocalFood);
            })
            .orElseThrow(() -> new RuntimeException("Local Food not found with id: " + id));
    }

    @Transactional
    public void softDeleteTriedLocalFood(String id, String deletedBy) {
        triedLocalFoodRepository.findById(id).ifPresent(triedLocalFood -> {
            triedLocalFood.setIsDeleted(true);
            triedLocalFood.setDeletedBy(deletedBy);
            triedLocalFood.setDeletedAt(Instant.now());
            triedLocalFoodRepository.save(triedLocalFood);
        });
    }

    @Transactional
    public void restoreTriedLocalFood(String id) {
        triedLocalFoodRepository.findById(id).ifPresent(triedLocalFood -> {
            triedLocalFood.setIsDeleted(false);
            triedLocalFood.setDeletedBy(null);
            triedLocalFood.setDeletedAt(null);
            triedLocalFoodRepository.save(triedLocalFood);
        });
    }
}
