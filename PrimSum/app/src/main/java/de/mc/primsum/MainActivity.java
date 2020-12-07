package de.mc.primsum;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;

import de.mc.lib.AddingThread;
import de.mc.lib.GeneratorThread;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "PrimSum";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        LinkedBlockingQueue<Long> lbq = new LinkedBlockingQueue<Long>();
        AtomicBoolean keepGenerating = new AtomicBoolean();
        GeneratorThread generator = new GeneratorThread(keepGenerating, lbq);
        AddingThread addingThread = new AddingThread(keepGenerating,lbq);
        keepGenerating.set(true);
        long  before = System.nanoTime();
        generator.start();
        addingThread.start();
        try {
            addingThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        try {
            generator.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Long after = System.nanoTime();
        Log.v(TAG,"Anzahl generierter Primes: " + generator.getAnzahlGenerierterPrimzahlen());
        Log.v(TAG,"Anzahl konsumierter Primes: " + addingThread.getAnzahlKonsumierterPrimzahlen());
        Log.v(TAG,"Anzahl Fehlzugriffe: " + addingThread.getAnzahlEmpty());
        Log.v(TAG,"Took: " + ((after-before)/1_000_000_000)+ "secs");


    }
}