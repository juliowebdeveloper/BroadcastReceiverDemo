package com.shido.broadcastreceiverdemo;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    private static final String TAG = MainActivity.class.getSimpleName();


    private MyFirstReceiver myFirstReceiver;

    TextView txtTime;
    int counter = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myFirstReceiver = new MyFirstReceiver();
        txtTime = (TextView)findViewById(R.id.textView);
    }


    @Override
    protected void onResume() {
        super.onResume();

        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(Intent.ACTION_AIRPLANE_MODE_CHANGED);
        registerReceiver(myFirstReceiver, intentFilter);


    }


    private static List<BroadcastReceiver> receivers = new ArrayList<>();
    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(myFirstReceiver);
        boolean containsReceiver = receivers.contains(timeTickReceiver);
        if(containsReceiver){
            unregisterReceiver(timeTickReceiver);
        }

    }

    public void registerTime(View view) {
       registerReceiver(timeTickReceiver, new IntentFilter(Intent.ACTION_TIME_TICK));
        receivers.add(timeTickReceiver);
    }

    public void unregisterTime(View view) {
        boolean containsReceiver = receivers.contains(timeTickReceiver);
        if(containsReceiver){
            unregisterReceiver(timeTickReceiver);
        }
    }


    //Declarando BroadcasReceiver de forma dinamica como um atributo da MainActivity
    private BroadcastReceiver timeTickReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            int  minutes = counter;
            txtTime.setText(minutes + " minutes over");
            counter++;
            Toast.makeText(context, "Hello from time tick receiver", Toast.LENGTH_SHORT).show();

        }
    };

}
