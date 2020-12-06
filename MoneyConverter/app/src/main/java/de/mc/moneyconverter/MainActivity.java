package de.mc.moneyconverter;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
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
    private Button btnSettings;
    private SharedPreferences sp;


    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        Log.v("Killing Activity", "saving stuff");
        super.onSaveInstanceState(outState);
        outState.putString("etAmount", etAmount.getText().toString());
        outState.putString("quellwaehrung", quellwaehrung.toString());
        outState.putString("zielwaehrung", zielwaehrung.toString());
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        Log.v("Restoring", "old instance was killed");
        super.onRestoreInstanceState(savedInstanceState);
        quellwaehrung=savedInstanceState.getString("quellwaehrung");
        zielwaehrung=savedInstanceState.getString("zielwaehrung");
        etAmount.setText(savedInstanceState.getString("etAmount"));
        calculation();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        //Todo: Saved instance nutzen
        super.onCreate(savedInstanceState);
        setContentView(R.layout.moneyconverter);
        etAmount = findViewById(R.id.etAmountSource);
        //Todo: Zielwährungsfeld soll et werden. Die
        tvResult = findViewById(R.id.tvResult);
        btnSettings=findViewById(R.id.btnSettings);

        sp = PreferenceManager.getDefaultSharedPreferences(this);

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



        quellwaehrungsspinner.setAdapter(waehrungsnamenadapter);
        zielwaehrungsspinner.setAdapter(waehrungsnamenadapter);

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
                Log.v(TAG, "s is " + s.toString());
                if(s.toString().matches("")) {
                    tvResult.setText("0");
                }else{

                    amount = new BigDecimal(s.toString());
                    Log.v(TAG, "Amount is " + amount);
                    calculation();
                }
            }
        });

        btnSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, PreferencesActivity.class);
                startActivity(i);
            }
        });

    }

    private void calculation() {
        String keyNachkommastellen = getString(R.string.key_nachkommastellen);
        String nachkommastellen = sp.getString(keyNachkommastellen,"4");
        Integer nachkommastellenI = Integer.valueOf(nachkommastellen);
        Log.v("Calc", "Nachkommastellen ist " +  nachkommastellenI);
        if (quellwaehrung != null && zielwaehrung != null && amount != null){
            String[] wechselkursnamen = this.getResources().getStringArray(R.array.wechselkursNamen);
            String[] wechselkurse = this.getResources().getStringArray(R.array.wechselkurse);
            LinkedHashMap<String, String> wechselkursmap = new LinkedHashMap<String, String>();
            for (int i = 0; i < wechselkurse.length; i++){
                wechselkursmap.put(wechselkursnamen[i], wechselkurse[i]);
            }
            tvResult.setText(calc.getResult(quellwaehrung,zielwaehrung,amount, wechselkursmap, nachkommastellenI).toString());
        }
    }

}
