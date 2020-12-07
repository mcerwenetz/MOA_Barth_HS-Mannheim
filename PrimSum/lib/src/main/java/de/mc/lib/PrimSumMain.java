package de.mc.lib;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicBoolean;

public class PrimSumMain {


    public static void main(String[] args) throws InterruptedException {
        ConcurrentLinkedQueue<Long> clq = new ConcurrentLinkedQueue<>();
        AtomicBoolean keepGenerating = new AtomicBoolean();
        Thread generator = new GeneratorThread(keepGenerating, clq);
        keepGenerating.set(true);
        generator.start();
        keepGenerating.set(false);
        generator.join();
    }
}