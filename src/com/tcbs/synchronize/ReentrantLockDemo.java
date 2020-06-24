package com.tcbs.synchronize;

import com.tcbs.utils.SynchronizeUtil;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.IntStream;

public class ReentrantLockDemo {

    ReentrantLock lock = new ReentrantLock();
    int count = 0;

    void increment() {
        lock.lock();
        try {
            count++;
        } finally {
            lock.unlock();
        }
    }

    void run() {
        ExecutorService executor = Executors.newFixedThreadPool(2);

        IntStream.range(0, 10000)
                .forEach(i -> executor.submit(this::increment));

        SynchronizeUtil.stop(executor);

        System.out.println(count);
    }



    public static void main(String[] args) {
        ReentrantLockDemo raceCondition = new ReentrantLockDemo();
        raceCondition.run();
    }
}
