package de.mc.lib;

import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;


public class AddingThread extends  Thread {

    AtomicBoolean keepGenerating;
    LinkedBlockingQueue<Long> lbq;
    Long sum;
    Long anzahlEmpty=new Long(0);
    Long anzahlKonsumierterPrimzahlen = new Long(0);



    public AddingThread(AtomicBoolean keepGenerating, LinkedBlockingQueue<Long> lbq){
        this.keepGenerating = keepGenerating;
        this.lbq=lbq;
        sum = new Long(0);
    }

    @Override
    public void run() {
        Long i = new Long(0);
        while(sum < 10_000_000){
            try {
                i = lbq.poll(10, TimeUnit.MINUTES);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
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
