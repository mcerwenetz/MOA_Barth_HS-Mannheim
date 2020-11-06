package de.mc.moneyconverter;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;

import java.math.BigDecimal;

public class MainActivity extends Activity{

    private final String TAG = "MoneyConverer";

    private RadioGroup rgSource;
    private RadioGroup rgDest;
    private EditText etAmount;
    private TextView tvResult;
    private String quellwaehrung;
    private String zielwaehrung;
    private BigDecimal amount;
    private BigDecimal result = new BigDecimal("0");


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.moneyconverter);
        rgSource = findViewById(R.id.rgSource);
        rgDest = findViewById(R.id.rgDest);
        etAmount = findViewById(R.id.etAmountSource);
        tvResult = findViewById(R.id.tvResult);

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
                    amount = null;
                }
                calculation();
            }
        });

    }

    private void calculation() {
        if (quellwaehrung != null && zielwaehrung != null && amount != null){
            String msg = "calculation has been called.\n Parameters are: quellwährung: " +quellwaehrung
                    + " zielwaehrung: " + zielwaehrung + " amount: "+amount;
            Log.v(TAG, msg );
            //Umrechnen in Euro
            BigDecimal amountInEuro=null;
            if(quellwaehrung.equals("EUR")){
                amountInEuro=amount;
            }
            else if (quellwaehrung.equals("USD")){
                amountInEuro = amount.multiply(new BigDecimal("0.84"));
            }
            else if (quellwaehrung.equals("GRD")){
                amountInEuro = amount.multiply(new BigDecimal("0.002934"));
            }
            //Umrechnen in Zielwährung
            if (zielwaehrung.equals("EUR")){
                result = amountInEuro;
            }
            else if (zielwaehrung.equals("USD")){
                result = amountInEuro.multiply(new BigDecimal("1.17"));
            }
            else if (zielwaehrung.equals("GRD")){
                result = amountInEuro.multiply(new BigDecimal("340.75"));
            }
            result = result.setScale(2, BigDecimal.ROUND_HALF_EVEN);
            tvResult.setText(result.toString());
        }
    }

}
