package de.mc.lib;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicBoolean;

public class PrimSumMain {


    public static void main(String[] args) throws InterruptedException {
        ConcurrentLinkedQueue<Long> clq = new ConcurrentLinkedQueue<>();
        AtomicBoolean keepGenerating = new AtomicBoolean();
        GeneratorThread generator = new GeneratorThread(keepGenerating, clq);
        AddingThread addingThread = new AddingThread(keepGenerating,clq);
        keepGenerating.set(true);
        generator.start();
        addingThread.start();
        addingThread.join();
        generator.join();
        System.out.format("Anzahl genereierte primes: %d\n", generator.anzahlGenerierterPrimzahlen);
        System.out.format("Anzahl konsumierte primes: %d\n", addingThread.anzahlKonsumierterPrimzahlen);
        System.out.format("Anzahl leere zugriffe: %d\n", addingThread.anzahlEmpty);

    }
}