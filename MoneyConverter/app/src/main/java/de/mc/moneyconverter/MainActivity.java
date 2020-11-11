package de.mc.moneyconverter;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;

import java.math.BigDecimal;
import java.util.LinkedHashMap;

public class MainActivity extends Activity{

    private final String TAG = "MoneyConverter";


    private EditText etAmount;
    private TextView tvResult;
    private String quellwaehrung;
    private String zielwaehrung;
    private BigDecimal amount;
    private BigDecimal result;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.moneyconverter);
        etAmount = findViewById(R.id.etAmountSource);
        tvResult = findViewById(R.id.tvResult);
        tvResult.setText("0");

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
            //Todo: Nicht bei jeder calc neu generieren -> onCreate()
            Calculation calculation = new Calculation(quellwaehrung,zielwaehrung,amount, wechselkursmap);
            tvResult.setText(calculation.getResult().toString());
        }
    }

}
