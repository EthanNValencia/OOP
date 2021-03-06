/*
File: Motorcycle.java
Author: Ethan Nephew
Assignment: Midterm Project
Date due: October 18, 2020
Course: COP3300C
Description: Motorcycle class, subclass of Vehicle, and is a Runnable
*/

package MidtermProjectEthanNephew;

public class Motorcycle extends Vehicle implements Runnable{
    public Motorcycle(double engineSize, String name, Racetrack racetrack, CreateFile file) {
        super(engineSize, name, racetrack, file);
        // the constructor assigns the subclass specific variables to the vehicle superclass
        this.setFuelCapacity(MOTORCYCLE_TANK);
        this.setFuelTank(MOTORCYCLE_TANK);
        this.setRefuelPenalty(MOTORCYCLE_TANK*MOTORCYCLE_PEN);
    }
    public void run(){
        getRacetrack().race(this, this.file);
    }

}
