package com.example.tercero;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Scanner;

public class MainActivity extends AppCompatActivity {
    EditText nume;
    TextView res1;
    Button but1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;


        });
         nume=  findViewById(R.id.numb);
         res1=findViewById(R.id.respuest);
         but1=findViewById(R.id.but);
    but1.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
        factorizar();
        }
    });

    }

    public void factorizar(){
        int nume1= Integer.parseInt(nume.getText().toString());

        int finala=1;
    if (nume1>0){
        for (int i = 1; i <=nume1 ; i++) {
             finala=finala *i;

        }
        if (nume1 <0){


        }

    }
    res1.setText(String.valueOf(finala));

    }

}