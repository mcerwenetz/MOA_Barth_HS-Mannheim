package de.mc.toggleapp;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.Nullable;

public class SpinnerActivity extends Activity {
    TextView tvSelected;
    Spinner spinnerRaeume;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.spinner);
        tvSelected = findViewById(R.id.tvSelected);
        spinnerRaeume = findViewById(R.id.spinnerRaeume);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.raeume, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerRaeume.setAdapter(adapter);
        AdapterView.OnItemSelectedListener oisl = new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selected = parent.getSelectedItem().toString();
                tvSelected.setText(selected);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                tvSelected.setText("");
            }
        };

        spinnerRaeume.setOnItemSelectedListener(oisl);

    }

}
