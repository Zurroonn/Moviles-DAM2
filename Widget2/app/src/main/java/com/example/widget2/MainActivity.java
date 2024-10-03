package com.example.widget2;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener {
    EditText edt1;
    EditText edt2;
    RadioButton rad1;
    RadioButton rad2;
    RadioButton rad3;
    RadioButton rad4;
    TextView tex1;
    RadioGroup radG;

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
            edt1 = findViewById(R.id.editTextNumberSigned);
            edt2 = findViewById(R.id.editTextNumberSigned2);
            rad1 = (RadioButton) findViewById(R.id.radioButton);
            rad2 = (RadioButton) findViewById(R.id.radioButton2);
            rad3 = (RadioButton) findViewById(R.id.radioButton3);
            rad4 = (RadioButton) findViewById(R.id.radioButton4);
            tex1 = findViewById(R.id.textView);
            radG = findViewById(R.id.radiogroup);

            radG.setOnCheckedChangeListener(this);

        }

    public void onCheckedChanged(RadioGroup group, int checkedId) {
        int n1 = Integer.parseInt(edt1.getText().toString());
        int n2 = Integer.parseInt(edt2.getText().toString());
        int result=0;
        RadioButton boton1 = findViewById(checkedId);

        switch (radG.indexOfChild(boton1)) {
            case 0:
                result = n1 + n2;
                break;
            case 1:
                result = n1 - n2;
                break;
            case 2:
                result = n1 / n2;
                break;
            case 3:
                result = n1 * n2;
                break;
        }
        tex1.setText(String.valueOf(result));
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {
        super.onPointerCaptureChanged(hasCapture);
    }
}