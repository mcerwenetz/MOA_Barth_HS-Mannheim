package de.mc.toggleapp;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;

public class RadioGroupActivity extends Activity {

    TextView tvColor;
    RadioGroup rgColor;

    private final RadioGroup.OnCheckedChangeListener occl = new RadioGroup.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            if (checkedId == R.id.rbRed) {
                tvColor.setText("Rot");
            } else if (checkedId == R.id.rbBlue) {
                tvColor.setText("Blau");
            } else if (checkedId == R.id.rbGreen) {
                tvColor.setText("Grün");
            }
        }
    };
    private final View.OnClickListener oclNext = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            int checked = rgColor.getCheckedRadioButtonId();
            if (checked == R.id.rbRed) {
                rgColor.check(R.id.rbGreen);
            } else if (checked == R.id.rbGreen) {
                rgColor.check(R.id.rbBlue);
            } else if (checked == R.id.rbBlue) {
                rgColor.check(R.id.rbRed);
            }
        }
    };


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.radiogroup);
        Button btnNext = findViewById(R.id.btnNext);
        btnNext.setOnClickListener(oclNext);
        rgColor = findViewById(R.id.rgColor);
        tvColor = findViewById(R.id.tvColor);
        rgColor.setOnCheckedChangeListener(occl);

    }

}
