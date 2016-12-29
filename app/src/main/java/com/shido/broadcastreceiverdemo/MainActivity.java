package com.shido.broadcastreceiverdemo;

import android.app.Activity;
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

       /*intent.putExtra("name", "Shido");
        intent.putExtra("age", 27);*/


        Bundle b = new Bundle();
        b.putInt("age", 27);
        b.putString("name", "Shido");


        /*void sendOrderedBroadcast (Intent intent,
                String receiverPermission,
                BroadcastReceiver resultReceiver,
                Handler scheduler,
        int initialCode,
        String initialData,
        Bundle initialExtras)

        Parameters
                intent
        Intent: The Intent to broadcast; all receivers matching this Intent will receive the broadcast.
                receiverPermission
        String: String naming a permissions that a receiver must hold in order to receive your broadcast. If null, no permission is required.
                resultReceiver
        BroadcastReceiver: Your own BroadcastReceiver to treat as the final receiver of the broadcast.
        scheduler
        Handler: A custom Handler with which to schedule the resultReceiver callback; if null it will be scheduled in the Context's main thread.
        initialCode
        int: An initial value for the result code. Often Activity.RESULT_OK.
                initialData
        String: An initial value for the result data. Often null.
                initialExtras
        Bundle: An initial value for the result extras. Often null.*/


        sendOrderedBroadcast(intent, null, new MyFourthReceiver(), null, Activity.RESULT_OK, "Android", b);



        // Enviando um Broadcast que verificar a prioridade dos intent filters quanto maior a prioridade primeiro ele vem
        //Mesma action:name

      /*
        Com isso a informação será passada para os BroadcastReceivers em ordem de prioridade
            cada um pode pegar essa informação e modifica-la e passa-la adianta
            caso deseje terminar a transmissao usa-se abortBroadcast();

            */

        //Toast para mostrar que os Broadcast Receiver são executados asynchronous

        Toast.makeText(MainActivity.this, "Toast From Main Activity", Toast.LENGTH_SHORT).show();
    }

    public void broadcastToInnerReceiver(View view) {
       // Intent i = new Intent(this, MyThirdReceiverInner.class);
        //sendBroadcast(i);
        Intent intent = new Intent("my.custom.anotheraction.name");
       /* Bundle bundle = new Bundle();
        bundle.putInt("age1", 33);
        bundle.putString("name1", "Shido");
        intent.putExtras(bundle);*/
        sendBroadcast(intent);


    }

    public static class MyThirdReceiverInner extends BroadcastReceiver {
            public static final String TAG = MyFirstReceiver.class.getSimpleName();
        @Override
        public void onReceive(Context context, Intent intent) {


            //Os getters and setters só valem para um OrderedBroadcast
            if(isOrderedBroadcast()) {
                //extraindo o initial code
                int initCode = getResultCode();
                String data = getResultData();
                Bundle initBundle = getResultExtras(true);
                String name = initBundle.getString("name");

                Log.i(TAG, "Init code: " + initCode + " data " + data + " name " + name);


           /* String name = intent.getStringExtra("name1");
            int age = intent.getIntExtra("age1", 0);
             Log.i(TAG, "NAME IS: " + name +" and age is " + String.valueOf(age)); */

                Log.i(TAG, "hello from third Receiver");
                Toast.makeText(context, "Hello from third receiver", Toast.LENGTH_LONG).show();

                //Modificando a informação para passa-la para o proximo receiver (o second)
                setResultCode(17);
                setResultData("Ios");
                initBundle.putString("name", "shidomaru");
                setResultExtras(initBundle);
            }

        }

    }



    }
