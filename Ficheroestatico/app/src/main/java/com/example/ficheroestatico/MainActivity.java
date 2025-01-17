package com.example.ficheroestatico;

import android.annotation.SuppressLint;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {
    InputStream in;
    BufferedReader bf;
    Button bt;
    Button bt1;
    TextView textView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        Resources r = getResources();
        bt = findViewById(R.id.button);
        bt1 = findViewById(R.id.button2);
        textView = findViewById(R.id.textView);

        // Botón para mostrar el contenido del archivo
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                reiniciarReader(); // Reinicia el lector para empezar desde el inicio del archivo
                mostrarTodo(view);
            }
        });

        // Botón para borrar el contenido del TextView
        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                borrarTexto(view);
            }
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    // Método para reiniciar el BufferedReader
    private void reiniciarReader() {
        try {
            if (bf != null) {
                bf.close(); // Cierra el lector actual si está abierto
            }
            in = getResources().openRawResource(R.raw.lineas);
            bf = new BufferedReader(new InputStreamReader(in)); // Reinicia el lector desde el inicio del archivo
        } catch (IOException e) {
            Log.e("IOException", "Error al reiniciar el lector", e);
        }
    }

    // Método para mostrar todo el texto en el TextView
    public void mostrarTodo(View view) {
        StringBuilder contenido = new StringBuilder();
        String linea;

        try {
            while ((linea = bf.readLine()) != null) {
                contenido.append(linea).append("\n");
            }
            textView.setText(contenido.toString()); // Muestra todo el contenido en el TextView
        } catch (IOException e) {
            Log.e("IOException", "Error de lectura");
            textView.setText("Error al leer el archivo.");
        }
    }

    // Método para borrar el texto del TextView
    public void borrarTexto(View view) {
        textView.setText(""); // Limpia el contenido del TextView
    }
}
