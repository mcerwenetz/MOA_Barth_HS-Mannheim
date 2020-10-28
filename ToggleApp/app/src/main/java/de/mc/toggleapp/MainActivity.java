package de.mc.toggleapp;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.Nullable;

import static android.content.ContentValues.TAG;

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

    private final View.OnClickListener onButtonListener = new View.OnClickListener(){
        @Override
        public void onClick(final View v) {
            int buttonID = v.getId();
            if (buttonID == R.id.btn_toggle){
                current=(current+1)%greetings.length;
                tvDisplay.setText(greetings[current]);
            }else if (buttonID == R.id.btn_finish) {
                finish();
            }else if (buttonID == R.id.btn_image){
                Log.v(TAG, "Image Button clicked");
            }
        }
    };
}
