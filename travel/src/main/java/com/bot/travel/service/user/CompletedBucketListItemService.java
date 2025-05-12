// com.bot.travel.service.user.CompletedBucketListItemService.java
package com.bot.travel.service.user;

import com.bot.travel.model.user.CompletedBucketListItem;
import com.bot.travel.repository.user.CompletedBucketListItemRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jakarta.validation.Valid;
import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.locks.ReentrantLock;

@Service
public class CompletedBucketListItemService {

    private final CompletedBucketListItemRepository completedBucketListItemRepository;
    private final ReentrantLock lock = new ReentrantLock();

    public CompletedBucketListItemService(CompletedBucketListItemRepository completedBucketListItemRepository) {
        this.completedBucketListItemRepository = completedBucketListItemRepository;
    }

    public List<CompletedBucketListItem> getAllCompletedBucketListItems() {
        return completedBucketListItemRepository.findAllByIsDeletedFalse();
    }

    public Optional<CompletedBucketListItem> getCompletedBucketListItemById(String id) {
        return completedBucketListItemRepository.findById(id)
                .filter(bucketListItem -> !bucketListItem.getIsDeleted());
    }

    @Transactional
    public CompletedBucketListItem saveCompletedBucketListItem(@Valid CompletedBucketListItem item) {
        item.setCreatedAt(Instant.now());
        item.setUpdatedAt(Instant.now());
        return completedBucketListItemRepository.save(item);
    }

    @Transactional
    public CompletedBucketListItem updateCompletedBucketListItem(String id, @Valid CompletedBucketListItem item) {
        return completedBucketListItemRepository.findById(id)
            .map(existingItem -> {
                item.setId(id);
                item.setUpdatedAt(Instant.now());
                return completedBucketListItemRepository.save(item);
            })
            .orElseThrow(() -> new RuntimeException("Bucket List Item not found with id: " + id));
    }

    @Transactional
    public void softDeleteCompletedBucketListItem(String id, String deletedBy) {
        completedBucketListItemRepository.findById(id).ifPresent(item -> {
            item.setIsDeleted(true);
            item.setDeletedBy(deletedBy);
            item.setDeletedAt(Instant.now());
            completedBucketListItemRepository.save(item);
        });
    }

    @Transactional
    public void restoreCompletedBucketListItem(String id) {
        completedBucketListItemRepository.findById(id).ifPresent(item -> {
            item.setIsDeleted(false);
            item.setDeletedBy(null);
            item.setDeletedAt(null);
            completedBucketListItemRepository.save(item);
        });
    }
}
