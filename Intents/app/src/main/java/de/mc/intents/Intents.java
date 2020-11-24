package de.mc.intents;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;


import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Locale;


public class Intents extends AppCompatActivity {

    private static final String TAG = "Intent";
    final static String EXTRA_KEY = Intents.class.getPackage().getName() + ".Greet";

    Button explizit;
    Button tel;
    Button maps;
    Button web;
    Button manualImplicit;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.intent);
        explizit = findViewById(R.id.btnExplicit);
        tel = findViewById(R.id.btnTelHSMA);
        maps = findViewById(R.id.btnLocHSMA);
        web = findViewById(R.id.btnWeb);
        manualImplicit = findViewById(R.id.btnImplicitManual);
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
        web.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickImplicitWeb(v);
            }
        });
        manualImplicit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickImplicitManuak(v);
            }
        });
    }

    private void onClickImplicitManuak(View v) {
        int[] zahlen = {17, 42};
        int zahlIndex = zahlen.length-1;
        String host = "de.mc.intent";
        Log.v(TAG, "onClickManual");
        String scheme="vorliebe";
        zahlIndex = (zahlIndex+1) % zahlen.length;
        String path = String.format(Locale.getDefault(), "zahl/%d",zahlen[zahlIndex]);
        String url = String.format("%s://%s/%s", scheme, host,path);
        Uri uri = Uri.parse(url);
        String action = Intent.ACTION_VIEW;
        Intent intent = new Intent(action);
        intent.setData(uri);
        Log.v(TAG, "Intent auf dem weg");
        startActivity(intent);
    }

    private void onClickImplicitMaps(View v) {
        Log.v(TAG, "Mapedimaps");
        Uri uri = Uri.parse("geo:49.46436,8.48724");
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent);
    }

    private void onClickImplicitWeb(View v) {
        Log.v(TAG, "WebWub");
        Uri uri = Uri.parse("https://mariuscerwenetz.de");
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
