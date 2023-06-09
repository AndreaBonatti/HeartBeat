package com.andreabonatti92.heartbeat;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private ArrayList<Integer> values;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        values = new ArrayList<>();
        HeartBeatCanvas canvas = new HeartBeatCanvas(MainActivity.this, values);
        setContentView(canvas);

        final Handler handler = new Handler();
        final Runnable runnable = new Runnable() {
            @Override
            public void run() {
                Collections.reverse(values);
                // Aggiungo un valore a caso ogni volta
                // Avendo solo 50 punti rappresentati conservo solo gli ultimi 50
                final int random = new Random().nextInt(200) + -100;
                values.add(random);
                if(values.size() > 50) {
                    values.remove(0);
                }
                Collections.reverse(values);
                HeartBeatCanvas canvas = new HeartBeatCanvas(MainActivity.this, values);
                setContentView(canvas);
                handler.postDelayed(this, 1000);
            }
        };
        handler.post(runnable);
    }
}