package com.example.preferencias;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private EditText etName, etSurname, etAge;
    private TextView tvPreferences;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Inicializar vistas
        etName = findViewById(R.id.editTextText);
        etSurname = findViewById(R.id.editTextText2);
        etAge = findViewById(R.id.editTextText3);
        tvPreferences = findViewById(R.id.textView);
        Button btnSavePreferences = findViewById(R.id.button2);
        Button btnLoadPreferences = findViewById(R.id.button);

        // Inicializar SharedPreferences
        sharedPreferences = getSharedPreferences("prefs_cli", MODE_PRIVATE);

        // Configurar el botón para guardar preferencias manualmente
        btnSavePreferences.setOnClickListener(v -> savePreferences());

        // Configurar el botón para cargar preferencias
        btnLoadPreferences.setOnClickListener(v -> loadPreferences());
    }

    @Override
    protected void onStart() {
        super.onStart();
        // Cargar preferencias automáticamente al iniciar
        loadPreferences();
    }

    @Override
    protected void onStop() {
        super.onStop();
        // Guardar datos en SharedPreferences automáticamente al detener la actividad
        savePreferences();
    }

    private void savePreferences() {
        // Leer los datos de las cajas de texto
        String name = etName.getText().toString();
        String surname = etSurname.getText().toString();
        String age = etAge.getText().toString();

        // Guardar en SharedPreferences
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("name", name);
        editor.putString("surname", surname);
        editor.putString("age", age);
        editor.apply(); // Guardar de forma asíncrona
    }

    private void loadPreferences() {
        // Leer los datos de SharedPreferences
        String name = sharedPreferences.getString("name", "No disponible");
        String surname = sharedPreferences.getString("surname", "No disponible");
        String age = sharedPreferences.getString("age", "No disponible");

        // Mostrar los datos en el TextView
        tvPreferences.setText("Nombre: " + name + "\nApellidos: " + surname + "\nEdad: " + age);
    }
}
