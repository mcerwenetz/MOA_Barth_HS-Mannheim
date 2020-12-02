package de.mc.numberguess;

import android.nfc.Tag;
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
    Button btnReset;
    Integer number;
    Integer tries;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.play);
        tvEcho = findViewById(R.id.tvecho);
        etGuess = findViewById(R.id.etguess);
        btnSubmit = findViewById(R.id.btnsubmit);
        btnReset =findViewById(R.id.btnreset);
        findViewById(R.id.btnreset);
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!etGuess.getText().toString().equals("Hier eingeben") && !etGuess.getText().toString().equals("")) {
                    Log.v(TAG, "guess not default or empty. calling response.");
                    getResponse();
                }
            }
        });
        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reset();
            }
        });

        Random rand = new Random();
        number = rand.nextInt(5);
        Log.v(TAG, String.format("Correct Number is: %d.", number));
        tries=0;

    }

    private void reset() {
        Log.v(TAG, "Reset was pressed");
        Random r = new Random();
        number = r.nextInt(5);
        Log.v(TAG, String.format("Correct Number is: %d.", number));
        tries=0;
        tvEcho.setText("Schätze");
    }

    private void getResponse() {
        Integer guess = parseInt(etGuess.getText().toString());
        if(guess < number) {
            Log.v(TAG, String.format("%d < %d",guess, number));
            tvEcho.setText("größer");
            tries++;
            Log.v(TAG, String.format("Tries: %d", tries));
        }
        else if(guess > number){
            Log.v(TAG, String.format("%d > %d",guess, number));
            tvEcho.setText("kleiner");
            tries++;
            Log.v(TAG, String.format("Tries: %d", tries));
        }
        else{
            Log.v(TAG, String.format("%d == %d",guess, number));
            tries++;
            tvEcho.setText("Richtig: Versuche:" + tries);
            checkHighScore();
        }
    }

    private void checkHighScore() {
        if(tries < Highscore.highScore){
            String tvEchoContent = tvEcho.getText().toString();
            tvEcho.setText(tvEchoContent + String.format("\nNeuer Highscore. \nAlter Highscore: %d", Highscore.highScore));
            Highscore.highScore=tries;
            Log.v(TAG, String.format("New static Highscore is %d", Highscore.highScore));
        }
    }
}
