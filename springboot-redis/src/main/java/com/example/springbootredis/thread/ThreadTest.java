package com.example.springbootredis.thread;

import java.util.concurrent.*;

public class ThreadTest {

    public static void main(String[] args){

        Thread th=new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("this is ThreadTest run ...");
            }
        });

        th.start();

        Thread thChild=new ThreadChild();

        thChild.start();

        FutureTask<String> futureTask=new FutureTask<>(new Callable<String>() {
            @Override
            public String call() throws Exception {
                return "this is futureTask call...";
            }
        });


        new Thread(futureTask).start();
        String v1=null,v2=null;

        ExecutorService  executor= Executors.newFixedThreadPool(10);
        Future<String> exectorFuture=executor.submit(new Callable<String>() {
                    @Override
                    public String call() throws Exception {
                        return "this is ExecutorService call...";
                    }
                });

        CompletableFuture<String> cp1 = CompletableFuture.supplyAsync(() -> {
//            try {
//                Thread.sleep(100 );
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }

            return "hello world";
        });

        try {
            v1=futureTask.get();
            v2=exectorFuture.get();
        }catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println(v1);
        System.out.println(v2);
        System.out.println(cp1.join());

        System.out.println(cp1.getNow("hello h2t"));

    }

}
