package de.mc.intents;

import android.content.Intent;
import android.net.Uri;
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
    Button tel;
    Button maps;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.intent);
        explizit = findViewById(R.id.btnExplicit);
        tel = findViewById(R.id.btnTelHSMA);
        maps = findViewById(R.id.btnLocHSMA);
        explizit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickExplicit(v);
            }
        });
        tel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickImplicitTel(v);
            }
        });
        maps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickImplicitMaps(v);
            }
        });

    }

    private void onClickImplicitMaps(View v) {
        Log.v(TAG, "Mapedimaps");
        Uri uri = Uri.parse("geo:49.46436,8.48724");
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent);
    }

    private void onClickImplicitTel(View v) {
        Log.v(TAG, "Ringring");
        Uri uri = Uri.parse("tel:017638547923");
        Intent intent = new Intent(Intent.ACTION_DIAL, uri);
        startActivity(intent);
    }

    public void onClickExplicit(View view){
        Log.v(TAG, "explicit was clicked");
        Intent intent = new Intent(this, Ziel.class);
        intent.putExtra(EXTRA_KEY, "Hallo");
        startActivity(intent);
    }
}
