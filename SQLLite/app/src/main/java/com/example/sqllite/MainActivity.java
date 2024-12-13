package com.example.sqllite;

import static java.sql.Types.VARCHAR;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    EditText txtGrupo,txtDisco;
    ListView listaDiscos;
    SQLiteDatabase db;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        txtGrupo=(EditText)findViewById(R.id.editTextText);
        txtDisco=(EditText)findViewById(R.id.editTextText2);
        listaDiscos=(ListView)findViewById(R.id.lista);
        db=openOrCreateDatabase("MisDiscos", Context.MODE_PRIVATE, null);
        db.execSQL("CREATE TABLE IF NOT EXISTS misDiscos(Grupo VARCHAR,Disco VARCHAR);");
        Listar();

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return true;
    }

    public void Añadir(View v){
        db.execSQL("INSERT INTO MisDiscos VALUES ('"+txtGrupo.getText().toString()+"','"+ txtDisco.getText().toString()+"')");
        Toast.makeText(this,"Se añadió el disco "+txtDisco.getText().toString(),Toast.LENGTH_LONG).show();
                Listar();
    }
    public void Borrar(View v){
        try {
            db.execSQL("DELETE FROM MisDiscos WHERE Grupo = '" +
                    txtGrupo.getText().toString() + "' AND Disco='" +
                    txtDisco.getText().toString() + "'");
            Toast.makeText(this, "Se borró el disco " +
                    txtDisco.getText().toString(), Toast.LENGTH_LONG).show();
        }
        catch(SQLException s){
            Toast.makeText(this, "Error al borrar!",
                    Toast.LENGTH_LONG).show();
        }
        Listar();
    }
    public void Listar(){
        ArrayAdapter<String> adaptador;
        List<String> lista = new ArrayList<String>();
        Cursor c=db.rawQuery("SELECT * FROM MisDiscos", null);
        if(c.getCount()==0)
            lista.add("No hay registros");
        else{
            while(c.moveToNext())
                lista.add(c.getString(0)+"-"+c.getString(1));
        }


    }
}