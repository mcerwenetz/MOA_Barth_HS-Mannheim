package de.mc.numberguess;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

import static java.lang.Integer.parseInt;


public class Play extends AppCompatActivity {
    private static final String TAG = "Play";
    TextView tvEcho;
    EditText etGuess;
    Button btnSubmit;
    Integer number;
    Integer tries;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.play);
        tvEcho = findViewById(R.id.tvecho);
        etGuess = findViewById(R.id.etguess);
        btnSubmit = findViewById(R.id.btnsubmit);
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!etGuess.getText().toString().equals("Hier eingeben") && !etGuess.getText().toString().equals("")) {
                    Log.v(TAG, "guess not default or empty. calling response.");
                    getResponse();
                }
            }
        });

        Random rand = new Random();
        number = rand.nextInt()%5;
        tries=0;

    }

    private void getResponse() {
        Integer guess = parseInt(etGuess.getText().toString());
        if(guess < number) {
            Log.v(TAG, String.format("%d < %d",guess, number));
            tvEcho.setText("größer");
            tries++;
        }
        else if(guess > number){
            Log.v(TAG, String.format("%d > %d",guess, number));
            tvEcho.setText("kleiner");
            tries++;
        }
        else{
            Log.v(TAG, String.format("%d == %d",guess, number));
            tvEcho.setText("Richtig: Versuche:" + tries);
        }
    }
}
