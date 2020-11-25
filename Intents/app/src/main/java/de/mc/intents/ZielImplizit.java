package de.mc.intents;

import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import org.w3c.dom.Text;

import java.util.List;
import java.util.Locale;

public class ZielImplizit extends AppCompatActivity {
    private static final String TAG = "ZielImplicit";
    private TextView tv;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.zielimplizit);
        Log.v(TAG, "onCreateImplicitManual");
        tv = findViewById(R.id.tvImplicitManual);
        updateZahl();
    }

    private void updateZahl() {
        Log.v(TAG, "updateZahl");
        if(getIntent() == null){
            Log.v(TAG, "no intent. aborting.");
            return;
        }
        Uri uri = getIntent().getData();
        List<String> segments = uri.getPathSegments();
        if(segments.size() != 2 || !segments.get(0).equals("zahl")){
            Log.v(TAG, "Intent uri not working" +uri);
            return;
        }
        try {
            int zahl = Integer.parseInt(segments.get(1));
            String s = String.format(Locale.getDefault(), "%d", zahl);
            tv.setText(s);
        }catch (NumberFormatException num){
            Log.v(TAG, "NaN");
        }
    }
}
