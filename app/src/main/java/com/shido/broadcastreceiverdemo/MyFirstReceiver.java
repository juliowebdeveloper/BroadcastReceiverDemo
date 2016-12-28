package com.shido.broadcastreceiverdemo;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by Teste2 on 26/12/2016.
 */

public class MyFirstReceiver extends BroadcastReceiver {


      /*Broadcast Receiver trabalham na Main Thread e não podemos bloquea-la por muito tempo
    * Nao devemos fazer downloads ou algo que dure muito tempo
    * se não irá gerar um ANR - Application not Responding e a aplicação irá crashear (que nao dure mais de 10 segundos)
    * Utilizar services para essas coisas demorada*/


    public static final String TAG= MyFirstReceiver.class.getSimpleName();


    @Override
    public void onReceive(Context context, Intent intent) {
        Log.i(TAG, "HELLO FROM FIRST RECEIBVER, thread name "+ Thread.currentThread().getName());
        String name = intent.getStringExtra("name");
        int age = intent.getIntExtra("age",0);
        Log.i("NAME", name);
        Log.i("AGE", String.valueOf(age));
        Toast.makeText(context, "hello from first receiver", Toast.LENGTH_LONG).show();


    }




}

