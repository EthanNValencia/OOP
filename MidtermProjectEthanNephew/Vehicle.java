/*
File: Vehicle.java
Author: Ethan Nephew
Assignment: Midterm Project
Date due: October 18, 2020
Course: COP3300C
Description: abstract Vehicle superclass, this could be considered the core of the program
*/

package MidtermProjectEthanNephew;

public abstract class Vehicle implements RaceConstants{ // by implementing the RaceConstants -- Truck, Motorcycle, and Car can access the constants too, even though they Implement Runnable.

    public double getEngineSize() {
        return vehicleDoubleArray[0];
    }
    public void setEngineSize(double engineSize) {
        this.vehicleDoubleArray[0] = engineSize;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public double getPosition() {
        return vehicleDoubleArray[4];
    }
    public void setPosition(double position) {
        this.vehicleDoubleArray[4] = position;
    }
    public double getFuelTank() {
        return vehicleDoubleArray[1];
    }
    public void setFuelTank(double fuelTank) {
        this.vehicleDoubleArray[1] = fuelTank;
    }
    public double getRefuelPenalty() {
        return vehicleDoubleArray[3];
    }
    public void setRefuelPenalty(double refuelPenalty) {
        this.vehicleDoubleArray[3] = refuelPenalty;
    }
    public int getRandomCounter() {
        return randomCounter;
    }
    public void setRandomCounter(int randomCounter) {
        this.randomCounter = getRandomCounter() + randomCounter;
    }
    public double getFuelCapacity() { // unused accessor
        return vehicleDoubleArray[2];
    }
    public void setFuelCapacity(double fuelCapacity) {
        this.vehicleDoubleArray[2] = fuelCapacity;
    }
    public Racetrack getRacetrack() {
        return racetrack;
    }


    private double[] vehicleDoubleArray = new double[5]; // engineSize[0], fuelTank[1], fuelCapacity[2], refuelPenalty[3], position[4]
    private String name;
    private int randomCounter = 1;
    protected Racetrack racetrack;
    protected CreateFile file;

    public Vehicle(double engineSize, String name, Racetrack racetrack, CreateFile file){
        this.setEngineSize(engineSize);
        this.setName(name);
        this.racetrack = racetrack;
        this.file = file;
    }

    public static synchronized void refuel(Vehicle vehicle, CreateFile file){ // this needs to be static so all the vehicle objects compete for the same method.
        if (vehicle instanceof Truck) {
            // EPA inspections before refueling, this block serves as a balancing effect. It will run more often if the RACE_LENGTH is set to 500+
            // if a vehicle gets this punishment, then it will still have to refuel.
            if (vehicle.getFuelCapacity() >= TRUCK_TANK*2){
                file.writeToFile(vehicle.getName() + " has been caught with eco-unfriendly equipment! Tank size adjusted to " + TRUCK_TANK + ".");
                System.out.println(vehicle.getName() + " has been caught with eco-unfriendly equipment!Tank size adjusted to " + TRUCK_TANK + ".");
                vehicle.setFuelCapacity(TRUCK_TANK);
            }
        } else if (vehicle instanceof Car){
            if (vehicle.getFuelCapacity() >= CAR_TANK*2){
                file.writeToFile(vehicle.getName() + " has been caught with eco-unfriendly equipment! Tank size adjusted to " + CAR_TANK + ".");
                System.out.println(vehicle.getName() + " has been caught with eco-unfriendly equipment! Tank size adjusted to " + CAR_TANK + ".");
                vehicle.setFuelCapacity(CAR_TANK);
            }
        } else if (vehicle instanceof Motorcycle){
            if (vehicle.getFuelCapacity() >= MOTORCYCLE_TANK*2){
                file.writeToFile(vehicle.getName() + " has been caught with eco-unfriendly equipment! Tank size adjusted to " + MOTORCYCLE_TANK + ".");
                System.out.println(vehicle.getName() + " has been caught with eco-unfriendly equipment! Tank size adjusted to " + MOTORCYCLE_TANK + ".");
                vehicle.setFuelCapacity(MOTORCYCLE_TANK);
            }
        } // if a vehicle gets this punishment, then it will still have to refuel.

        if (vehicle.getFuelCapacity() <= vehicle.getEngineSize()){ // verify that vehicle has sufficient tank size to operate
            // if the fuel tank becomes too small, then it will be repaired to vehicle default
            file.writeToFile("Emergency repairs are required for " + vehicle.getName() + "...");
            System.out.println("Emergency repairs are required for " + vehicle.getName() + "...");
            if (vehicle instanceof Truck) {
                vehicle.setFuelCapacity(TRUCK_TANK);
                vehicle.setFuelTank(TRUCK_TANK); // comment out setFuelTank() to make repairs a bigger disadvantage
            } else if (vehicle instanceof Car){
                vehicle.setFuelCapacity(CAR_TANK);
                vehicle.setFuelTank(CAR_TANK); // comment out setFuelTank() to make repairs a bigger disadvantage
            } else if (vehicle instanceof Motorcycle) {
                vehicle.setFuelTank(MOTORCYCLE_TANK);
                vehicle.setFuelCapacity(MOTORCYCLE_TANK); // comment out setFuelTank() to make repairs a bigger disadvantage
            }
            try {
                Thread.sleep(2500);
            } catch (InterruptedException ie){
                ie.printStackTrace();
            }
            file.writeToFile("Emergency repairs for the " + vehicle.getName() + " have been completed!");
            System.out.println("Emergency repairs for the " + vehicle.getName() + " have been completed!");
        } // end of if

        try {
            file.writeToFile(vehicle.getName() + " is refueling...");
            System.out.println(vehicle.getName() + " is refueling...");
            vehicle.setFuelTank(vehicle.getFuelCapacity());
            //System.out.println(vehicle.getName() + " fuel level is at " + vehicle.getFuelTank() + " The fuel capacity is " + vehicle.getFuelCapacity());
            Thread.sleep((long)vehicle.getRefuelPenalty());
        } catch (InterruptedException ie){
            ie.printStackTrace();
        }
        file.writeToFile(vehicle.getName() + " has refueled!" + " Tank is at " + vehicle.getFuelTank());
        System.out.println(vehicle.getName() + " has refueled!" + " Tank is at " + vehicle.getFuelTank());
    } // end of refuel method
    public synchronized void randomEvent(){ // This is a random event that will bestow good or bad effects (randomly, of course)
        file.writeToFile("An event for " + getName() + " has occurred!");
        System.out.println("An event for " + getName() + " has occurred!");
        double randomNumber = Math.random(); // role for positive or negative event
            if (randomNumber > 0.5) { // if greater than 0.5 positive event will occur
                randomNumber = Math.random();
                    if (randomNumber > 0.5) { // positive event 1
                        setRandomCounter(1);
                        setFuelCapacity(getFuelCapacity()+4);
                        //fuelCapacity+=8; // higher fuel capacity
                        file.writeToFile(getName() + " has received a fuel capacity upgrade! Tank size: " + getFuelCapacity() + " *good*");
                        System.out.println(getName() + " has received a fuel capacity upgrade! Tank size: " + getFuelCapacity() + " *good*");
                    } else { // positive event 2
                        setRandomCounter(1);
                        if (getRefuelPenalty()-20 > 0) { // we do not want there to be a negative wait time.
                            setRefuelPenalty(getRefuelPenalty() - 20); // less refuel time
                        } else {
                            setFuelCapacity(getFuelCapacity()+4);
                            //fuelCapacity+=8; // higher fuel capacity
                            file.writeToFile(getName() + " has received a fuel capacity upgrade! Tank size: " + getFuelCapacity() + " *good*");
                            System.out.println(getName() + " has received a fuel capacity upgrade! Tank size: " + getFuelCapacity() + " *good*");
                        }
                        file.writeToFile(getName() + " has received a decrease to refueling time! *good*");
                        System.out.println(getName() + " has received a decrease to refueling time! *good*");
                    }
            } else { // if less than 0.5 negative event
                randomNumber = Math.random();
                    if (randomNumber > 0.5){ // negative event 1
                        setRandomCounter(1);
                        setFuelCapacity(getFuelCapacity()-4);
                        //fuelCapacity-=4; // less fuel capacity
                        file.writeToFile(getName() + " has received a fuel capacity downgrade! Tank size: " + getFuelCapacity() + " *bad*");
                        System.out.println(getName() + " has received a fuel capacity downgrade! Tank size: " + getFuelCapacity() + " *bad*");
                    } else { // negative event 2
                        setRandomCounter(1);
                        setRefuelPenalty(getRefuelPenalty()+10); // longer fuel time
                        file.writeToFile(getName() + " has received an increase to refueling time! *bad*");
                        System.out.println(getName() + " has received an increase to refueling time! *bad*");
                    }
            }
    }
}
