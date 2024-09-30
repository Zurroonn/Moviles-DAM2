package com.example.circulo;

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

public class MainActivity extends AppCompatActivity {
    Button boton1;
    TextView radio1;
    EditText radio2;
    TextView longitud1;
    TextView longitud2;
    TextView area1;
    TextView area2;
    TextView vol1;
    TextView vol2;




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
    boton1=findViewById(R.id.button);
    radio1=findViewById(R.id.textView);
    radio2=findViewById(R.id.editTextNumberDecimal5);
    longitud1=findViewById(R.id.textView2);
         longitud2=findViewById(R.id.textView6);
         area1=findViewById(R.id.textView3);
         area2=findViewById(R.id.textView7);
         vol1=findViewById(R.id.textView4);
         vol2=findViewById(R.id.textView8);


         boton1.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
        calcular();
             }
         });
    }

    public void calcular (){
    int n=Integer.parseInt(radio2.getText().toString());

    longitud2.setText(String.valueOf(n*2*Math.PI));
    area2.setText(String.valueOf(Math.PI*Math.pow(n,2)));
    vol2.setText(String.valueOf(4/3*Math.PI*Math.pow(n,3)));
    }
}