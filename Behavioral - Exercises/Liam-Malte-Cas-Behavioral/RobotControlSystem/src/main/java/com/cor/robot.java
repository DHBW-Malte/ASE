package com.cor;

public class Robot {
    private int x;
    private int y;
    private int direction;
    private boolean carryingObject;

    public void moveForward(int distance) {
        System.out.println("Moving forward " + distance + " units");
    }

    public void moveBackward(int distance) {
        System.out.println("Moving backward " + distance + " units");
    }

    public void turnLeft(int angle) {
        System.out.println("Turning left " + angle + " degrees");
        direction = (direction - angle + 360) % 360;
    }

    public void turnRight(int angle) {
        System.out.println("Turning right " + angle + " degrees");
        direction = (direction + angle) % 360;
    }

    public void pickUp() {
        if (!carryingObject) {
            System.out.println("Picked up object");
            carryingObject = true;
        } else {
            System.out.println("Already carrying an object!");
        }
    }

    public void drop() {
        if (carryingObject) {
            System.out.println("Dropped object");
            carryingObject = false;
        } else {
            System.out.println("Nothing to drop!");
        }
    }
}