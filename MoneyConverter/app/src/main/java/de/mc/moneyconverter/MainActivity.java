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

public class MainActivity extends Activity {

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
        outState.putString("quellwaehrung", quellwaehrung);
        outState.putString("zielwaehrung", zielwaehrung);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        Log.v("Restoring", "old instance was killed");
        super.onRestoreInstanceState(savedInstanceState);
        quellwaehrung = savedInstanceState.getString("quellwaehrung");
        zielwaehrung = savedInstanceState.getString("zielwaehrung");
        etAmount.setText(savedInstanceState.getString("etAmount"));
        calculation();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.moneyconverter);
//        Verschiedene Items binden
        etAmount = findViewById(R.id.etAmountSource);
        tvResult = findViewById(R.id.tvResult);
        btnSettings = findViewById(R.id.btnSettings);
        quellwaehrungsspinner = findViewById(R.id.spinnerquellwaehrung);
        zielwaehrungsspinner = findViewById(R.id.spinnerqzielwaehrung);
//Hol dir die Shared Prefs. Brauchste später für die calc.
        sp = PreferenceManager.getDefaultSharedPreferences(this);
//        Calc Objekt anlegen für die Calc
        calc = new Calculation();
//      TV initial 0 setzen
        tvResult.setText("0");
//        Baue einen Arrayadapter für die währungsnamen und gebe Sie den
        ArrayAdapter<CharSequence> waehrungsnamenadapter = ArrayAdapter.createFromResource(this, R.array.wechselkursNamen, android.R.layout.simple_spinner_item);
        waehrungsnamenadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        AdapterView.OnItemSelectedListener quellOisl = new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                quellwaehrung = (String) parent.getSelectedItem();
                Log.v(TAG, "Quellwährung is " + quellwaehrung);
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
                Log.v(TAG, "Zielwährung is " + zielwaehrung);
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
//      Füge einen TextWatcher auf etAmount hinzu. Jedes mal wenn sich der Text ändert wird
//      calc aufgerufen
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
                if (s.toString().matches("")) {
                    tvResult.setText("0");
                } else {

                    amount = new BigDecimal(s.toString());
                    Log.v(TAG, "Amount is " + amount);
                    calculation();
                }
            }
        });
//        Füge einen ocl für den Settingsbutton hinzu.
//        Drückt man drauf starten die Settings
        btnSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, PreferencesActivity.class);
                startActivity(i);
            }
        });

    }

    private void calculation() {
//        Hole Nachkommastellen aus den Settings und schreib's in nen Integer
        String keyNachkommastellen = getString(R.string.key_nachkommastellen);
        String nachkommastellen = sp.getString(keyNachkommastellen, "2");
        Integer nachkommastellenI = Integer.valueOf(nachkommastellen);
//        Sichere ab das die Range in Ordnung ist
        if (nachkommastellenI > 6) {
            nachkommastellenI = 6;
        } else if (nachkommastellenI < 0) {
            nachkommastellenI = 0;
        }
//        Gib Nachkommastellen zu logzwecken aus
        Log.v("Calc", "Nachkommastellen ist " + nachkommastellenI);
//        Wenn alles gesetzt ist:
//        Mache ne HashMap für die Währungen und ihre Umrechnungen
        if (quellwaehrung != null && zielwaehrung != null && amount != null) {
            String[] wechselkursnamen = this.getResources().getStringArray(R.array.wechselkursNamen);
            String[] wechselkurse = this.getResources().getStringArray(R.array.wechselkurse);
            LinkedHashMap<String, String> wechselkursmap = new LinkedHashMap<>();
            for (int i = 0; i < wechselkurse.length; i++) {
                wechselkursmap.put(wechselkursnamen[i], wechselkurse[i]);
            }
//            Setze das Ergebnis in den TextView.
            tvResult.setText(calc.getResult(quellwaehrung, zielwaehrung, amount, wechselkursmap, nachkommastellenI).toString());
        }
    }

}
