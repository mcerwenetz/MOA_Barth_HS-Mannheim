package de.mc.lib;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;

public class GeneratorThread extends Thread{

    AtomicBoolean keepGenerating;
    LinkedBlockingQueue<Long> lbq;
    Long anzahlGenerierterPrimzahlen = new Long(0);


    public GeneratorThread(AtomicBoolean keepGenerating, LinkedBlockingQueue<Long> lbq){
        this.keepGenerating = keepGenerating;
        this.lbq=lbq;
    }

    @Override
    public void run() {
        Long start = new Long(3);
        while (keepGenerating.get()){
            List<Long> divs = new ArrayList<>();
            for(Long i = new Long(2); i < start; i++){
                if(start%i==0){
                    divs.add(i);
                    anzahlGenerierterPrimzahlen++;
                }
            }
            if(divs.isEmpty() == true){
                lbq.add(start);
            }
            start++;
        }
    }
}
