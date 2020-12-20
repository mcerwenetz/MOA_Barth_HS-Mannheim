package de.mc.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.speech.tts.TextToSpeech;
import android.util.Log;

import androidx.annotation.Nullable;

public class LocalService extends Service {

    public static final String CMD_COMPUTE = "compute";
    public static final String CMD_SAY = "say";
    TextToSpeech tts;
    private final IBinder binder = new LocalBinder();
    public class LocalBinder extends Binder{
        public LocalService getService(){
            return LocalService.this;
        }
    }

    @Override
    public void onCreate() {
        super.onCreate();
        tts = new TextToSpeech(this, null);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        tts.shutdown();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return binder;
    }

    public Integer doAdd(Integer zahl1, Integer zahl2){
        int ret =0;
        if (zahl1 != null){
            ret += zahl1;
        }
        if (zahl2 != null){
            ret +=zahl2;
        }
        return ret;
    }

//    In dem fall Quatsch weil ich das Ergebnis nicht setzen kann.
//    Nur für Service das kein Return hat
    public int onStartCommand(Intent intent, int flags , int startId){
        if(intent.getAction().equals(CMD_SAY)){
            Log.v("Paramslocal", "Neues Sprachausgabe intent");
            Bundle extras = intent.getExtras();
            Integer number1 = extras.getInt("number1");
            Integer number2 = extras.getInt("number2");
            Integer res = doAdd(number1, number2);
            Log.v("LocalService", "got result " + res + " but cant do anything with it.");
            String ausgabe = String.format("Ein deutscher String");
            tts.speak(ausgabe,TextToSpeech.QUEUE_ADD, null, "1");
        }
        return START_NOT_STICKY;
    }
}
