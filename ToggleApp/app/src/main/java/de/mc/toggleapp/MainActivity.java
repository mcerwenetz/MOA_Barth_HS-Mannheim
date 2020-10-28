package de.mc.toggleapp;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.Nullable;

public class MainActivity extends Activity {

    private TextView tvDisplay;
    private String[] greetings ;
    private int current;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        greetings=getResources().getStringArray(R.array.greetings);
        current=0;
        setContentView(R.layout.main);
        tvDisplay = findViewById(R.id.tv_display);
        Button btnToggle = findViewById(R.id.btn_toggle);
        Button btnfinish = findViewById(R.id.btn_finish);
        ImageButton btnImage = findViewById(R.id.btn_image);
        btnImage.setOnClickListener(onButtonListener);
        btnfinish.setOnClickListener(onButtonListener);
        btnToggle.setOnClickListener(onButtonListener);
        tvDisplay.setText(greetings[current]);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.v("MainActivity", "onDestroy");
    }

    private View.OnClickListener onToggleListener = new View.OnClickListener(){

        @Override
        public void onClick(View v) {
            current = (current+1)%greetings.length;
            tvDisplay.setText(greetings[current]);
        }
    };
    private View.OnClickListener onFinishListener = new View.OnClickListener(){

        @Override
        public void onClick(View v) {
            finish();
        }
    };

}
