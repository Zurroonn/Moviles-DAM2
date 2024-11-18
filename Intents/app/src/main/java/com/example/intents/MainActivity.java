package com.example.intents;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    Button bt1, bt2, bt3;

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
        bt1 = findViewById(R.id.button1);
        bt2 = findViewById(R.id.button2);
        bt3 = findViewById(R.id.button3);
        Intent i = new Intent(Intent.ACTION_SEND);

        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent();
                Intent chooser = null;
                EditText edURL = (EditText) findViewById(R.id.texto1);
                i.setAction(Intent.ACTION_VIEW);
                i.setData(Uri.parse(edURL.getText().toString()));
                chooser = i.createChooser(i, "Elige un navegador");
                startActivity(chooser);
                Toast.makeText(getApplicationContext(), "Access a web!", Toast.LENGTH_LONG).show();
            }
        });
        bt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent();
                Intent chooser = null;
                EditText edLongitud = (EditText) findViewById(R.id.texto2);
                EditText edLatitud = (EditText) findViewById(R.id.texto3);
                i.setAction(Intent.ACTION_VIEW);
                i.setData(Uri.parse("geo:" + edLatitud.getText().toString() + "," +
                        edLongitud.getText().toString()));
                chooser = i.createChooser(i, "Lanzar Maps");
                startActivity(chooser);
                Toast.makeText(getApplicationContext(), "Acceso a mapas!", Toast.LENGTH_LONG).show();
            }
        });
        bt3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent();
                Intent chooser = null;
                EditText
                        edEmail=(EditText)findViewById(R.id.texto4);
                i.setAction(Intent.ACTION_SEND);
                i.setData(Uri.parse("mailto:"));
                String
                        para[]={edEmail.getText().toString(),"kkk@lll.es","otro@gmail. com"};
                i.putExtra(Intent.EXTRA_EMAIL,para);
                i.putExtra(Intent.EXTRA_SUBJECT,"Saludos desde Android");
                i.putExtra(Intent.EXTRA_TEXT,"Hola!. Este es nuestro primer email!!");
                i.setType("message/rfc822");
                chooser=i.createChooser(i,"Enviar email");
                startActivity(chooser);
                Toast.makeText(MainActivity.this,"Envía el email!!",Toast.LENGTH_LONG).show();
            }
        });
    }


}

