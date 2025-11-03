package com.cor;

public class Expense {
  private Double amount;
  private String description;

  public Expense(Double amount, String description) {
    this.amount = amount;
    this.description = description;
  }

  public Double getAmount() {
    return this.amount;
  }

  public void getDescription() {
    System.out.println("Description: " + this.description);
  }
}
