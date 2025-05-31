package com.brainteaser;

public class Null {
    
    public static void greet() {
        System.out.println("Hello world!");
    }

    public void print(String name) {
        System.out.print(name);
    }

    public static void main(String[] args) {
        ((Null) null).greet();
    } 
}