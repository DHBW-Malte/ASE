package com.cor;

public abstract class Handler {
  protected Handler nextHandler;

  public void setNextHandler(Handler nextHandler) {
    this.nextHandler = nextHandler;
  }

  public abstract void handleExpenseRequest(Expense expense);

}
