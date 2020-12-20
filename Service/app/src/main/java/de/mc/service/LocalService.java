package de.mc.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

import androidx.annotation.Nullable;

public class LocalService extends Service {

    private final IBinder binder = new LocalBinder();
    public class LocalBinder extends Binder{
        public LocalService getService(){
            return LocalService.this;
        }
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
}
