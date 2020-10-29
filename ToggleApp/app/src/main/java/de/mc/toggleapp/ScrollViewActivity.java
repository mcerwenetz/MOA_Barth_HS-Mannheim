package de.mc.toggleapp;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;

import org.w3c.dom.Text;

public class ScrollViewActivity extends Activity {
    TextView tvSelected;
    Spinner spinnerRaeume;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.scrollview);
        LinearLayout layout = findViewById(R.id.llScrollview);
        ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        View.OnClickListener ocl = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CharSequence msg = ((TextView) v).getText();
                Toast.makeText(ScrollViewActivity.this, msg, Toast.LENGTH_SHORT).show();
            }
        };

        for(int i =0 ; i < 123; i +=1){
            TextView textView = new TextView(this);
            textView.setLayoutParams(layoutParams);
            textView.setText(String.format("TextView %03d",i));
            textView.setClickable(true);
            textView.setOnClickListener(ocl);
            layout.addView(textView);
        }

    }

}
