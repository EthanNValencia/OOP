/*
File: Food.java
Author: Ethan Nephew
Assignment: Module 4 Project
Date due: October 11, 2020
Course: COP3300C
Description: Food class
*/
package Module4EthanNephew;

public class Food {

    /*
    Create a class called Food.  It is not a Thread,
    and does not run.  It's just a class that represents
    some data that will be shared by multiple threads.

    Simulating an animal eating, simply means that the
    thread will sleep for some length of time.  This is
    the same as the "resting" that the turtle an rabbit
    did in part I.
    */

    public synchronized void eat(Animal animal) { // synchronization seems to mostly benefit the rabbit

        /*
        The rabbit eats the food (the thread will sleep)
        for a longer time than the turtle, thus giving
        an advantage to the turtle.

        But, the turtle must wait until the rabbit is
        done eating until it can eat, so the advantage
        is reduced.
        */

        long sleepTime = animal.genRandom(); // generates a sleep time number, stored so it can be printed
        System.out.println(animal.getName() + " is eating for " + sleepTime + " milliseconds.");
        try {
            Thread.sleep(sleepTime);
        } catch (InterruptedException ie){
            ie.printStackTrace();
        }
        System.out.println(animal.getName() + " has finished eating.");

    }
}
