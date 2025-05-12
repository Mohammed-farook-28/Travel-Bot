package com.bot.travel.service.expense;

import com.bot.travel.model.expense.ExpenseSettlement;
import com.bot.travel.model.expense.ExpenseSplit;
import com.bot.travel.repository.expense.ExpenseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ExpenseService {

    @Autowired
    private ExpenseRepository expenseRepository;

    public ExpenseSplit addExpense(ExpenseSplit expense) {
        return expenseRepository.save(expense);
    }

    public List<ExpenseSplit> getExpensesByChatRoomId(String chatRoomId) {
        return expenseRepository.findByChatRoomId(chatRoomId);
    }

    public void deleteExpense(String id) {
        expenseRepository.deleteById(id);
    }

    public ExpenseSplit updateExpense(String id, ExpenseSplit expense) {
        expense.setId(id);
        return expenseRepository.save(expense);
    }

    public List<ExpenseSettlement> calculateSettlement(String chatRoomId) {
        List<ExpenseSplit> expenses = expenseRepository.findByChatRoomId(chatRoomId);
        Map<String, Double> balances = new HashMap<>();

        // Calculate individual balances
        for (ExpenseSplit expense : expenses) {
            double splitAmount = expense.getAmount() / expense.getParticipants().size();
            for (String userId : expense.getParticipants()) {
                if (userId.equals(expense.getSpenderId())) continue;
                balances.put(userId, balances.getOrDefault(userId, 0.0) - splitAmount);
            }
            balances.put(expense.getSpenderId(), balances.getOrDefault(expense.getSpenderId(), 0.0) + expense.getAmount());
        }

        // Prepare Settlement
        List<ExpenseSettlement> settlements = new ArrayList<>();
        for (Map.Entry<String, Double> entry : balances.entrySet()) {
            if (entry.getValue() < 0) {
                settlements.add(new ExpenseSettlement(entry.getKey(), null, Math.abs(entry.getValue()), false, null));
            }
        }
        return settlements;
    }

    public ExpenseSettlement markAsPaid(ExpenseSettlement settlement) {
        settlement.setIsSettled(true);
        return settlement;
    }

    public String generatePaymentLink(String payerId, String receiverId, Double amount) {
        // Here you can use UPI / Razorpay to create a link
        return "upi://pay?pa=" + receiverId + "@upi&pn=" + payerId + "&am=" + amount;
    }
}
