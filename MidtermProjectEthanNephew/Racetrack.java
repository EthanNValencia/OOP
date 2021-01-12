/*
File: Racetrack.java
Author: Ethan Nephew
Assignment: Midterm Project
Date due: October 18, 2020
Course: COP3300C
Description: This is where the main method is located and is the track that the threads are competing over.
*/

package MidtermProjectEthanNephew;

public class Racetrack implements RaceConstants{
    private static boolean winner = false;
    private static String winnerName = "";

    public void race(Vehicle vehicle, CreateFile file){
        while (!winner) {
            if (vehicle.getRandomCounter()%10 != 0) {
                if (vehicle.getFuelTank() > vehicle.getEngineSize()) {
                    try {
                        vehicle.setRandomCounter(1);
                        vehicle.setFuelTank(vehicle.getFuelTank() - vehicle.getEngineSize());
                        vehicle.setPosition(vehicle.getPosition() + vehicle.getEngineSize());
                        System.out.println(vehicle.getName() + " is in position " + vehicle.getPosition());
                        file.writeToFile(vehicle.getName() + " is in position " + vehicle.getPosition());
                        Thread.sleep((long) vehicle.getEngineSize()); // larger engine sleeps longer, but moves farther.
                    } catch (InterruptedException ie) {
                        ie.printStackTrace();
                    }
                    if (vehicle.getPosition() >= RACE_LENGTH && !winner) {
                        vehicle.setPosition(RACE_LENGTH);
                        winner = true; // winner boolean
                        winnerName = vehicle.getName(); //winner name is set

                        try {
                            Thread.sleep(3000); // this sleep time gives all other processes time to end
                        } catch (InterruptedException ie){
                            ie.printStackTrace();
                        }
                        file.writeToFile("The winner is " + winnerName);
                        System.out.println("The winner is " + winnerName);
                    }
                } else {
                    Vehicle.refuel(vehicle, file); // static method call
                }
            } else if (vehicle.getRandomCounter()%10==0) {
                vehicle.randomEvent(); // non-static method call
            }
        } // while
    } // race

    public static void main(String[]args){
        CreateFile file = new CreateFile(); // the threads share the CreateFile object too
        Racetrack racetrack = new Racetrack(); // the threads share the racetrack object
        Thread truck1 = new Thread(new Truck(6.5, "Raptor", racetrack, file));
        Thread car1 = new Thread(new Car(4.5, "Mercedes", racetrack, file));
        Thread motorcycle1 = new Thread(new Motorcycle(1.5, "Africa Twin", racetrack, file));
        truck1.start();
        car1.start();
        motorcycle1.start();

    }
}
