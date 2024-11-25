package com.example.a11listviewcon7;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity  {
    TextView tx1;
    String[] elementos = {"León", "Zamora", "Salamanca", "Palencia"};

    String[] descripciones = {"La ciudad Imperial", "Qué gran ciudad",
            "Ciudad gastronómica", "Ciudad encantada"};

    int imagenes[] = {R.drawable.leon, R.drawable.zamora,
            R.drawable.salamanca, R.drawable.palencia};

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        @SuppressLint({"MissingInflatedId", "LocalSuppress"}) ListView l = (ListView) findViewById(R.id.lista);
        l.setOnCreateContextMenuListener(this);
        AdaptadorPersonalizado adap= new AdaptadorPersonalizado(this, android.R.layout.simple_list_item_single_choice,elementos);
        l.setAdapter(adap);
        l.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                tx1=findViewById(R.id.textView3);
                tx1.setText(elementos[i]);
            }
        });


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);


            return insets;
        });


    }

    public View crearFilaPersonalizada(int position, View convertView,
                                       ViewGroup parent) {
        LayoutInflater inflater = getLayoutInflater();
        View miFila = inflater.inflate(R.layout.fila, parent,
                false);
        TextView nombre = (TextView) miFila.findViewById(R.id.nombre);
        nombre.setText(elementos[position]);
        TextView descripcion = (TextView) miFila.findViewById(R.id.descripcion);
        descripcion.setText(descripciones[position]);
        ImageView imagen = (ImageView)
                miFila.findViewById(R.id.imageView);
        imagen.setImageResource(imagenes[position]);
        return miFila;
    }

    public class AdaptadorPersonalizado extends ArrayAdapter<String> {
        public AdaptadorPersonalizado(Context ctx, int txtViewResourceId, String[]
                objects) {
            super(ctx, txtViewResourceId, objects);
        }

        @Override
        public View getDropDownView(int position, View cnvtView, ViewGroup prnt) {
            return crearFilaPersonalizada(position, cnvtView, prnt);
        }

        @Override
        public View getView(int pos, View cnvtView, ViewGroup prnt) {
            return crearFilaPersonalizada(pos, cnvtView, prnt);
        }



    }
    @Override
    public boolean onContextItemSelected(MenuItem item) {
        ListView starks = (ListView) findViewById(R.id.lista);
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo)
                item.getMenuInfo();

        if (item.getItemId() == R.id.matar) {

            Toast.makeText(getApplicationContext(), "Hemos matado a " + starks.getItemAtPosition(info.position),
                    Toast.LENGTH_LONG).show();
            return true;
        }


        if (item.getItemId() == R.id.enviarmensjae) {

            Toast.makeText(getApplicationContext(), "Hemos matado a " + starks.getItemAtPosition(info.position),
                    Toast.LENGTH_LONG).show();
            return true;
        }
        if (item.getItemId() == R.id.sanar) {

            Toast.makeText(getApplicationContext(), "Hemos matado a " + starks.getItemAtPosition(info.position),
                    Toast.LENGTH_LONG).show();
            return true;
        }
        return true;
    }
@Override
public void onCreateContextMenu(ContextMenu menu,
                                View v, ContextMenu.ContextMenuInfo menuInfo) {
    ListView starks = (ListView) findViewById(R.id.lista);
    registerForContextMenu(starks);
    MenuInflater m=getMenuInflater();
    m.inflate(R.menu.menu,menu);
    super.onCreateContextMenu(menu, v, menuInfo);
}


}