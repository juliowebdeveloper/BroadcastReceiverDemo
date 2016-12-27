package com.shido.broadcastreceiverdemo;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    private static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void sendBroadCastMessage(View view) {
        Intent i = new Intent(this, MyFirstReceiver.class);
        sendBroadcast(i);

    }

    public void broadcastToInnerReceiver(View view) {
        Intent i = new Intent(this, MyThirdReceiverInner.class);
        sendBroadcast(i);
    }

    public static class MyThirdReceiverInner extends BroadcastReceiver {
            public static final String TAG = MyFirstReceiver.class.getSimpleName();
        @Override
        public void onReceive(Context context, Intent intent) {
                Log.i(TAG, "hello from third Receiver");
            Toast.makeText(context, "Hello from third receiver", Toast.LENGTH_LONG).show();
        }

    }



    }
