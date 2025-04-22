package com.bot.travel.repository.post;

import com.bot.travel.model.post.Expense;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ExpenseRepository extends MongoRepository<Expense, String> {
    List<Expense> findByCategory(String category);
    List<Expense> findByAmountGreaterThan(Double amount);
    List<Expense> findByCurrency(String currency);
}