package com.cor;

public class Drop implements Command {
    private final Robot robot;

    public Drop(Robot robot) {
        this.robot = robot;
    }

    @Override
    public void execute() {
        robot.drop();
    }

    @Override
    public void undo() {
        robot.pickUp();
    }
}