package com.example.botonimagen;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
ImageButton imgb;
ImageView imgv;
boolean alternar=false;
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
    imgb=findViewById(R.id.imageButton);
    imgv=findViewById(R.id.imageView);

    imgb.setOnClickListener(new View.OnClickListener() {
        @Override

        public void onClick(View view) {
        alternar=!alternar;
            if(alternar){
                imgb.setImageResource(R.drawable.bot2);
                imgv.setImageResource(R.drawable.fanta);
            }else{
                imgb.setImageResource(R.drawable.bot1);
                imgv.setImageResource(R.drawable.sin_t_tulo);
            }
        }
    });
    }
}