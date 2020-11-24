package de.mc.intents;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;


import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;


public class Intents extends AppCompatActivity {

    private static final String TAG = "Intent";
    final static String EXTRA_KEY = Intents.class.getPackage().getName() + ".Greet";

    Button explizit;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.intent);
        explizit = findViewById(R.id.btnExplcit);
        explizit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickExplicit(v);
            }
        });
    }

    public void onClickExplicit(View view){
        Log.v(TAG, "explicit was clicked");
        Intent intent = new Intent(this, Ziel.class);
        intent.putExtra(EXTRA_KEY, "Hallo");
        startActivity(intent);
    }
}
