package com.example.exev2_rollan_moralejo;

import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class enviarSms extends AppCompatActivity
{
    TextView tvNombreSMS,tvApellidosSMS,tvTlf;
    EditText etmensaje;
    Button butEnviar;
    public static final int ENVIASMS = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_enviar_sms);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) ->
        {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        tvNombreSMS = findViewById(R.id.tvNombreSMS);
        tvApellidosSMS = findViewById(R.id.tvApellidosSMS);
        tvTlf = findViewById(R.id.tvTlf);
        etmensaje = findViewById(R.id.etmensaje);
        butEnviar = findViewById(R.id.butEnviar);

        butEnviar.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                String tlf = tvTlf.getText().toString();
                String mensaje = etmensaje.getText().toString();
                EnviaSMS(tlf,mensaje);
                finish();
            }
        });
    }
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        tvNombreSMS = findViewById(R.id.tvNombreSMS);
        tvApellidosSMS = findViewById(R.id.tvApellidosSMS);
        tvTlf = findViewById(R.id.tvTlf);
        if (requestCode == ENVIASMS)
        {
            if (resultCode == RESULT_OK)
            {
                String nombreC,apellidosC,tlfC;
                nombreC = data.getStringExtra("NOMBRE");
                apellidosC = data.getStringExtra("APELLIDOS");
                tlfC = data.getStringExtra("TLF");
                tvNombreSMS.setText(nombreC);
                tvApellidosSMS.setText(apellidosC);
                tvTlf.setText(tlfC);
            }
        }
    }
    public void EnviaSMS(String telefono,String mensaje)
    {
        try
        {
            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage(telefono, null, mensaje, null, null);
            Toast.makeText(getApplicationContext(), "SMS enviado.", Toast.LENGTH_LONG).show();
        }
        catch (Exception e)
        {
            Toast.makeText(getApplicationContext(), "SMS no enviado, por favor, inténtalo otra vez.", Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }
    }
}