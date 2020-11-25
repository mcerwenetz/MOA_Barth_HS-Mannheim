package de.mc.threading;

import android.app.Activity;
import android.os.Bundle;
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
    TextView tvTime;
    SeekBar sbar;
    Integer sleep = 0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nl);
        compMain = findViewById(R.id.btnMainThread);
        tvTime = findViewById(R.id.tvTime);
        sbar = findViewById(R.id.sbar);
        compMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                compute();
                update();
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
