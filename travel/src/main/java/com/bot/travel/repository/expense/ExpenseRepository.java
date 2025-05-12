package com.bot.travel.repository.expense;

import com.bot.travel.model.expense.ExpenseSplit;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExpenseRepository extends MongoRepository<ExpenseSplit, String> {
    
    // Fetch all expenses linked to a specific ChatRoom
    List<ExpenseSplit> findByChatRoomId(String chatRoomId);
}
