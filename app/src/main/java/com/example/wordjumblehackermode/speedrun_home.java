package com.example.wordjumblehackermode;

import android.app.Dialog;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

public class speedrun_home extends AppCompatActivity implements timer.OnDataPassListener {



    int rows,columns,number_words,time,h,min,sec;
    EditText rowsin,columnsin,num_words,seconds;
    Button nextbutton,set_timer;
    ImageButton timer_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.speedrun_home);
        rowsin = (EditText) findViewById(R.id.rowsin);
        columnsin = (EditText) findViewById(R.id.columnsin);
        num_words = (EditText) findViewById(R.id.num_words2);
        final MediaPlayer mediaPlayer = MediaPlayer.create(this, R.raw.click);
        timer_button = (ImageButton) findViewById(R.id.timer_button);
        timer_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openCustomDialog();
            }
        });
        nextbutton = (Button) findViewById(R.id.nextbutton);
        nextbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaPlayer.start();
                rows = Integer.parseInt(rowsin.getText().toString());
                columns = Integer.parseInt(columnsin.getText().toString());
                number_words = Integer.parseInt(num_words.getText().toString());
                openMainActivity_hacker();

            }
        });
    }
    public void openMainActivity_hacker() {

        rowsin = (EditText) findViewById(R.id.rowsin);
        columnsin = (EditText) findViewById(R.id.columnsin);
        num_words = (EditText) findViewById(R.id.num_words);
        rows = Integer.parseInt(rowsin.getText().toString());
        columns = Integer.parseInt(columnsin.getText().toString());
        number_words = Integer.parseInt(num_words.getText().toString());

        Intent intent = new Intent(this, mainactivity2_speedrun.class);
        intent.putExtra("rows", rows);
        intent.putExtra("columns", columns);
        intent.putExtra("num_words", number_words);
        intent.putExtra("hours", h);
        intent.putExtra("minutes", min);
        intent.putExtra("seconds", sec);
        startActivity(intent);
    }

    private void openCustomDialog() {
        timer customDialog = new timer(this,this);
        customDialog.show();
    }

    public void onDataPass(int hours, int minutes, int seconds) {
        h=hours;
        min=minutes;
        sec=seconds;
    }
}

