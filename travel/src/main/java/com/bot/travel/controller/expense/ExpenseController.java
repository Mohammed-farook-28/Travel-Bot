package com.bot.travel.controller.expense;

import com.bot.travel.model.expense.ExpenseSettlement;
import com.bot.travel.model.expense.ExpenseSplit;
import com.bot.travel.service.expense.ExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/expenses")
public class ExpenseController {

    @Autowired
    private ExpenseService expenseService;

    @PostMapping
    public ExpenseSplit addExpense(@RequestBody ExpenseSplit expense) {
        return expenseService.addExpense(expense);
    }

    @GetMapping("/chatroom/{chatRoomId}")
    public List<ExpenseSplit> getExpensesByChatRoomId(@PathVariable String chatRoomId) {
        return expenseService.getExpensesByChatRoomId(chatRoomId);
    }

    @GetMapping("/chatroom/{chatRoomId}/summary")
    public List<ExpenseSettlement> getSettlementSummary(@PathVariable String chatRoomId) {
        return expenseService.calculateSettlement(chatRoomId);
    }

    @PostMapping("/pay")
    public ExpenseSettlement markAsPaid(@RequestBody ExpenseSettlement settlement) {
        return expenseService.markAsPaid(settlement);
    }

    @PostMapping("/paylink")
    public String generatePayLink(@RequestParam String payerId, @RequestParam String receiverId, @RequestParam Double amount) {
        return expenseService.generatePaymentLink(payerId, receiverId, amount);
    }

    @PutMapping("/{id}")
    public ExpenseSplit updateExpense(@PathVariable String id, @RequestBody ExpenseSplit expense) {
        return expenseService.updateExpense(id, expense);
    }

    @DeleteMapping("/{id}")
    public void deleteExpense(@PathVariable String id) {
        expenseService.deleteExpense(id);
    }
}
