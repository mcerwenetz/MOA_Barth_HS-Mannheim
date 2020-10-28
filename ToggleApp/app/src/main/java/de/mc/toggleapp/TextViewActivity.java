package de.mc.toggleapp;

import android.app.Activity;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;

public class TextViewActivity extends Activity {

    TextView tvDisplay;
    int text=0, fontsize=0;
//    boolean bg=false,fg=false;
    String[] texts;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.textview);
        tvDisplay = findViewById(R.id.tv_disp);
        Resources res = getResources();
        texts = res.getStringArray(R.array.textviewMsgs);
        Button btnTextview = findViewById(R.id.btnTextView);
        Button btnFontsize = findViewById(R.id.btnFontsize);
        Button btnFg = findViewById(R.id.btnFG);
        Button btnBg = findViewById(R.id.btnBG);
        btnTextview.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                onButtonClicked(v);
            }
        });
        btnFontsize.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                onButtonClicked(v);
            }
        });
        btnFg.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                onButtonClicked(v);
            }
        });
        btnBg.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                onButtonClicked(v);
            }
        });

    }

    void onButtonClicked (View view){
        int id = view.getId();
        if(id == R.id.btnTextView){
            text = (text +1) % texts.length;
            tvDisplay.setText(texts[text]);
        }else if(id == R.id.btnFontsize){
            fontsize = (fontsize == 28) ? 8:fontsize+1;
            tvDisplay.setTextSize(TypedValue.COMPLEX_UNIT_SP, fontsize);
//        }else if (id == R.id.btnFG){
//        }else if (id == R.id.btnBG){
        }
    }

}
