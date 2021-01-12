/*
File: Main.java
Author: Ethan Nephew
Assignment: Module 4 Project
Date due: October 11, 2020
Course: COP3300C
Description: Main class
*/
package Module4EthanNephew;

public class Main {
    public static void main(String[] args){
        /*
        In the main method create 2 instances of the
        Animal class, one called rabbit and one called
        turtle.  Make them "user" threads, as opposed
        to daemon threads.

        The race is 100 yards. The initial position is 0.
        Suppose the speed of the rabbit is 5, and its
        maxRest is 150.  Suppose the speed of the turtle
        is 3, and its maxRest is 100.
        */


        Food food = new Food(); // both animals are competing for the food object
        Thread rabbit = new Thread(new Animal("Rabbit", 0, 5, 150, food)); // The rabbit rests longer than the turtle, but the rabbit has a higher speed.
        rabbit.setDaemon(false); // unnecessary, this is a default setting.
        rabbit.start(); // start the thread
        Thread turtle = new Thread(new Animal("Turtle", 0, 3, 70, food)); // Adjust them so that the rabbit wins sometimes, and the turtle wins sometimes.
        turtle.setDaemon(false); // unnecessary, this is a default setting.
        turtle.start(); // start the thread
        /*
        I have observed that optimizing the race varies with
        respect to what sort of applications I have opened
        on the laptop. This laptop has a fairly weak processor.
        1.1GHZ w/ 4 CPUs.
        */
    }
}
