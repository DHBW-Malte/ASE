package com.cor;

public class TurnRight implements Command {
    private final Robot robot;
    private final int angle;

    public TurnRight(Robot robot, int angle) {
        this.robot = robot;
        this.angle = angle;
    }

    @Override
    public void execute() {
        robot.turnRight(angle);
    }

    @Override
    public void undo() {
        robot.turnLeft(angle);
    }
}