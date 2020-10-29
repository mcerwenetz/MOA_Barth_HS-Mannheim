package de.mc.toggleapp;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import androidx.annotation.Nullable;

public class CheckBoxActivity extends Activity {
    TextView tvCheckBox1, tvCheckBox2;
    CheckBox cb1, cb2, cb3;
    private CompoundButton.OnCheckedChangeListener occl = new CompoundButton.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            String msg = String.format("cb1: %s, cb2: %s, cb3: %s", cb1.isChecked(), cb2.isChecked(),cb3.isChecked());
            tvCheckBox1.setText(msg);
        }
    };
    private View.OnClickListener ocset = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            cb1.setChecked(true);
            cb2.setChecked(false);
            cb3.setChecked(true);
        }
    };
    private View.OnClickListener oclu = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String msg = String.format("cb1: %s, cb2: %s, cb3: %s", cb1.isChecked(), cb2.isChecked(),cb3.isChecked());
            tvCheckBox2.setText(msg);
        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.checkbox);
        cb1 = findViewById(R.id.checkbox1);
        cb2 = findViewById(R.id.checkbox2);
        cb3 = findViewById(R.id.checkbox3);
        tvCheckBox1=findViewById(R.id.tvCheckBox1);
        tvCheckBox2=findViewById(R.id.tvCheckBox2);

        Button update = findViewById(R.id.btnUpdateCheckbox);
        update.setOnClickListener(oclu);
        Button set = findViewById(R.id.btnSetCheckbox);
        set.setOnClickListener(ocset);

        CheckBox[] cbs = {cb1, cb2, cb3};

        for (CheckBox c : cbs){
            c.setOnCheckedChangeListener(occl);
        }

    }

}
