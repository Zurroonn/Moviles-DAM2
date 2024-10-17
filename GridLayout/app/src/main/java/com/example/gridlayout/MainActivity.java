package com.example.gridlayout;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.gridlayout.widget.GridLayout;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
GridLayout gl;
    @SuppressLint("MissingInflatedId")
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
        gl=findViewById(R.id.grid);
        gl.setColumnCount(3);
        gl.setRowCount(6);
        int [] colores= new int[17];
        Random rd= new Random();

        for (int i = 0; i <17 ; i++) {
            Button bt= new Button(this);
            bt.setText("boton"+(i+1));
            gl.addView(bt);
            int colo= Color.rgb(rd.nextInt(256),rd.nextInt(256),rd.nextInt(256));
            bt.setBackgroundColor(colo);
            colores[i]=colo;
            bt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    bt.setBackgroundColor(Color.WHITE);
                }

            });
        }
        Button bt1= new Button (this);
        bt1.setText("Reset");
        bt1.setBackgroundColor(Color.RED);
        gl.addView(bt1);
        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for (int i = 0; i <17 ; i++) {
                    Button bt2=(Button)gl.getChildAt(i);
                    bt2.setBackgroundColor(colores[i]);
                }
            }
        });
    }
}