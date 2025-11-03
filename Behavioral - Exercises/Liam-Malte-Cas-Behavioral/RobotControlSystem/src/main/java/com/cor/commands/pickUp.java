package com.cor.commands;

import com.cor.Command;
import com.cor.Robot;

public class PickUp implements Command {
    private final Robot robot;

    public PickUp(Robot robot) {
        this.robot = robot;
    }

    @Override
    public void execute() {
        robot.pickUp();
    }

    @Override
    public void undo() {
        robot.drop();
    }
}