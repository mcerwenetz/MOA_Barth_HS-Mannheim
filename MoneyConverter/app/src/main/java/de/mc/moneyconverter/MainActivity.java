package de.mc.moneyconverter;

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

import java.math.BigDecimal;
import java.util.LinkedHashMap;

public class MainActivity extends Activity{

    private final String TAG = "MoneyConverter";


    private EditText etAmount;
    private TextView tvResult;
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
        super.onCreate(savedInstanceState);
        setContentView(R.layout.moneyconverter);
        etAmount = findViewById(R.id.etAmountSource);
        tvResult = findViewById(R.id.tvResult);
        quellwaehrungsspinner = findViewById(R.id.spinnerquellwaehrung);
        zielwaehrungsspinner = findViewById(R.id.spinnerqzielwaehrung);

        calc = new Calculation();

        tvResult.setText("0");
        ArrayAdapter<CharSequence> waehrungsnamenadapter = ArrayAdapter.createFromResource(this, R.array.wechselkursNamen, android.R.layout.simple_spinner_item);
        waehrungsnamenadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        AdapterView.OnItemSelectedListener quellOisl = new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                quellwaehrung = (String) parent.getSelectedItem();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        };


        AdapterView.OnItemSelectedListener zielOisl = new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                zielwaehrung = (String) parent.getSelectedItem();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        };



        quellwaehrungsspinner.setAdapter(waehrungsnamenadapter);
        quellwaehrungsspinner.setAdapter(waehrungsnamenadapter);

        quellwaehrungsspinner.setOnItemSelectedListener(quellOisl);
        zielwaehrungsspinner.setOnItemSelectedListener(zielOisl);

        etAmount.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                //Todo: If für Big Decimal
                try{
                    amount = new BigDecimal(s.toString());
                    Log.v(TAG, "Amount is " + amount);
                } catch (NumberFormatException e){
                    tvResult.setText("0");
                    amount = null;
                }
                calculation();
            }
        });

    }

    private void calculation() {
        if (quellwaehrung != null && zielwaehrung != null && amount != null){
            String[] wechselkursnamen = this.getResources().getStringArray(R.array.wechselkursNamen);
            String[] wechselkurse = this.getResources().getStringArray(R.array.wechselkurse);
            LinkedHashMap<String, String> wechselkursmap = new LinkedHashMap<String, String>();
            for (int i = 0; i < wechselkurse.length; i++){
                wechselkursmap.put(wechselkursnamen[i], wechselkurse[i]);
            }
            tvResult.setText(calc.getResult(quellwaehrung,zielwaehrung,amount, wechselkursmap).toString());
        }
    }

}
