package com.cor.handlers;

import com.cor.Expense;
import com.cor.Handler;

public class DirectorHandler extends Handler {
  @Override
  public void handleExpenseRequest(Expense expense) {
    if (expense.getAmount() <= 10000) {
      System.out.println("Director Approved Request");
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
