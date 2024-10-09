package com.example.radiocheck;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    CheckBox cb1, cb2, cb3, cb4;
    TextView textView;
    RadioGroup rg1, rg2;

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

        // Inicializar CheckBoxes y RadioButtons
        cb1 = findViewById(R.id.checkBox); // Negrita
        cb2 = findViewById(R.id.checkBox2); // Cursiva
        cb3 = findViewById(R.id.checkBox3); // Serif
        cb4 = findViewById(R.id.checkBox4); // MonoSpace

        textView = findViewById(R.id.textView);

        rg1 = findViewById(R.id.radioGroup); // Color de fondo
        rg2 = findViewById(R.id.radioGroup2); // Color de fuente


        cb1.setOnCheckedChangeListener((buttonView, isChecked) -> setTypeface());
        cb2.setOnCheckedChangeListener((buttonView, isChecked) -> setTypeface());
        cb3.setOnCheckedChangeListener((buttonView, isChecked) -> setTypeface());
        cb4.setOnCheckedChangeListener((buttonView, isChecked) -> setTypeface());
        /*cb4.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                METODO DONDE TENDRIA QUE PONER EL TYPEFACE SI NO QUISIERA PONER LO DE ARRIBA
            }
        })*/

        rg1.setOnCheckedChangeListener((group, checkedId) -> setBackgroundColor(checkedId));
        rg2.setOnCheckedChangeListener((group, checkedId) -> setTextColor(checkedId));
    }


    private void setTypeface() {
        int style = Typeface.NORMAL;

        if (cb1.isChecked() && cb2.isChecked()) {
            style = Typeface.BOLD_ITALIC;
        } else if (cb1.isChecked()) {
            style = Typeface.BOLD;
        } else if (cb2.isChecked()) {
            style = Typeface.ITALIC;
        }

        if (cb3.isChecked()) {
            textView.setTypeface(Typeface.SERIF, style);
        } else if (cb4.isChecked()) {
            textView.setTypeface(Typeface.MONOSPACE, style);
        } else {
            textView.setTypeface(Typeface.SANS_SERIF, style);
        }
    }

    // Método para configurar el color de fondo utilizando if-else if
    private void setBackgroundColor(int checkedId) {
        if (checkedId == R.id.radioButton6) { // Rojo
            textView.setBackgroundColor(Color.RED);
        } else if (checkedId == R.id.radioButton7) { // Azul
            textView.setBackgroundColor(Color.BLUE);
        } else if (checkedId == R.id.radioButton8) { // Verde
            textView.setBackgroundColor(Color.GREEN);
        } else if (checkedId == R.id.radioButton10) { // Blanco
            textView.setBackgroundColor(Color.WHITE);
        } else if (checkedId == R.id.radioButton9) { // Negro
            textView.setBackgroundColor(Color.BLACK);
        }
    }

    // Método para configurar el color de fuente utilizando if-else if
    private void setTextColor(int checkedId) {
        if (checkedId == R.id.radioButton) { // Rojo
            textView.setTextColor(Color.RED);
        } else if (checkedId == R.id.radioButton2) { // Azul
            textView.setTextColor(Color.BLUE);
        } else if (checkedId == R.id.radioButton3) { // Verde
            textView.setTextColor(Color.GREEN);
        } else if (checkedId == R.id.radioButton5) { // Blanco
            textView.setTextColor(Color.WHITE);
        } else if (checkedId == R.id.radioButton4) { // Negro
            textView.setTextColor(Color.BLACK);
        }
    }
}
