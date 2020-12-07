package de.mc.lib;

import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicBoolean;


public class AddingThread extends  Thread {

    AtomicBoolean keepGenerating;
    ConcurrentLinkedQueue<Long> clq;
    Long sum;
    Long anzahlEmpty=new Long(0);
    Long anzahlKonsumierterPrimzahlen = new Long(0);



    public AddingThread(AtomicBoolean keepGenerating, ConcurrentLinkedQueue<Long> clq){
        this.keepGenerating = keepGenerating;
        this.clq=clq;
        sum = new Long(0);
    }

    @Override
    public void run() {
        Long i = new Long(0);
        while(sum < 10_000_000){
            i = clq.poll();
            if (i != null){
                anzahlKonsumierterPrimzahlen++;
                sum+=i;
            }else{
                anzahlEmpty++;
            }
        }
        keepGenerating.set(false);
        System.out.println("Letzte konsumierte Primzahl: " + i);
    }
}
