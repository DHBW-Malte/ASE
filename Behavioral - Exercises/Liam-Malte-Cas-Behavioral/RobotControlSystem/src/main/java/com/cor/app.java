package com.cor;

import com.cor.commands.*;

public class App {
    public static void main(String[] args) {
        Robot robot = new Robot();
        Invoker invoker = new Invoker();

        invoker.executeCommand(new MoveForward(robot, 10));
    }
}