package com.cwjcsu.learning.笔试._1;
/**
 * 
 * @author atlas
 * @date 2013-4-23
 */
public class Twisted {
    private final String name;
    Twisted(String name) {
        this.name = name;
    }
    private String name() {
        return name;
    }
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
