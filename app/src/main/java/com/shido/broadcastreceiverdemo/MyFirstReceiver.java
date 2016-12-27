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

    public static final String TAG= MyFirstReceiver.class.getSimpleName();
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.i(TAG, "HELLO FROM FIRST RECEIBVER");
        Toast.makeText(context, "hello from first receiver", Toast.LENGTH_LONG).show();
    }




}

