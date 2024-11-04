package com.example.fecha;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class MainActivity extends AppCompatActivity implements DialogoFecha.OnFechaSeleccionada, DialogoHora.OnHoraSeleccionada{
EditText edt1;
    EditText edt2;
    EditText edt3;
Button but1;
Button but2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        Calendar c=Calendar.getInstance();


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        but1=findViewById(R.id.button);
        but2=findViewById(R.id.button2);
        but1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogoFecha d=new DialogoFecha();
                d.show(getSupportFragmentManager(),"Mi diálogo Fecha");}
            }
        );
        but2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogoHora d=new DialogoHora();
                d.show(getSupportFragmentManager(),"Mi diálogo Hora");}

            }
        );

    }

    public void onResultadoFecha(GregorianCalendar fecha) {
        edt2=(EditText)findViewById(R.id.editTextText2);
        edt2.setText(fecha.get(Calendar.DAY_OF_MONTH)+"/"+(fecha.get(Calendar.MONTH)+1)+"/"+fecha.get(Calendar.YEAR));
    }
    public void onResultadoHora(GregorianCalendar tiempo){
        edt3=(EditText) findViewById(R.id.editTextText3);
        edt3.setText(tiempo.get(Calendar.HOUR)+":"+(Calendar.MINUTE));

    }
}