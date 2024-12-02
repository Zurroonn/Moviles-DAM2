package com.example.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class Receptor extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        if
        (intent.getAction().equals("android.intent.action.AIRPLANE_MODE"))
                Toast.makeText(context, "Cambiado modo ",
                        Toast.LENGTH_LONG).show();
    }


}

