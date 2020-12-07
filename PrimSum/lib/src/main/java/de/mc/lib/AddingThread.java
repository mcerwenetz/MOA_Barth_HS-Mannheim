package de.mc.lib;

import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicBoolean;

public class AddingThread extends  Thread {

    AtomicBoolean keepGenerating;
    ConcurrentLinkedQueue<Long> clq;
    Long sum;


    public AddingThread(AtomicBoolean keepGenerating, ConcurrentLinkedQueue<Long> clq){
        this.keepGenerating = keepGenerating;
        this.clq=clq;
        sum = new Long(0);
    }

    @Override
    public void run() {
        while(sum < 100_000_000){
            Long i = clq.poll();
            if (i != null){
                sum+=i;
            }
        }
        keepGenerating.set(false);
    }
}
