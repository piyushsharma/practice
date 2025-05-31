package com.brainteaser;


public class Twisted {
    
    private final String name;
    Twisted(String name) {
        this.name = name;
    }
    private String name() {
        return name;
    }

    // we have created an anonymous inner class
    // which is referencing the outer class object that has string main
    private void reproduce() {
        new Twisted("reproduce") {
            void printName() {                
                System.out.println(name());
            }
        }.printName();
    }
    
    public static void main(String[] args) {
        new Twisted("main").reproduce();
    }
}