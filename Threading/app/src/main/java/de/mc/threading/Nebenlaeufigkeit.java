package de.mc.threading;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class Nebenlaeufigkeit extends Activity {

    private static final String TAG = "Nebenlaeufigkeit";
    Button compMain;
    Button compWrong;
    Button compRunOnUiThread;
    Button compHandler;
    Button compLooper;
    TextView tvTime;
    SeekBar sbar;
    Integer sleep = 0;
    private Handler handler;
    private LooperThread looperThread;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nl);
        looperThread = new LooperThread();
        looperThread.start();
        compMain = findViewById(R.id.btnMainThread);
        compWrong = findViewById(R.id.btnCrash);
        compRunOnUiThread = findViewById(R.id.btnRunOnUiThread);
        compHandler = findViewById(R.id.btnRunOnHandler);
        compLooper = findViewById(R.id.btnRunOnLooperThread);
        tvTime = findViewById(R.id.tvTime);
        sbar = findViewById(R.id.sbar);
        compMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                compute();
                update();
            }
        });
        compWrong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(() -> {
                    compute();
                    update();
                }
                ).start();
            }
        });
        compRunOnUiThread.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread() {
                    @Override
                    public void run() {
                        compute();
                        Nebenlaeufigkeit.this.runOnUiThread(() -> update());
                    }
                }.start();
            }
        });
        compHandler.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(){
                    @Override
                    public void run() {
                        compute();
                        handler.post(() -> update());
                    }
                }.start();
            }
        });
        compLooper.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                looperThread.mHandler.post(() -> {
                    compute();
                    looperThread.mHandler.post(() -> update());
                });
            }
        });
        sbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                Integer val = seekBar.getProgress();
                Log.v(TAG, "sleep = " +val);
                sleep=val;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        handler = new Handler();
    }

    private void update() {
        tvTime.setText("" + System.currentTimeMillis()) ;
    }

    private void compute() {
        try {
            Thread.sleep(sleep);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
