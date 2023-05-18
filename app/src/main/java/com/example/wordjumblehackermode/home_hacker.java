package com.example.wordjumblehackermode;

import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;


public class home_hacker extends AppCompatActivity {


    Button classic,speedrun;
    SharedPreferences sharedPreferencesnew;
    private static final String SHARED_PREF_NAME="mypref";
    private static final String KEY_SCORE="score";


    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_layout_hacker);

        sharedPreferencesnew = getSharedPreferences(SHARED_PREF_NAME,MODE_PRIVATE);
        int score = sharedPreferencesnew.getInt(KEY_SCORE,0);
        TextView best_score = findViewById(R.id.best_score);
        best_score.setText("Best Score:" + score);
        final MediaPlayer mediaPlayer = MediaPlayer.create(this,R.raw.click);
        speedrun = (Button) findViewById(R.id.speedrun_button);
        classic = (Button) findViewById(R.id.classic_button);
        speedrun.setOnClickListener (new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaPlayer.start();
                openspeedrun();

            }
        });
        classic.setOnClickListener (new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaPlayer.start();
                openclassic();

            }
        });
    }
    public void openspeedrun() {

        Intent intent = new Intent(this, speedrun_home.class);
        startActivity(intent);
    }
    public void openclassic() {

        Intent intent = new Intent(this, classic_home.class);
        startActivity(intent);
    }
}