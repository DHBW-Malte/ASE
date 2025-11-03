package com.cor.handlers;

import com.cor.Expense;
import com.cor.Handler;

public class BoardHandler extends Handler {
  @Override
  public void handleExpenseRequest(Expense expense) {
    System.out.println("Board Approved Expense Request");
  }
}
