package com.shido.broadcastreceiverdemo;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

public class MySecondReceiver extends BroadcastReceiver {
    public MySecondReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.i("SECONDRECEIVER", "HELLO FROM SECOND RECEIVER");
        Toast.makeText(context, "Hello from second Receiver", Toast.LENGTH_LONG).show();

        if(isOrderedBroadcast()){
            int initCode = getResultCode();
            String data = getResultData();
            Bundle bundle = getResultExtras(true);
            String name = bundle.getString("name");

            Log.i("SECOND RECEIVER", "Init code: " + initCode + " data " + data + " name " + name);

            setResultCode(47);
            setResultData("Mobile");
            bundle.putString("name", "Shidobecker");
            setResultExtras(bundle);

            //setResult(55, "testemobile", bundle);


        }


        //Checando Conectividade se é de wi fi ou de 3g - Possivel trata-las individualmente
        ConnectivityManager connMgr = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        if (activeNetwork != null) { // connected to the internet
            if (activeNetwork.getType() == ConnectivityManager.TYPE_WIFI) {
                // connected to wifi
                Log.i("Connected", "CONECTADO COM  WI FI");

               // Toast.makeText(context, activeNetwork.getTypeName(), Toast.LENGTH_SHORT).show();
            } else if (activeNetwork.getType() == ConnectivityManager.TYPE_MOBILE) {
                // connected to the mobile provider's data plan
                Log.i("Connected", "CONECTADO COM  3g");

               // Toast.makeText(context, activeNetwork.getTypeName(), Toast.LENGTH_SHORT).show();
            }
        } else {
            Log.i("Not Connected", "NÃO CONECTADO COM A INTERNET");
            // not connected to the internet
        }

    }
}
