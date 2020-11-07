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

    private RadioGroup rgSource;
    private RadioGroup rgDest;
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
        rgSource = findViewById(R.id.rgSource);
        rgDest = findViewById(R.id.rgDest);
        etAmount = findViewById(R.id.etAmountSource);
        tvResult = findViewById(R.id.tvResult);
        tvResult.setText("0");
        setAllRadios();

        rgSource.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton checked = findViewById(checkedId);
                quellwaehrung = (String) checked.getText();
                Log.v(TAG, "Source checked it's" + quellwaehrung);
                calculation();
            }
        });

        rgDest.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton checked = findViewById(checkedId);
                zielwaehrung = (String) checked.getText();
                Log.v(TAG, "Dest checked it's" + zielwaehrung);
                calculation();
            }
        });

        etAmount.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
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

    private void setAllRadios() {
        String[] wechselkursnamen = this.getResources().getStringArray(R.array.wechselkursNamen);
        RadioGroup rgSource = findViewById(R.id.rgSource);
        RadioGroup rgDest = findViewById(R.id.rgDest);
        //Todo: RadioButtons automatisch generieren
        for(int i=0; i < wechselkursnamen.length; i++){
            ((RadioButton) rgSource.getChildAt(i)).setText(wechselkursnamen[i]);
            ((RadioButton) rgDest.getChildAt(i)).setText(wechselkursnamen[i]);
        }

    }

    private void calculation() {
        if (quellwaehrung != null && zielwaehrung != null && amount != null){
            String[] wechselkursnamen = this.getResources().getStringArray(R.array.wechselkursNamen);
            String[] wechselkurse = this.getResources().getStringArray(R.array.wechselkurse);
            LinkedHashMap<String, String> wechselkursmap = new LinkedHashMap<String, String>();
            for (int i = 0; i < wechselkurse.length; i++){
                wechselkursmap.put(wechselkursnamen[i], wechselkurse[i]);
            }
            Calculation calculation = new Calculation(quellwaehrung,zielwaehrung,amount, wechselkursmap);
            tvResult.setText(calculation.getResult().toString());
        }
    }

}
