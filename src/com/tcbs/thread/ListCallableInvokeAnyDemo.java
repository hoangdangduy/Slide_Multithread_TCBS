package com.tcbs.thread;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;

public class ListCallableInvokeAnyDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executor = Executors.newWorkStealingPool();

        List<Callable<String>> callables = Arrays.asList(
                callable("task1", 20),
                callable("task2", 7),
                callable("task3", 3));

        String result = executor.invokeAny(callables);
        System.out.println(result);
    }

    private static Callable<String> callable(String result, long sleepSeconds) {
        return () -> {
            TimeUnit.SECONDS.sleep(sleepSeconds);
            return result;
        };
    }
}
