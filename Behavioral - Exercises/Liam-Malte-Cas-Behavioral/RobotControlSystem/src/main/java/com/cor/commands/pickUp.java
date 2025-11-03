package com.cor;

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