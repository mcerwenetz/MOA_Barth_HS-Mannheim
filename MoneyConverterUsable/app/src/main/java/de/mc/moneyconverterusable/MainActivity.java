package de.mc.moneyconverterusable;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.Nullable;

import org.w3c.dom.Text;

import java.math.BigDecimal;
import java.util.LinkedHashMap;

public class MainActivity extends Activity{

    private final String TAG = "MoneyConverter";


    private TextView tvDest;
    private TextView tvSource;
    private EditText etSource;
    private EditText etDest;
    private Spinner quellwaehrungsspinner;
    private Spinner zielwaehrungsspinner;
    private String quellwaehrung;
    private String zielwaehrung;
    private BigDecimal amount;
    private BigDecimal result;
    private Calculation calc;

    public MainActivity() {
    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        //Todo: Saved instance nutzen
        super.onCreate(savedInstanceState);
        setContentView(R.layout.moneyconverter);
        //Todo: Zielwährungsfeld soll et werden. Die
        etSource = findViewById(R.id.etSource);
        etDest = findViewById(R.id.etDest);

        resetAllEts();

        quellwaehrungsspinner = findViewById(R.id.spinnerquellwaehrung);
        zielwaehrungsspinner = findViewById(R.id.spinnerqzielwaehrung);

        etSource.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                Log.v(TAG, "s is " + s.toString());
                if(s.toString().matches("")) {
                    resetAllEts();
                }else{

                    amount = new BigDecimal(s.toString());
                    Log.v(TAG, "Amount is " + amount);
                    calculation();
                }
            }
        });
        etDest.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                Log.v(TAG, "s is " + s.toString());
                if(s.toString().matches("")) {
                    resetAllEts();
                }else{

                    amount = new BigDecimal(s.toString());
                    Log.v(TAG, "Amount is " + amount);
                    calculation();
                }
            }
        });

        calc = new Calculation();

        ArrayAdapter<CharSequence> waehrungsnamenadapter = ArrayAdapter.createFromResource(this, R.array.wechselkursNamen, android.R.layout.simple_spinner_item);
        waehrungsnamenadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        quellwaehrungsspinner.setAdapter(waehrungsnamenadapter);
        zielwaehrungsspinner.setAdapter(waehrungsnamenadapter);

        AdapterView.OnItemSelectedListener quellOisl = new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                quellwaehrung = (String) parent.getSelectedItem();
                Log.v(TAG, "Quellwährung is "+quellwaehrung);
                calculation();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        };
        AdapterView.OnItemSelectedListener zielOisl = new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                zielwaehrung = (String) parent.getSelectedItem();
                Log.v(TAG, "Zielwährung is "+zielwaehrung);
                calculation();


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        };

        quellwaehrungsspinner.setOnItemSelectedListener(quellOisl);
        zielwaehrungsspinner.setOnItemSelectedListener(zielOisl);








    }

    private void calculation() {
        if (quellwaehrung != null && zielwaehrung != null && amount != null){
            String[] wechselkursnamen = this.getResources().getStringArray(R.array.wechselkursNamen);
            String[] wechselkurse = this.getResources().getStringArray(R.array.wechselkurse);
            LinkedHashMap<String, String> wechselkursmap = new LinkedHashMap<String, String>();
            for (int i = 0; i < wechselkurse.length; i++){
                wechselkursmap.put(wechselkursnamen[i], wechselkurse[i]);
            }
//            tvResult.setText(calc.getResult(quellwaehrung,zielwaehrung,amount, wechselkursmap).toString());
        }
    }

    private void resetAllEts(){
        etSource.setText("0", TextView.BufferType.EDITABLE);
        etDest.setText("0", TextView.BufferType.EDITABLE);
    }
}
