package com.bot.travel.repository.expense;

import com.bot.travel.model.expense.Participant;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ParticipantRepository extends MongoRepository<Participant, String> {
    List<Participant> findByUserId(String userId);
    List<Participant> findByIsPaidFalse();
    List<Participant> findByAmountOwedGreaterThan(Double amount);
    Long countByIsPaidFalse();
}