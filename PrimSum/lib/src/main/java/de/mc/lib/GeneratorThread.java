package de.mc.lib;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicBoolean;

import sun.rmi.runtime.Log;

public class GeneratorThread extends Thread{

    AtomicBoolean keepGenerating;
    ConcurrentLinkedQueue<Long> clq;


    public GeneratorThread(AtomicBoolean keepGenerating, ConcurrentLinkedQueue<Long> clq){
        this.keepGenerating = keepGenerating;
        this.clq=clq;
    }

    @Override
    public void run() {
        Long start = new Long(3);
        while (keepGenerating.get()){
            List<Long> divs = new ArrayList<>();
            for(Long i = new Long(2); i < start; i++){
                if(start%i==0){
                    divs.add(i);
                }
            }
            if(divs.isEmpty() == true){
                clq.add(start);
            }
            start++;
        }
    }
}
