package com.example.alarmas;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

public class Alarma extends android.content.BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        // Mensaje emergente al activarse la alarma
        Toast.makeText(context, "DESPIERTAAAAAAAAAAAA QUE YA ES HORAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA", Toast.LENGTH_LONG).show();
        Log.i("La alarma ha sonado","Jeje");
    }
}