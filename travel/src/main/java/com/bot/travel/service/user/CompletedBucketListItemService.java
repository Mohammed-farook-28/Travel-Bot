// com.bot.travel.service.user.CompletedBucketListItemService.java
package com.bot.travel.service.user;

import com.bot.travel.model.user.CompletedBucketListItem;
import com.bot.travel.repository.user.CompletedBucketListItemRepository;
import com.bot.travel.service.audit.AuditLoggerService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jakarta.validation.Valid;
import java.time.Instant;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.locks.ReentrantLock;


@Service
public class CompletedBucketListItemService {

    private final CompletedBucketListItemRepository completedBucketListItemRepository;
    private final AuditLoggerService auditLoggerService;
    private final ReentrantLock lock = new ReentrantLock();

    public CompletedBucketListItemService(CompletedBucketListItemRepository completedBucketListItemRepository,
                                          AuditLoggerService auditLoggerService) {
        this.completedBucketListItemRepository = completedBucketListItemRepository;
        this.auditLoggerService = auditLoggerService;
    }

    public List<CompletedBucketListItem> getAllCompletedBucketListItems() {
        return completedBucketListItemRepository.findAllByIsDeletedFalse();
    }

    public Optional<CompletedBucketListItem> getCompletedBucketListItemById(String id) {
        return completedBucketListItemRepository.findByIdAndIsDeletedFalse(id);
    }

    @Transactional
    public CompletedBucketListItem saveCompletedBucketListItem(@Valid CompletedBucketListItem item) {
        item.setCreatedAt(Instant.now());
        item.setUpdatedAt(Instant.now());
        CompletedBucketListItem savedItem = completedBucketListItemRepository.save(item);

        // 🔍 **Audit Log**
        Map<String, Object> changes = new HashMap<>();
        changes.put("bucketListItemId", item.getBucketListItemId());
        changes.put("countryId", item.getCountryId());
        auditLoggerService.logEvent("CompletedBucketListItem", "CREATE", item.getUserId(), changes, "SYSTEM");

        return savedItem;
    }

    @Transactional
    public CompletedBucketListItem updateCompletedBucketListItem(String id, @Valid CompletedBucketListItem item) {
        lock.lock();
        try {
            return completedBucketListItemRepository.findByIdAndIsDeletedFalse(id)
                .map(existingItem -> {
                    item.setId(id);
                    item.setUpdatedAt(Instant.now());
                    CompletedBucketListItem updatedItem = completedBucketListItemRepository.save(item);

                    // 🔍 **Audit Log**
                    Map<String, Object> changes = new HashMap<>();
                    changes.put("bucketListItemId", item.getBucketListItemId());
                    changes.put("countryId", item.getCountryId());
                    auditLoggerService.logEvent("CompletedBucketListItem", "UPDATE", item.getUserId(), changes, "SYSTEM");

                    return updatedItem;
                })
                .orElseThrow(() -> new RuntimeException("Bucket List Item not found with id: " + id));
        } finally {
            lock.unlock();
        }
    }

    @Transactional
    public void softDeleteCompletedBucketListItem(String id, String deletedBy) {
        completedBucketListItemRepository.findById(id).ifPresent(item -> {
            item.setIsDeleted(true);
            item.setDeletedBy(deletedBy);
            item.setDeletedAt(Instant.now());
            completedBucketListItemRepository.save(item);

            // 🔍 **Audit Log**
            Map<String, Object> changes = new HashMap<>();
            changes.put("isDeleted", true);
            auditLoggerService.logEvent("CompletedBucketListItem", "DELETE", item.getUserId(), changes, deletedBy);
        });
    }

    @Transactional
    public void restoreCompletedBucketListItem(String id) {
        completedBucketListItemRepository.findById(id).ifPresent(item -> {
            item.setIsDeleted(false);
            item.setDeletedBy(null);
            item.setDeletedAt(null);
            completedBucketListItemRepository.save(item);

            Map<String, Object> changes = new HashMap<>();
            changes.put("isDeleted", false);
            auditLoggerService.logEvent("CompletedBucketListItem", "RESTORE", item.getUserId(), changes, "SYSTEM");
        });
    }
}
