package com.cor;

public class MoveForward implements Command {
    private final Robot robot;
    private final int distance;

    public MoveForward(Robot robot, int distance) {
        this.robot = robot;
        this.distance = distance;
    }

    @Override
    public void execute() {
        robot.moveForward(distance);
    }

    @Override
    public void undo() {
        robot.moveBackward(distance);
    }
}