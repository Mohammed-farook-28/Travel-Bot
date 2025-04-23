package com.bot.travel.controller.points;

import com.bot.travel.model.points.PointHistory;
import com.bot.travel.model.points.UserPoints;
import com.bot.travel.service.points.PointsService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/points")
public class PointsController {

    private final PointsService pointsService;

    public PointsController(PointsService pointsService) {
        this.pointsService = pointsService;
    }

    @GetMapping("/users/{userId}")
    public ResponseEntity<UserPoints> getUserPoints(@PathVariable String userId) {
        return pointsService.getUserPoints(userId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/users/{userId}/history")
    public ResponseEntity<List<PointHistory>> getUserPointsHistory(@PathVariable String userId) {
        return ResponseEntity.ok(pointsService.getUserPointsHistory(userId));
    }

    @GetMapping("/leaderboard")
    public ResponseEntity<List<UserPoints>> getLeaderboard(@RequestParam(defaultValue = "10") int limit) {
        return ResponseEntity.ok(pointsService.getLeaderboard(limit));
    }

    @GetMapping("/users/{userId}/history/action-type/{actionType}")
    public ResponseEntity<List<PointHistory>> getPointsHistoryByActionType(
            @PathVariable String userId,
            @PathVariable String actionType) {
        return ResponseEntity.ok(pointsService.getPointsHistoryByActionType(userId, actionType));
    }

    @GetMapping("/users/{userId}/history/date-range")
    public ResponseEntity<List<PointHistory>> getPointsHistoryByDateRange(
            @PathVariable String userId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date startDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date endDate) {
        return ResponseEntity.ok(pointsService.getPointsHistoryByTimestampBetween(userId, startDate, endDate));
    }

    @GetMapping("/users/{userId}/history/country/{countryId}")
    public ResponseEntity<List<PointHistory>> getPointsHistoryByCountry(
            @PathVariable String userId,
            @PathVariable String countryId) {
        return ResponseEntity.ok(pointsService.getPointsHistoryByCountry(userId, countryId));
    }

    @GetMapping("/users/{userId}/total/country/{countryId}")
    public ResponseEntity<Integer> getTotalPointsByCountry(
            @PathVariable String userId,
            @PathVariable String countryId) {
        return ResponseEntity.ok(pointsService.getTotalPointsByCountry(userId, countryId));
    }
}