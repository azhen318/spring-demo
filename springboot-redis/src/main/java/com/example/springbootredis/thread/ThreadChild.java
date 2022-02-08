package com.example.springbootredis.thread;

public class ThreadChild extends Thread{

    @Override
    public void run() {
        System.out.println("this is threadChild run...");
    }
}
