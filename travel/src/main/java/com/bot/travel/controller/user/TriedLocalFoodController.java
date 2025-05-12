// com.bot.travel.controller.user.TriedLocalFoodController.java
package com.bot.travel.controller.user;

import com.bot.travel.model.user.TriedLocalFood;
import com.bot.travel.service.user.TriedLocalFoodService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tried-local-foods")
public class TriedLocalFoodController {

    private final TriedLocalFoodService triedLocalFoodService;

    public TriedLocalFoodController(TriedLocalFoodService triedLocalFoodService) {
        this.triedLocalFoodService = triedLocalFoodService;
    }

    @GetMapping
    public ResponseEntity<List<TriedLocalFood>> getAllTriedLocalFoods() {
        return ResponseEntity.ok(triedLocalFoodService.getAllTriedLocalFoods());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TriedLocalFood> getTriedLocalFoodById(@PathVariable String id) {
        return triedLocalFoodService.getTriedLocalFoodById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> softDeleteTriedLocalFood(@PathVariable String id, @RequestParam String deletedBy) {
        triedLocalFoodService.softDeleteTriedLocalFood(id, deletedBy);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}/restore")
    public ResponseEntity<Void> restoreTriedLocalFood(@PathVariable String id) {
        triedLocalFoodService.restoreTriedLocalFood(id);
        return ResponseEntity.noContent().build();
    }
}
