package com.example.widgerts;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
EditText edt1;
EditText edt2;
RadioButton rad1;
RadioButton rad2;
RadioButton rad3;
RadioButton rad4;
TextView tex1;
RadioGroup radG;
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
         edt1=findViewById(R.id.editTextNumberSigned);
         edt2=findViewById(R.id.editTextNumberSigned2);
         rad1=(RadioButton) findViewById(R.id.radioButton);
         rad2=(RadioButton)findViewById(R.id.radioButton2);
         rad3=(RadioButton)findViewById(R.id.radioButton3);
         rad4=(RadioButton)findViewById(R.id.radioButton4);
         tex1=findViewById(R.id.textView);
         radG=findViewById(R.id.radioGroup);
         but1=findViewById(R.id.button);
         
         
         but1.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 calcular();
             }
         });

    }
    public void calcular(){
        int n1=Integer.parseInt(edt1.getText().toString());
        int n2=Integer.parseInt(edt2.getText().toString());
        int result=0;
        if (rad1.isChecked()==true) {
            result=n1+n2;
        } else
        if (rad2.isChecked()==true) {
            result=n1-n2;
        }else
        if (rad3.isChecked()==true){
            result=n1*n2;
        }else
            if (rad4.isChecked()==true) {
                result = n1 / n2;
            }
            tex1.setText(String.valueOf(result));
    }
}