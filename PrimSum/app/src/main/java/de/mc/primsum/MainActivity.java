package de.mc.primsum;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.util.Locale;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;

import de.mc.lib.AddingThread;
import de.mc.lib.GeneratorThread;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "PrimSum";

    TextView genprimes;
    TextView conprimes;
    TextView empty;
    TextView took;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        genprimes=findViewById(R.id.genprimes);
        conprimes=findViewById(R.id.conprimes);
        empty = findViewById(R.id.empty);
        took=findViewById(R.id.took);
        LinkedBlockingQueue<Long> lbq = new LinkedBlockingQueue<Long>();
        AtomicBoolean keepGenerating = new AtomicBoolean();
        GeneratorThread generator = new GeneratorThread(keepGenerating, lbq);
        AddingThread addingThread = new AddingThread(keepGenerating,lbq);
        keepGenerating.set(true);
        Long  before = System.nanoTime();
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
        double tookTime = ((after.floatValue() - before.floatValue())/1E9);
        genprimes.setText(String.format(Locale.getDefault(),"Anzahl generierter Primes: %d", generator.getAnzahlGenerierterPrimzahlen()));
        conprimes.setText(String.format(Locale.getDefault(),"Anzahl konsumierter Primes: %d", addingThread.getAnzahlKonsumierterPrimzahlen()));
        empty.setText(String.format(Locale.getDefault(), "Anzahl Fehlzugriffe: %d", addingThread.getAnzahlEmpty()));
        took.setText(String.format(Locale.getDefault(), "Took %.4f sec", tookTime));


    }
}