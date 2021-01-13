/*
File: Car.java
Author: Ethan Nephew
Assignment: Midterm Project
Date due: October 18, 2020
Course: COP3300C
Description: Car class, subclass of Vehicle, and is a Runnable
*/

package MidtermProjectEthanNephew;

public class Car extends Vehicle implements Runnable{
    public Car(double engineSize, String name, Racetrack racetrack, CreateFile file) {
        super(engineSize, name, racetrack, file);
        // the constructor assigns the subclass specific variables to the vehicle superclass
        this.setFuelCapacity(CAR_TANK);
        this.setFuelTank(CAR_TANK);
        this.setRefuelPenalty(CAR_TANK*CAR_PEN);
    }
    public void run(){
        getRacetrack().race(this, this.file);
    }

}

// I made some changes here. 

// This will cause a conflict with my local repository. Uh oh
