package com.tcbs.thread;

import java.util.concurrent.*;

public class CallableDemo {
    public static void main(String[] args) throws Exception {
        Callable<Integer> task = () -> {
            try {
                TimeUnit.SECONDS.sleep(3);
                return 123;
            } catch (InterruptedException e) {
                throw new IllegalStateException("task interrupted", e);
            }
        };

        System.out.println("Start Callable");
        // chi lay du lieu, ko kiem tra duoc trang thai
        System.out.println(task.call());
        System.out.println("End Callable");


        System.out.println("\n\n\n\n");



        // tra du lieu va dung, co the kiem tra
        ExecutorService executor = Executors.newFixedThreadPool(1);
        Future<Integer> future = executor.submit(task);

        System.out.println("future done? " + future.isDone());

        Integer result = future.get();
        System.out.println("block thread to get value in Future");
        System.out.println("future done? " + future.isDone());
        System.out.print("result: " + result);
        if (future.isDone()) {
            executor.shutdown();
        }


    }
}
