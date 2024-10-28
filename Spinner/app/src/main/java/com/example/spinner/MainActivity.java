package com.example.spinner;

import android.os.Bundle;
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        String [] elementos={"Elige","León","Zamora","Salamanca","Palencia","Valladolid","Ávila","Burgos","Segovia","Soria"};
        ArrayAdapter<String> adaptador;


        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        Spinner l=(Spinner)findViewById(R.id.spinner);
        adaptador=new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item,elementos);

        l.setAdapter(adaptador);
        l.setOnItemSelectedListener(this);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);



            return insets;
        });

    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        TextView t=(TextView)findViewById(R.id.textView);
        Spinner li=(Spinner)findViewById(R.id.spinner);

        t.setText(li.getItemAtPosition(i).toString());

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}