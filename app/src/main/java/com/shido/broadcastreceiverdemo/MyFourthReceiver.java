package com.shido.broadcastreceiverdemo;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

public class MyFourthReceiver extends BroadcastReceiver {

    public MyFourthReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {


        if(isOrderedBroadcast()){
            int initCode = getResultCode();
            String data = getResultData();
            Bundle initBundle = getResultExtras(true);
            String name = initBundle.getString("name");


            Log.i("Fourth Receiver","Init code: " + initCode + " data " + data + " name " + name);
        }


    }
}
