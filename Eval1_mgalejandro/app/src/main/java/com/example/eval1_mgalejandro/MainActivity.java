package com.example.eval1_mgalejandro;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.gridlayout.widget.GridLayout;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity implements Spinner.OnItemSelectedListener, View.OnClickListener{

    String [] texto = {"2X2","3X3","4X4"};
    int imagenes [] = { R.drawable.sp2x2, R.drawable.sp3x3, R.drawable.sp4x4};
    String [] letras2X2 = {"A","B","C","D"};
    String [] letras3X3 = {"A","B","C","D","E","F","G","H","I"};
    String [] letras4X4 = {"A","B","C","D","E","F","G","H","I","J","K","L","M","N","Ñ","O","P","Q"};

    public class AdaptadorPersonalizado extends ArrayAdapter<String> {

        public AdaptadorPersonalizado(Context ctx, int txtViewResourceId, String[] objects){
            super(ctx, txtViewResourceId, objects);
        }
        @Override
        public View getDropDownView(int position, View cnvtView, ViewGroup prnt){
            return crearFilaPersonalizada(position, cnvtView, prnt);
        }
        @Override
        public View getView(int pos, View cnvtView, ViewGroup prnt){
            return crearFilaPersonalizada(pos, cnvtView, prnt);
        }

        public View crearFilaPersonalizada(int position, View convertView, ViewGroup parent){
            LayoutInflater inflater = getLayoutInflater();
            View miFila = inflater.inflate(R.layout.spinnerpersonal, parent,
                    false);
            TextView textoV = (TextView) miFila.findViewById(R.id.tvSpinner);
            textoV.setText(texto[position]);
            ImageView imagen = (ImageView)
                    miFila.findViewById(R.id.imagenSpinner);
            imagen.setImageResource(imagenes[position]);
            return miFila;
        }
    }

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
        Spinner selectorTablero = (Spinner) findViewById(R.id.sp1);
        AdaptadorPersonalizado a=new AdaptadorPersonalizado(this, R.layout.spinnerpersonal, texto);
        selectorTablero.setAdapter(a);
        selectorTablero.setOnItemSelectedListener(this);
    }
    public void onItemSelected(AdapterView<?> a, View view, int position, long id){
        GridLayout g= (GridLayout) findViewById(R.id.gl1);
        Spinner sp = (Spinner) findViewById(R.id.sp1);
        Random rd = new Random();
        ArrayList letrasSeleccionadas = new ArrayList<>();

        if(position==0) {
            g.removeAllViews();
            g.setColumnCount(2);
            Button b;
            int iden;
            for (int i = 0; i < 4; i++) {
                b = new Button(this);
                b.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                iden = View.generateViewId();
                b.setId(iden);
                while (letrasSeleccionadas.size() < letras2X2.length) {
                    String letra = letras2X2[rd.nextInt(letras2X2.length)];
                    if (!letrasSeleccionadas.contains(letra)) {
                        b.setText(letra);
                        letrasSeleccionadas.add(letra);
                        break;
                    }
                }
                b.setOnClickListener(this);
                g.addView(b, i);
            }
        } else if (position==1) {
            g.removeAllViews();
            g.setColumnCount(3);
            Button b;
            int iden;
            for (int i = 0; i < 9; i++) {
                b = new Button(this);
                b.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                iden = View.generateViewId();
                b.setId(iden);
                while (letrasSeleccionadas.size() < letras3X3.length) {
                    String letra = letras3X3[rd.nextInt(letras3X3.length)];
                    if (!letrasSeleccionadas.contains(letra)) {
                        b.setText(letra);
                        letrasSeleccionadas.add(letra);
                        break;
                    }
                }
                b.setOnClickListener(this);
                g.addView(b, i);
            }
        }else{
            g.removeAllViews();
            g.setColumnCount(4);
            Button b;
            int iden;
            for (int i = 0; i < 16; i++) {
                b = new Button(this);
                b.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                iden = View.generateViewId();
                b.setId(iden);
                while (letrasSeleccionadas.size() < letras4X4.length) {
                    String letra = letras4X4[rd.nextInt(letras4X4.length)];
                    if (!letrasSeleccionadas.contains(letra)) {
                        b.setText(letra);
                        letrasSeleccionadas.add(letra);
                        break;
                    }
                }
                b.setOnClickListener(this);
                g.addView(b, i);
            }
        }
    }
    public void onNothingSelected(AdapterView<?> a){
    }
    public void onClick (View v) {
        if (v.getClass().getSimpleName().equals("Button")) {
            Button b = (Button) v;
        }
    }
}
