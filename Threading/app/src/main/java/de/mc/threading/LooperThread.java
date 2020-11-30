package de.mc.threading;

import android.os.Handler;
import android.os.Looper;

public class LooperThread extends Thread{
    public Handler mHandler;

    @Override
    public void run() {
        Looper.prepare();
        mHandler = new Handler();
        Looper.loop();
    }

    public void shutdown(){
        mHandler.getLooper().quit();
    }
}


