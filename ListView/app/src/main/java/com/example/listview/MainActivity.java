package com.example.listview;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity implements ListView.OnItemClickListener {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        String [] elementos={"León","Zamora","Salamanca","Palencia","Valladolid","Ávila","Burgos","Segovia","Soria"};
        ArrayAdapter<String> adaptador;


        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ListView l=(ListView)findViewById(R.id.listView);
        adaptador=new ArrayAdapter<String>(this, android.R.layout.simple_list_item_multiple_choice,elementos);

        l.setAdapter(adaptador);
        l.setOnItemClickListener(this);



        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);



            return insets;
        });



    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        TextView t=(TextView)findViewById(R.id.textView2);
        ListView li=(ListView)findViewById(R.id.listView);
        StringBuffer seleccionado= new StringBuffer("Has elegido: ");
        SparseBooleanArray checked = li.getCheckedItemPositions();
        for( i=0;i<checked.size();i++)

            if(checked.valueAt(i)){
                seleccionado.append(adapterView.getItemAtPosition(checked.keyAt(i)).toString());
                seleccionado.append(";");
            }
        t.setText(seleccionado.toString());
    }
}