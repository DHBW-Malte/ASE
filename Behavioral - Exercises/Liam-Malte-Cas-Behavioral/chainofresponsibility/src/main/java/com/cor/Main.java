package com.cor;

import com.cor.handlers.BoardHandler;
import com.cor.handlers.CEOHandler;
import com.cor.handlers.DirectorHandler;
import com.cor.handlers.LeadHandler;
import com.cor.handlers.ManagerHandler;

public class Main {
    public static void main(String[] args) {
        Handler leadHandler = new LeadHandler();
        Handler managerHandler = new ManagerHandler();
        Handler directorHandler = new DirectorHandler();
        Handler ceoHandler = new CEOHandler();
        Handler boardHandler = new BoardHandler();

        leadHandler.setNextHandler(managerHandler);
        managerHandler.setNextHandler(directorHandler);
        directorHandler.setNextHandler(ceoHandler);
        ceoHandler.setNextHandler(boardHandler);

        Expense cheapExpense = new Expense(3.50, "Döner Kebap");
        Expense expensiveExpense = new Expense(10_000.50, "New Computers");

        leadHandler.handleExpenseRequest(cheapExpense);
        leadHandler.handleExpenseRequest(expensiveExpense);
    }
}