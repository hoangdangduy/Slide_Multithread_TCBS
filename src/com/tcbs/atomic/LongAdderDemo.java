package com.tcbs.atomic;

import com.tcbs.utils.SynchronizeUtil;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.LongAdder;
import java.util.stream.IntStream;

public class LongAdderDemo {
    public static void main(String[] args) {
        LongAdder adder = new LongAdder();
        ExecutorService executor = Executors.newFixedThreadPool(2);

        IntStream.range(0, 1000)
                .forEach(i -> executor.submit(adder::increment));

        SynchronizeUtil.stop(executor);

        System.out.println(adder.sum());
        adder.reset();
        System.out.println(adder.sum());
    }
}
