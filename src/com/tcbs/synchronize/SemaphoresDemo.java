package com.tcbs.synchronize;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

import static com.tcbs.utils.SynchronizeUtil.stop;
import static java.lang.Thread.sleep;

public class SemaphoresDemo {
    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(10);

        Semaphore semaphore = new Semaphore(5);

        Runnable longRunningTask = () -> {
            boolean permit = false;
            try {
                permit = semaphore.tryAcquire(1, TimeUnit.MILLISECONDS);
                if (permit) {
                    System.out.println("Semaphore acquired: " + Thread.currentThread().getName());
                    sleep(10);
                } else {
                    System.out.println("Could not acquire semaphore: " + Thread.currentThread().getName());
                }
            } catch (InterruptedException e) {
                throw new IllegalStateException(e);
            } finally {
                if (permit) {
                    semaphore.release();
                }
            }
        };

        IntStream.range(0, 10)
                .forEach(i -> executor.submit(longRunningTask));

        stop(executor);
    }
}
