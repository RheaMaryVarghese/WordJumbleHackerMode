package com.example.wordjumblehackermode;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class classic_home extends AppCompatActivity {



    int rows,columns,words;
    EditText rowsin,columnsin,num_words;
    Button nextbutton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.classic_home);
        rowsin = (EditText) findViewById(R.id.rowsin);
        columnsin = (EditText) findViewById(R.id.columnsin);
        num_words = (EditText) findViewById(R.id.num_words);
        final MediaPlayer mediaPlayer = MediaPlayer.create(this, R.raw.click);
        nextbutton = (Button) findViewById(R.id.nextbutton);
        nextbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaPlayer.start();

                rows = Integer.parseInt(rowsin.getText().toString());
                columns = Integer.parseInt(columnsin.getText().toString());
                words = Integer.parseInt(num_words.getText().toString());
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
            words = Integer.parseInt(num_words.getText().toString());

            Intent intent = new Intent(this, MainActivity_hacker.class);
            intent.putExtra("rows", rows);
            intent.putExtra("columns", columns);
            intent.putExtra("words", words);
            startActivity(intent);
        }
    }

