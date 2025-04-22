package com.bot.travel.service.user;

import com.bot.travel.model.user.User;
import com.bot.travel.model.user.VisitedCountry;
import com.bot.travel.repository.user.UserRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public Optional<User> getUserById(String id) {
        return userRepository.findById(id);
    }

    public Optional<User> getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public Optional<User> getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public User saveUser(User user) {
        return userRepository.save(user);
    }

    public void deleteUser(String id) {
        userRepository.deleteById(id);
    }

    public List<User> searchUsersByUsername(String username) {
        return userRepository.findByUsernameContainingIgnoreCase(username);
    }

    public boolean addVisitedCountry(String userId, String countryId) {
        Optional<User> userOpt = userRepository.findById(userId);
        if (userOpt.isPresent()) {
            User user = userOpt.get();
            boolean alreadyVisited = user.getVisitedCountries().stream()
                .anyMatch(vc -> vc.getCountryId().equals(countryId));
                
            if (!alreadyVisited) {
                VisitedCountry visitedCountry = new VisitedCountry();
                visitedCountry.setCountryId(countryId);
                visitedCountry.setVisitDate(new Date());
                
                user.getVisitedCountries().add(visitedCountry);
                userRepository.save(user);
                return true;
            }
        }
        return false;
    }

    public boolean removeVisitedCountry(String userId, String countryId) {
        Optional<User> userOpt = userRepository.findById(userId);
        if (userOpt.isPresent()) {
            User user = userOpt.get();
            boolean removed = user.getVisitedCountries().removeIf(vc -> vc.getCountryId().equals(countryId));
            if (removed) {
                userRepository.save(user);
                return true;
            }
        }
        return false;
    }

    public List<VisitedCountry> getVisitedCountries(String userId) {
        Optional<User> userOpt = userRepository.findById(userId);
        if (userOpt.isPresent()) {
            return userOpt.get().getVisitedCountries();
        }
        return new ArrayList<>();
    }
}