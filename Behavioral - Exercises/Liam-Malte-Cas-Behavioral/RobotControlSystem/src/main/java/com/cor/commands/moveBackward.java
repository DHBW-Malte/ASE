package com.cor;

public class MoveBackward implements Command {
    private final Robot robot;
    private final int distance;

    public MoveBackward(Robot robot, int distance) {
        this.robot = robot;
        this.distance = distance;
    }

    @Override
    public void execute() {
        robot.moveBackward(distance);
    }

    @Override
    public void undo() {
        robot.moveForward(distance);
    }
}