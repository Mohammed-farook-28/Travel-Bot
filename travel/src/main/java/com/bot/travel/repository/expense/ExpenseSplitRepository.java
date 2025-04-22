package com.bot.travel.repository.expense;

import com.bot.travel.model.expense.ExpenseSplit;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Date;

@Repository
public interface ExpenseSplitRepository extends MongoRepository<ExpenseSplit, String> {

    List<ExpenseSplit> findByCreatedBy(String userId);
    List<ExpenseSplit> findByCreatedAtBetween(Date startDate, Date endDate);
    List<ExpenseSplit> findByCurrency(String currency);
    List<ExpenseSplit> findByTotalAmountGreaterThan(Double amount);
}