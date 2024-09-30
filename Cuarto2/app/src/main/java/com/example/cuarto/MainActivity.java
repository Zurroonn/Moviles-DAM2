package com.example.cuarto;

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
        TextView titulo1;
        TextView descrip1;
        EditText poner1;
        Button butt1;
        TextView finale1;


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
        titulo1=findViewById(R.id.textView);
        descrip1=findViewById(R.id.textView2);
        poner1=findViewById(R.id.editTextNumber);
        butt1=findViewById(R.id.button);
        finale1=findViewById(R.id.respueee);
        butt1.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        calcular();
    }
});

        }

public void calcular(){
    int n = Integer.parseInt(poner1.getText().toString());
    int count = 0;
    int num = 2;
    int primoNesimo = 0;

    if (n==0){
        finale1.setText("No se puede meter un 0");
        return;
    }

        while (count < n) {
            boolean esPrimo = true;

            for (int i = 2; i <= Math.sqrt(num); i++) {
                if (num % i == 0) {
                    esPrimo = false;
                    break;
                }
            }

            if (esPrimo) {
                count++;
                primoNesimo = num;
            }
            num++;
        }




    finale1.setText("El " + n + "º número primo es: " + primoNesimo);
}

}

