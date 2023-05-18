package com.example.wordjumblehackermode;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;




public class mainactivity2_speedrun extends AppCompatActivity {

    SharedPreferences sharedPreferences;
    ImageButton ImageButton;
    Button Okbutton, reset, check, home, playagain;

    String guess = "",chosen_word,chosen_clue;

    int score=300, count = 3, len, blanks_filled = 0, k = 0, rows, columns, product, no_words,size,m=0,hour,min,sec,initial_time;
    List<Integer> ids, click_ids, button_ids;
    List<Character> letters, alphabets;


    private static final String SHARED_PREF_NAME = "mypref";
    private static final String KEY_SCORE = "score";
    MediaPlayer mediaPlayer;
    ArrayList<String> list_words, list_clues;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mainactivity2_speedrun);

        mediaPlayer = MediaPlayer.create(this, R.raw.click);
        sharedPreferences = getSharedPreferences(SHARED_PREF_NAME, MODE_PRIVATE);
        ImageButton = (ImageButton) findViewById(R.id.info);
        ImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialog();
            }
        });

        Intent intent = getIntent();
        rows = intent.getIntExtra("rows", 0);
        columns = intent.getIntExtra("columns", 0);
        no_words = intent.getIntExtra("words", 0);
        list_words = intent.getStringArrayListExtra("list_words");
        list_clues = intent.getStringArrayListExtra("list_clues");
        hour = intent.getIntExtra("hours", 0);
        min = intent.getIntExtra("mins", 0);
        sec = intent.getIntExtra("secs", 0);

        initial_time=(hour*3600)+(min*60)+sec;
        score+=initial_time;




        GridLayout gridLayout = findViewById(R.id.gridLayout);



        letters = new ArrayList<>();
        for (int i = 0; i < no_words; i++) {
            String word = list_words.get(i);
            for (char letter : word.toCharArray()) {
                letters.add(letter);
            }
        }

        String alpha = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

        alphabets = new ArrayList<>();
        for (char letter : alpha.toCharArray()) {
            alphabets.add(letter);
        }
        Collections.shuffle(alphabets);

        product = rows * columns;
        size = letters.size();
        for (int i = 0; i < product - size; i++) {
            letters.add(alphabets.get(i));
        }
        Collections.shuffle(letters);

        ids = new ArrayList<>();
        click_ids = new ArrayList<>();
        button_ids = new ArrayList<>();

        for (int i = 0; i < product; i++) {
            Button button = new Button(this);
            int buttonId = View.generateViewId();
            button.setId(buttonId);
            button_ids.add(buttonId);
            button.setText(String.valueOf(letters.get(i % letters.size())));
            button.setTextSize(24);
            button.setAllCaps(true);
            button.setBackgroundColor(Color.parseColor("#E4A6EF"));
            button.setOnClickListener(new View.OnClickListener() {
                private void setWorkings(CharSequence buttonText) {

                    guess = guess + buttonText;
                    TextView ans = findViewById(ids.get(k));
                    ans.setText(buttonText);
                    k += 1;

                }

                @Override
                public void onClick(View v) {
                    if (k == len) {
                        Toast.makeText(mainactivity2_speedrun.this, "You've entered maximum no. of letters!", Toast.LENGTH_SHORT).show();

                    } else {
                    mediaPlayer.start();
                    button.setTextColor(Color.parseColor("#ffffff"));
                    button.setBackgroundColor(Color.parseColor("#6e4887"));
                    button.setEnabled(false);
                    //int buttonId = View.generateViewId();
                    //button.setId(buttonId);
                    click_ids.add(buttonId);
                    CharSequence buttonText = button.getText();
                    setWorkings(buttonText);
                    blanks_filled += 1;
                    }

                }

            });


            GridLayout.LayoutParams params = new GridLayout.LayoutParams();
            gridLayout.setRowCount(rows);
            gridLayout.setColumnCount(columns);
            params.width = 0;
            params.height = GridLayout.LayoutParams.WRAP_CONTENT;
            params.columnSpec = GridLayout.spec(i % columns, 1f);
            params.rowSpec = GridLayout.spec(i / columns, 1f);
            params.setMargins(20, 20, 20, 20);
            button.setLayoutParams(params);
            gridLayout.addView(button);
        }

        chosen_word = list_words.get(m);
        chosen_clue = list_clues.get(m);
        myFunction(chosen_word,chosen_clue);

    }

    public void myFunction(String chosen_word,String chosen_clue) {
        LinearLayout lm = (LinearLayout) findViewById(R.id.linearmain);
        LinearLayout.LayoutParams paramslin = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        len=chosen_word.length();
        for (int i = 0; i < len; i++) {
            TextView tv = new TextView(this);
            int tvId = View.generateViewId();
            tv.setId(tvId);
            ids.add(tvId);
            //tv.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            tv.setPadding(20, 20, 20, 20);
            tv.setTextSize(30);
            tv.setTextColor(Color.parseColor("#ffffff"));
            tv.setAllCaps(true);
            tv.setBackgroundColor(Color.parseColor("#6e4887"));
            tv.setText("_");
            lm.addView(tv);
        }
    }
    public void resetOnClick (View view)
    {
        mediaPlayer.start();
        guess = "";
        Toast.makeText(this, "Reset Successful", Toast.LENGTH_SHORT).show();
        for (int b=0;b<k;b++) {
            TextView ans = findViewById(ids.get(b));
            ans.setText("_");
        }
        for (int i=0;i<click_ids.size();i++) {
            Button button = findViewById(click_ids.get(i));
            button.setTextColor(Color.parseColor("#000000"));
            button.setBackgroundColor(Color.parseColor("#E4A6EF"));
            button.setEnabled(true);
        }
        click_ids.clear();
        k=0;
        blanks_filled=0;
    }

    public void checkOnClick(View view) {
        mediaPlayer.start();
        if (blanks_filled != len) {
            Toast.makeText(this, "Fill all the blanks!!", Toast.LENGTH_SHORT).show();
        } else if (guess.equals(chosen_word)) {
            Toast.makeText(this, "Correct!", Toast.LENGTH_SHORT).show();
            m = m + 1;
            if (m == no_words) {
                if (score > sharedPreferences.getInt(KEY_SCORE, 0)) {
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putInt(KEY_SCORE, score);
                    editor.apply();
                }
                showDialog();
            } else {
                chosen_word = list_words.get(m);
                chosen_clue = list_clues.get(m);
                for (int i = 0; i < len; i++) {
                    LinearLayout lm = (LinearLayout) findViewById(R.id.linearmain);
                    TextView tv = findViewById(ids.get(i));
                    lm.removeView(tv);
                }

                for (int i = 0; i < click_ids.size(); i++) {
                    Button button = findViewById(click_ids.get(i));
                    button.setTextColor(Color.parseColor("#000000"));
                    button.setBackgroundColor(Color.parseColor("#E4A6EF"));
                    button.setEnabled(true);
                }
                click_ids.clear();
                blanks_filled = 0;
                guess = "";
                k=0;
                ids.clear();
                myFunction(chosen_word, chosen_clue);
            }
        }

        else {
            Toast.makeText(this, "Wrong!", Toast.LENGTH_SHORT).show();
            //Shuffle grid
            Collections.shuffle(letters);
            for (int i = 0; i < product; i++) {
                Button button2 = findViewById(button_ids.get(i));
                button2.setText(String.valueOf(letters.get(i % letters.size())));
            }

            guess = "";
            count=count-1;
            k = 0;
            score = score - 100;
            for (int i = 0; i < click_ids.size(); i++) {
                Button button = findViewById(click_ids.get(i));
                button.setTextColor(Color.parseColor("#000000"));
                button.setBackgroundColor(Color.parseColor("#E4A6EF"));
                button.setEnabled(true);
            }
            click_ids.clear();
            for (int b = 0; b < len; b++) {
                TextView ans = findViewById(ids.get(b));
                ans.setText("_");
            }
            blanks_filled = 0;

            if (count == 2) {
                ImageView heart = (ImageView) findViewById(R.id.heart3);
                int color = Color.parseColor("#046134");
                heart.setColorFilter(color);

            } else if (count == 1) {
                ImageView heart = (ImageView) findViewById(R.id.heart2);
                int color = Color.parseColor("#046134");
                heart.setColorFilter(color);
            } else if (count == 0) {
                ImageView heart = (ImageView) findViewById(R.id.heart1);
                int color = Color.parseColor("#046134");
                heart.setColorFilter(color);
                if (score > sharedPreferences.getInt(KEY_SCORE, 0)) {
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putInt(KEY_SCORE, score);
                    editor.apply();
                }
                showDialog();
            }
        }
    }


    private void openDialog() {
        Intent intent = getIntent();
        String clue = intent.getStringExtra(MainActivity_hacker.EXTRA_TEXT2);
        Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.clue_hacker);
        TextView hint = dialog.findViewById(R.id.clue_string);
        dialog.setCancelable(false);
        hint.setText(chosen_clue);
        dialog.show();
        Okbutton = (Button) dialog.findViewById(R.id.Okbutton);
        Okbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }

        });
    }



    private void showDialog() {
        Dialog dialog1 = new Dialog(this);
        dialog1.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog1.setContentView(R.layout.gameover_hacker);
        TextView result = dialog1.findViewById(R.id.finalscore);
        result.setText(String.valueOf(score));
        dialog1.setCancelable(false);
        dialog1.show();
        home = (Button) dialog1.findViewById(R.id.homebutton);
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaPlayer.start();
                openhome();
            }

        });
        playagain = (Button) dialog1.findViewById(R.id.playagain);
        playagain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaPlayer.start();
                openMainActivity();
            }

        });

    }

    public void openMainActivity() {

        Intent intent1 = new Intent(this, classic_home.class);
        startActivity(intent1);
    }
    public void openhome() {

        Intent intent2 = new Intent(this, home_hacker.class);
        startActivity(intent2);
    }


}

