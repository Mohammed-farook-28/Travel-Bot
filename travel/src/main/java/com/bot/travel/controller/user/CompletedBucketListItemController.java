// com.bot.travel.controller.user.CompletedBucketListItemController.java
package com.bot.travel.controller.user;

import com.bot.travel.model.user.CompletedBucketListItem;
import com.bot.travel.service.user.CompletedBucketListItemService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/completed-bucket-list")
public class CompletedBucketListItemController {

    private final CompletedBucketListItemService completedBucketListItemService;

    public CompletedBucketListItemController(CompletedBucketListItemService completedBucketListItemService) {
        this.completedBucketListItemService = completedBucketListItemService;
    }

    @GetMapping
    public ResponseEntity<List<CompletedBucketListItem>> getAllCompletedBucketListItems() {
        return ResponseEntity.ok(completedBucketListItemService.getAllCompletedBucketListItems());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CompletedBucketListItem> getCompletedBucketListItemById(@PathVariable String id) {
        return completedBucketListItemService.getCompletedBucketListItemById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> softDeleteCompletedBucketListItem(@PathVariable String id, @RequestParam String deletedBy) {
        completedBucketListItemService.softDeleteCompletedBucketListItem(id, deletedBy);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}/restore")
    public ResponseEntity<Void> restoreCompletedBucketListItem(@PathVariable String id) {
        completedBucketListItemService.restoreCompletedBucketListItem(id);
        return ResponseEntity.noContent().build();
    }
}
