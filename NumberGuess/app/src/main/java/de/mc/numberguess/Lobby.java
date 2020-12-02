package de.mc.numberguess;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class Lobby extends AppCompatActivity {

    private static final String TAG = "Lobby" ;
    Button startGame;
    Button highscore;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lobby);
        startGame=findViewById(R.id.btnStartGame);
        highscore=findViewById(R.id.btnHighscore);
        startGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickedStart();
            }
        });
        highscore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickHighscore();
            }

        });

    }

    private void onClickHighscore() {
        Log.v(TAG, "Highscore was clicked");
        Intent intent = new Intent(this, Play.class);
        startActivity(intent);
    }



    private void onClickedStart() {
        Log.v(TAG, "start game was clicked");
        Intent intent = new Intent(this, Play.class);
        startActivity(intent);
    }
}
