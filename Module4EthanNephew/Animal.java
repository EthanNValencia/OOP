/*
File: Animal.java
Author: Ethan Nephew
Assignment: Module 4 Project
Date due: October 11, 2020
Course: COP3300C
Description: Animal class (thread)
*/
package Module4EthanNephew;

public class Animal implements Runnable{ // Create a class called Animal that implements the Runnable interface.
    /*
    Some detail about the Animal class.  It has
    instance variables, name, position, speed,
    and restMax.  It has a static boolean winner.
    It starts a false.  The position represents
    the position in the race for this Animal object.
    The restMax represents how long the Animal rests
    between each time it runs.
    */
    private final int RACEMAX = 100; // can be converted to local variable
    private static boolean winner = false;
    private static String winnerName = "";
    private int counter = 0; // I like seeing how many times each thread runs (doesn't count food executions)
    private String name;
    private int position;
    private int speed;
    private int restMax;
    private Food eatFood;

    @Override
    public void run() {

        while(!winner && position <= RACEMAX) { // while the race is not finished

            eatFood.eat(this); // the thread will try to eat. I'm not sure if there is a better place for the eat() method call.

            try { // the thread will try to rest
                Thread.sleep(genRandom());
            } catch (InterruptedException ie){
                ie.printStackTrace();
            }

            if (!winner && position <= RACEMAX) { // if race is still not finished
                position += speed; // increase the threads position in the race
                counter++;
                    if (position >= RACEMAX) { // if thread has passed or is on the finish line
                        winner = true; // When someone wins, set the static variable winner to true, and both threads will finish their run method, and thus stop.
                        winnerName = getName(); // winner name is assigned
                    }
                System.out.println(getName() + " [position " + getPosition() + "] [winner: " + winner + "] [counter: " + counter + "]");
            }
            if (winner && getName().equals(winnerName)){ // if the accessing thread is the winning thread
                try {
                    Thread.sleep(300); // this forces the execution of this if statement to wait until the other thread has finished
                } catch (InterruptedException ie) {
                    ie.printStackTrace();
                }
                System.out.println("The winner is " + this.getName()); // prints the winner
            }
        }
    }
    public Animal(String name, int position, int speed, int restMax, Food food){ // animal constructor
        this.setName(name);
        this.setPosition(position);
        this.setSpeed(speed);
        this.setRestMax(restMax);
        this.eatFood = food;
    }
    public long genRandom() {
        return (long)(Math.random()*this.getRestMax());
        // I decided to just create a random number generator method.
    }
    // standard accessors and mutators
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getPosition() {
        return position;
    }
    public void setPosition(int position) {
        this.position = position;
    }
    public int getSpeed() {
        return speed;
    }
    public void setSpeed(int speed) {
        this.speed = speed;
    }
    public int getRestMax() {
        return restMax;
    }
    public void setRestMax(int restMax) {
        this.restMax = restMax;
    }


}
