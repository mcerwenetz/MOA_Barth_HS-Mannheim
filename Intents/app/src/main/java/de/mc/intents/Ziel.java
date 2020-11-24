package de.mc.intents;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class Ziel extends AppCompatActivity {
    private static final String TAG = "Ziel";
    private TextView tvExplicit;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.v(TAG, "onCreate explicit");
        setContentView(R.layout.ziel);
        tvExplicit = findViewById(R.id.tvZiel);
        Bundle bundle = getIntent().getExtras();
        String value = bundle.getString(Intents.EXTRA_KEY, "none");
        tvExplicit.setText(value);
    }
}
