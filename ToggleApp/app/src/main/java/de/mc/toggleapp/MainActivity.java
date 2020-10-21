package de.mc.toggleapp;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;

public class MainActivity extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.v("MainActivity", "onCreate");
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
}
