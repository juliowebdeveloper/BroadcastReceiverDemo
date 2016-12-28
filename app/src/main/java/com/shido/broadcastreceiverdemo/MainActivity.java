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
       // Intent i = new Intent(this, MyFirstReceiver.class);
        //sendBroadcast(i);
        Intent intent = new Intent("my.custom.action.name"); //Passando a action como parametro da intent
        intent.putExtra("name", "Shido");
        intent.putExtra("age", 27);
        sendBroadcast(intent);
        //Toast para mostrar que os Broadcast Receiver são executados asynchronous

        Toast.makeText(MainActivity.this, "Toast From Main Activity", Toast.LENGTH_SHORT).show();
    }

    public void broadcastToInnerReceiver(View view) {
       // Intent i = new Intent(this, MyThirdReceiverInner.class);
        //sendBroadcast(i);
        Intent intent = new Intent("my.custom.anotheraction.name");
        Bundle bundle = new Bundle();
        bundle.putInt("age1", 33);
        bundle.putString("name1", "Shido");
        intent.putExtras(bundle);
        sendBroadcast(intent);


    }

    public static class MyThirdReceiverInner extends BroadcastReceiver {
            public static final String TAG = MyFirstReceiver.class.getSimpleName();
        @Override
        public void onReceive(Context context, Intent intent) {

            String name = intent.getStringExtra("name1");
            int age = intent.getIntExtra("age1", 0);

            Log.i(TAG, "NAME IS: " + name +" and age is " + String.valueOf(age));
                Log.i(TAG, "hello from third Receiver");
            Toast.makeText(context, "Hello from third receiver", Toast.LENGTH_LONG).show();

        }

    }



    }
