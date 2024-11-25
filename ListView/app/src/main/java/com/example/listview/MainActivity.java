package com.example.listview;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.SparseBooleanArray;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

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

        ArrayAdapter<String> adaptador1=new ArrayAdapter<String>(this, android.R.layout.simple_list_item_multiple_choice, getResources().getStringArray(R.array.lannisters));
        l.setAdapter(adaptador1);
        l.setOnItemClickListener(this);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);



            return insets;
        });



    }

    public void onItemClick1(AdapterView<?> p, View v, int position, long id){
        Object mActionMode = MainActivity.this.startActionMode(mActionModeCallback);
        v.setSelected(true);
    }
    private ActionMode.Callback mActionModeCallback = new ActionMode.Callback() {
        // Called when the action mode is created; startActionMode() was called
        @Override
        public boolean onCreateActionMode(ActionMode mode, Menu menu) {

            MenuInflater inflater = mode.getMenuInflater();
            inflater.inflate(R.menu.menu, menu);
            return true;
        }

        @Override
        public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
            return false; // Return false if nothing is done
        }
        @Override
        public boolean onActionItemClicked(ActionMode mode, MenuItem item) {

            if(item.getItemId()==R.id.aniquilar) {
                Toast.makeText(getApplicationContext(), "Hemos aniquilado a algún Lannister", Toast.LENGTH_LONG).show();
                return true;
            }
            if(item.getItemId()==R.id.salvar) {
                Toast.makeText(getApplicationContext(), "Hemos salvado a algún Lannister", Toast.LENGTH_LONG).show();
                return true;
            }
            if(item.getItemId()==R.id.encerrar) {
                Toast.makeText(getApplicationContext(), "Hemos encerrar a algún Lannister", Toast.LENGTH_LONG).show();
                return true;
            }
               else {
                return false;
            }

        }
        // Called when the user exits the action mode
        @Override
        public void onDestroyActionMode(ActionMode mode) {
            Object mActionMode = null;
        }
    };
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
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    };
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

            if(id==R.id.aniquilar){
                Toast.makeText(getApplicationContext(),"Se ha seleccionado aniquilar",Toast.LENGTH_LONG).show();
                return true;
            }
        if(id==R.id.encerrar){
            Toast.makeText(getApplicationContext(),"Se ha seleccionado encerrar",Toast.LENGTH_LONG).show();
            return true;
        }
        if(id==R.id.salvar){
            Toast.makeText(getApplicationContext(),"Se ha seleccionado salvar",Toast.LENGTH_LONG).show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    };
}