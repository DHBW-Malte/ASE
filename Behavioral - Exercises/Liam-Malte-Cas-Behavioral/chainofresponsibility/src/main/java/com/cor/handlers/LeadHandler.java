package com.cor.handlers;

import com.cor.Expense;
import com.cor.Handler;

public class LeadHandler extends Handler {
  @Override
  public void handleExpenseRequest(Expense expense) {
    if (expense.getAmount() <= 500) {
      System.out.println("Team Lead Approved Request");
      expense.getDescription();
      System.out.println("Amount: " + expense.getAmount());
    } else if (nextHandler != null) {
      System.out.println("Need higher authority, moving request up the chain.");
      nextHandler.handleExpenseRequest(expense);
    } else {
      System.out.println("No handler available");
    }
  }
}
