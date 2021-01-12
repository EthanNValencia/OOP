/*
File: Truck.java
Author: Ethan Nephew
Assignment: Midterm Project
Date due: October 18, 2020
Course: COP3300C
Description: Truck class, subclass of Vehicle, and is a Runnable
*/

package MidtermProjectEthanNephew;

public class Truck extends Vehicle implements Runnable{
    public Truck(double engineSize, String name, Racetrack racetrack, CreateFile file) {
        super(engineSize, name, racetrack, file);
        // the constructor assigns the subclass specific variables to the vehicle superclass
        this.setFuelCapacity(TRUCK_TANK);
        this.setFuelTank(TRUCK_TANK);
        this.setRefuelPenalty(TRUCK_TANK*TRUCK_PEN);
    }
    public void run(){
        getRacetrack().race(this, this.file);
    }
}
