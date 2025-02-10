package com.example.exev2_rollan_moralejo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class anadirContacto extends AppCompatActivity
{
    Button butguardar;
    EditText et1,et2,et3,et4,et5;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_anadir_contacto);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) ->
        {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        butguardar = findViewById(R.id.butguardar);
        et1 = findViewById(R.id.et1);
        et2 =  findViewById(R.id.et2);
        et3 = findViewById(R.id.et3);
        et4 = findViewById(R.id.et4);
        et5 = findViewById(R.id.et5);
        butguardar.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent i=new Intent();
                if(!et1.getText().toString().isBlank())
                {
                    i.putExtra("NOMBRE", et1.getText().toString());
                    i.putExtra("APELLIDOS", et2.getText().toString());
                    i.putExtra("DIRECCION", et3.getText().toString());
                    i.putExtra("LOCALIDAD", et4.getText().toString());
                    i.putExtra("PROVINCIA", et5.getText().toString());
                    setResult(RESULT_OK, i);
                    finish();
                }
                Toast.makeText(anadirContacto.this, "Hasta que no se introduzca un nombre no se puede guardar el contacto", Toast.LENGTH_SHORT).show();
            }
        });
    }
}