package com.example.dialogos;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    Button buttonShowDialog;
    TextView responseText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        // Aseguramos la compatibilidad con la disposición de los insets
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Aquí añadimos la lógica para el botón y el diálogo
        Button buttonShowDialog = findViewById(R.id.button);
        @SuppressLint({"LocalSuppress", "MissingInflatedId"}) TextView responseText = findViewById(R.id.textView);

        buttonShowDialog.setOnClickListener(v -> {
            // Crear el diálogo
            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
            builder.setTitle("Pregunta muy importante:");
            builder.setMessage("¿Es un buen día?");

            // Opción Sí
            builder.setPositiveButton("¡Sí!", (dialog, which) -> {
                responseText.setText("Es un buen día!");
                responseText.setVisibility(TextView.VISIBLE);
                dialog.dismiss();  // Cerrar el diálogo
            });

            // Opción No
            builder.setNegativeButton("¡No!", (dialog, which) -> {
                responseText.setText("¡Oh no!");
                responseText.setVisibility(TextView.VISIBLE);
                dialog.dismiss();  // Cerrar el diálogo
            });

            // Mostrar el diálogo
            AlertDialog alertDialog = builder.create();
            alertDialog.show();
        });
    }
}
