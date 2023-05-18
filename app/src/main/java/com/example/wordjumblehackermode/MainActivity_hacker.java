package com.example.wordjumblehackermode;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity_hacker extends AppCompatActivity {
    public static final String EXTRA_TEXT1 = "com.example.application.example.EXTRA_TEXT1";
    public static final String EXTRA_TEXT2 = "com.example.application.example.EXTRA_TEXT2";
    String word,clue;
    EditText wordin,cluein;
    Button startbutton,addbutton;
    int rows,columns,words,p;
    ArrayList<String> list_words,list_clues;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_hacker);
        wordin = (EditText) findViewById(R.id.wordin);
        cluein = (EditText) findViewById(R.id.cluein);
        final MediaPlayer mediaPlayer = MediaPlayer.create(this, R.raw.click);
        startbutton = (Button) findViewById(R.id.startbutton);
        Intent Intent = getIntent();
        rows = Intent.getIntExtra("rows", 0);
        columns = Intent.getIntExtra("columns", 0);
        words = Intent.getIntExtra("words", 0);
        list_words = new ArrayList<String>();
        list_clues = new ArrayList<String>();
        startbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaPlayer.start();
                openMainActivity2();
            }
        });
        addbutton = (Button) findViewById(R.id.addbutton);
        addbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mediaPlayer.start();
                wordin = (EditText) findViewById(R.id.wordin);
                cluein = (EditText) findViewById(R.id.cluein);
                word = wordin.getText().toString();
                clue = cluein.getText().toString();

                    p+=1;
                    list_words.add(word);
                    list_clues.add(clue);
                if (p<words){
                    Toast.makeText(MainActivity_hacker.this, "More", Toast.LENGTH_SHORT).show();
                    wordin.setText("");
                    cluein.setText("");
                }
                else if (p==words){
                    Toast.makeText(MainActivity_hacker.this, "Click on Start", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }


    public void openMainActivity2() {

        Intent intent = new Intent(this, MainActivity2_Hacker.class);
        intent.putExtra("rows", rows);
        intent.putExtra("columns", columns);
        intent.putExtra("words", words);
        intent.putStringArrayListExtra("list_words", list_words);
        intent.putStringArrayListExtra("list_clues", list_clues);

        startActivity(intent);
    }
}

