package com.example.wordjumblehackermode;


import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDialogFragment;
import androidx.fragment.app.DialogFragment;

public class timer extends Dialog implements View.OnClickListener {
    private EditText hours_text, minutes_text, seconds_text;
    private OnDataPassListener dataPassListener;
    Button set_timer;
    public interface OnDataPassListener {
        void onDataPass(int hours, int minutes, int seconds);

    }



    public timer(Context context, OnDataPassListener listener){
        super(context);
        this.dataPassListener = listener;
    }
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.timer);


        hours_text = findViewById(R.id.hours);
        minutes_text = findViewById(R.id.minutes);
        seconds_text = findViewById(R.id.seconds);
        set_timer = findViewById(R.id.set_timer);
        set_timer.setOnClickListener(this);
    }
            @Override
            public void onClick(View v) {
                String hourstv = hours_text.getText().toString();
                String minutestv = minutes_text.getText().toString();
                String secondstv = seconds_text.getText().toString();

                int hours = Integer.parseInt(hourstv);
                int minutes = Integer.parseInt(minutestv);
                int seconds = Integer.parseInt(secondstv);
                dataPassListener.onDataPass(hours,minutes,seconds);
                dismiss();
            }
        }


