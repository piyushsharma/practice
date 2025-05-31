package com.dsandalgos.string.statepattern;

/**
 * Created by Piyush Sharma
 */


public class Context {

    private State state;
    private String str;
    private StringBuilder sb;
    private int begin;

    public Context(String str, int begin, StringBuilder sb) {
        this.str = str;
        this.sb = sb;
        this.begin = begin;
    }

    public int getBegin() {
        return begin;
    }

    public void setBegin(int begin) {
        this.begin = begin;
    }

    public String getStr() {
        return str;
    }

    public void setStr(String str) {
        this.str = str;
    }

    public StringBuilder getSb() {
        return sb;
    }

    public void setSb(StringBuilder sb) {
        this.sb = sb;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }
}
