package com.example.llamadas;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Vista2 extends AppCompatActivity {
ListView lv1;
String [] elementos ={"Salamanca","Avila","Valladolid","Burgos","Leon"};
@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_vista2);
        ArrayAdapter<String> adaptador;
        ListView l=(ListView)findViewById(R.id.lista);
        adaptador=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,elementos);
        l.setAdapter(adaptador);
        l.setOnItemClickListener((adapterView, view, i, l1) -> {
            Intent intent=new Intent();
            intent.putExtra("PROVINCIA", elementos[i]);
            setResult(RESULT_OK, intent);
            finish();
        });


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;

        });

    }
}