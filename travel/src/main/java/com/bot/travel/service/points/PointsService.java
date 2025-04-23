package com.bot.travel.service.points;

import com.bot.travel.model.points.PointHistory;
import com.bot.travel.model.points.UserPoints;
import com.bot.travel.model.user.User;
import com.bot.travel.repository.points.PointHistoryRepository;
import com.bot.travel.repository.points.UserPointsRepository;
import com.bot.travel.repository.user.UserRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class PointsService {

    private final PointHistoryRepository pointHistoryRepository;
    private final UserPointsRepository userPointsRepository;
    private final UserRepository userRepository;

    public PointsService(PointHistoryRepository pointHistoryRepository, 
                       UserPointsRepository userPointsRepository,
                       UserRepository userRepository) {
        this.pointHistoryRepository = pointHistoryRepository;
        this.userPointsRepository = userPointsRepository;
        this.userRepository = userRepository;
    }

    @Transactional
    public void awardPoints(String userId, String actionType, int points, 
                          String countryId, String itemId) {
        // Record point history
        PointHistory pointHistory = new PointHistory();
        pointHistory.setUserId(userId);
        pointHistory.setActionType(actionType);
        pointHistory.setPoints(points);
        pointHistory.setCountryId(countryId);
        pointHistory.setItemId(itemId);
        pointHistory.setTimestamp(new Date());
        
        pointHistoryRepository.save(pointHistory);
        
        // Update user's total points
        UserPoints userPoints = userPointsRepository.findByUserId(userId)
            .orElse(new UserPoints(userId, 0));
        
        userPoints.setTotalPoints(userPoints.getTotalPoints() + points);
        userPointsRepository.save(userPoints);
        
        // Update the user's totalPoints field as well for quick access
        Optional<User> userOpt = userRepository.findById(userId);
        if (userOpt.isPresent()) {
            User user = userOpt.get();
            user.setTotalPoints(user.getTotalPoints() + points);
            userRepository.save(user);
        }
    }
    
    public List<PointHistory> getUserPointsHistory(String userId) {
        return pointHistoryRepository.findByUserId(userId);
    }
    
    public Optional<UserPoints> getUserPoints(String userId) {
        return userPointsRepository.findByUserId(userId);
    }
    
    public List<UserPoints> getLeaderboard(int limit) {
        Pageable pageable = PageRequest.of(0, limit);
        return userPointsRepository.findByOrderByTotalPointsDesc(pageable);
    }
    
    public List<PointHistory> getPointsHistoryByActionType(String userId, String actionType) {
        return pointHistoryRepository.findByUserIdAndActionType(userId, actionType);
    }
    
    public List<PointHistory> getPointsHistoryByTimestampBetween(String userId, Date startDate, Date endDate) {
        return pointHistoryRepository.findByUserIdAndTimestampBetween(userId, startDate, endDate);
    }
    
    public List<PointHistory> getPointsHistoryByCountry(String userId, String countryId) {
        return pointHistoryRepository.findByUserIdAndCountryId(userId, countryId);
    }
    
    public int getTotalPointsByCountry(String userId, String countryId) {
        List<PointHistory> pointHistory = pointHistoryRepository.findByUserIdAndCountryId(userId, countryId);
        return pointHistory.stream().mapToInt(PointHistory::getPoints).sum();
    }
}