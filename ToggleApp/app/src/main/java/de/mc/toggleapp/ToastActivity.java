package de.mc.toggleapp;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class ToastActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.toast);
        Button btnLongToast=findViewById(R.id.btnLongToast);
        Button btnShortToast=findViewById(R.id.btnShortToast);
        btnShortToast.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Toast toast = Toast.makeText(ToastActivity.this, "Short Toast", Toast.LENGTH_SHORT);
                toast.show();
            }
        });
        btnLongToast.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Toast toast = Toast.makeText(ToastActivity.this, "Long Toast", Toast.LENGTH_LONG);
                toast.show();
            }
        });

    }
}
