package com.cor.commands;

import com.cor.Command;
import com.cor.Robot;

public class TurnLeft implements Command {
    private final Robot robot;
    private final int angle;

    public TurnLeft(Robot robot, int angle) {
        this.robot = robot;
        this.angle = angle;
    }

    @Override
    public void execute() {
        robot.turnLeft(angle);
    }

    @Override
    public void undo() {
        robot.turnRight(angle);
    }
}