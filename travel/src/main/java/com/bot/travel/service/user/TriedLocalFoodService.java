// com.bot.travel.service.user.TriedLocalFoodService.java
package com.bot.travel.service.user;

import com.bot.travel.model.user.TriedLocalFood;
import com.bot.travel.repository.user.TriedLocalFoodRepository;
import com.bot.travel.service.audit.AuditLoggerService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jakarta.validation.Valid;
import java.time.Instant;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class TriedLocalFoodService {

    private final TriedLocalFoodRepository triedLocalFoodRepository;
    private final AuditLoggerService auditLoggerService;

    public TriedLocalFoodService(TriedLocalFoodRepository triedLocalFoodRepository, AuditLoggerService auditLoggerService) {
        this.triedLocalFoodRepository = triedLocalFoodRepository;
        this.auditLoggerService = auditLoggerService;
    }

    public List<TriedLocalFood> getAllTriedLocalFoods() {
        return triedLocalFoodRepository.findAllByIsDeletedFalse();
    }

    public Optional<TriedLocalFood> getTriedLocalFoodById(String id) {
        return triedLocalFoodRepository.findByIdAndIsDeletedFalse(id);
    }

    @Transactional
    public TriedLocalFood saveTriedLocalFood(@Valid TriedLocalFood triedLocalFood) {
        triedLocalFood.setCreatedAt(Instant.now());
        triedLocalFood.setUpdatedAt(Instant.now());
        TriedLocalFood savedFood = triedLocalFoodRepository.save(triedLocalFood);

        Map<String, Object> changes = new HashMap<>();
        changes.put("foodId", triedLocalFood.getFoodId());
        changes.put("countryId", triedLocalFood.getCountryId());
        auditLoggerService.logEvent("TriedLocalFood", "CREATE", triedLocalFood.getUserId(), changes, "SYSTEM");

        return savedFood;
    }

    @Transactional
    public void softDeleteTriedLocalFood(String id, String deletedBy) {
        triedLocalFoodRepository.findById(id).ifPresent(triedLocalFood -> {
            triedLocalFood.setIsDeleted(true);
            triedLocalFood.setDeletedBy(deletedBy);
            triedLocalFood.setDeletedAt(Instant.now());
            triedLocalFoodRepository.save(triedLocalFood);

            Map<String, Object> changes = new HashMap<>();
            changes.put("isDeleted", true);
            auditLoggerService.logEvent("TriedLocalFood", "DELETE", triedLocalFood.getUserId(), changes, deletedBy);
        });
    }

    @Transactional
    public void restoreTriedLocalFood(String id) {
        triedLocalFoodRepository.findById(id).ifPresent(triedLocalFood -> {
            triedLocalFood.setIsDeleted(false);
            triedLocalFood.setDeletedBy(null);
            triedLocalFood.setDeletedAt(null);
            triedLocalFoodRepository.save(triedLocalFood);

            Map<String, Object> changes = new HashMap<>();
            changes.put("isDeleted", false);
            auditLoggerService.logEvent("TriedLocalFood", "RESTORE", triedLocalFood.getUserId(), changes, "SYSTEM");
        });
    }
}
