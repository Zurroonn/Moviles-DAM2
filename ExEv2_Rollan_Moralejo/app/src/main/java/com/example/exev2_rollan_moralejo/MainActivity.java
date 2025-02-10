package com.example.exev2_rollan_moralejo;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements ListView.OnItemClickListener
{
    Button but1;
    ListView listaContactos;
    SQLiteDatabase db;
    String[] permisos={Manifest.permission.READ_CONTACTS,Manifest.permission.SEND_SMS,};
    List<String> lista_nombresContactos,nombres,apellidos,direcciones,localidades,provincias, lista_telefonosSMS;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) ->
        {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        for (String perm : permisos)
        {
            if (ContextCompat.checkSelfPermission(this, perm) != PackageManager.PERMISSION_GRANTED)
            {
                ActivityCompat.requestPermissions(this, new String[]{perm}, 0);
            }
        }

        but1 = findViewById(R.id.but1);
        listaContactos = findViewById(R.id.lv1);

        db = openOrCreateDatabase("MisContactos", Context.MODE_PRIVATE, null);
        db.execSQL("CREATE TABLE IF NOT EXISTS misContactos(Nombre VARCHAR,Apellidos VARCHAR, Direccion VARCHAR, Localidad VARCHAR, Provincia VARCHAR);");
        Listar();

        but1.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent= new Intent(MainActivity.this, anadirContacto.class);
                launcherAdd.launch(intent);
            }
            ActivityResultLauncher<Intent> launcherAdd = registerForActivityResult(
                    new ActivityResultContracts.StartActivityForResult(),
                    new ActivityResultCallback<ActivityResult>()
                    {
                        @Override
                        public void onActivityResult(ActivityResult result)
                        {
                            if (result.getResultCode() == Activity.RESULT_OK)
                            {
                                Intent data = result.getData();
                                String nombre,apellidos,direcciones,localidades,provincias;
                                if(data != null)
                                {
                                    nombre = data.getStringExtra("NOMBRE");
                                    apellidos = data.getStringExtra("APELLIDOS");
                                    direcciones = data.getStringExtra("DIRECCION");
                                    localidades = data.getStringExtra("LOCALIDAD");
                                    provincias = data.getStringExtra("PROVINCIA");
                                    Anadir(nombre,apellidos,direcciones,localidades,provincias);
                                }
                                else
                                {
                                    Toast.makeText(MainActivity.this, "No se han introducido valores", Toast.LENGTH_SHORT).show();
                                }
                            }
                        }
                    });

        });
        listaContactos.setOnItemClickListener(this);
    }

    public void Listar()
    {
        nombres = new ArrayList<String>();
        apellidos = new ArrayList<String>();
        direcciones = new ArrayList<String>();
        localidades = new ArrayList<String>();
        provincias = new ArrayList<String>();
        boolean hay = true;
        Cursor c=db.rawQuery("SELECT * FROM misContactos", null);
        if(c.getCount()==0)
        {
        }
        else
        {
            while(c.moveToNext())
            {
                nombres.add(c.getString(0));
                apellidos.add(c.getString(1));
                direcciones.add(c.getString(2));
                localidades.add(c.getString(3));
                provincias.add(c.getString(4));
            }
        }
        AdaptadorPersonalizado a = new AdaptadorPersonalizado(this,R.layout.fila,nombres);
        listaContactos.setAdapter(a);
    }
    public void Anadir(String nombre,String apellidos,String direccion, String localidad, String provincia)
    {
        db.execSQL("INSERT INTO misContactos VALUES ('"+nombre+"','"+ apellidos+"','"+direccion+"','"+localidad+"','"+provincia+"')");
        Toast.makeText(this,"Se añadió el contacto "+nombre,Toast.LENGTH_LONG).show();
        Listar();
    }


    @SuppressLint("Range")
    public void Buscar(View v,String nombre)
    {
        lista_nombresContactos = new ArrayList<String>();
        lista_telefonosSMS = new ArrayList<String>();
        String proyeccion[]={ContactsContract.Contacts._ID, ContactsContract.Contacts.DISPLAY_NAME, ContactsContract.Contacts.HAS_PHONE_NUMBER, ContactsContract.Contacts.PHOTO_ID};
        String filtro=ContactsContract.Contacts.DISPLAY_NAME + " = ?";
        String args_filtro[]={nombre};
        ContentResolver cr = getContentResolver();
        Cursor cur = cr.query(ContactsContract.Contacts.CONTENT_URI, proyeccion, filtro, args_filtro, null);
        if (cur.getCount() > 0)
        {
            while (cur.moveToNext())
            {
                String id = cur.getString( cur.getColumnIndex(ContactsContract.Contacts._ID));
                String name = cur.getString( cur.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
                if (Integer.parseInt(cur.getString(cur.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER))) > 0)
                {
                    lista_nombresContactos.add(name);
                    Toast.makeText(this, "A", Toast.LENGTH_SHORT).show();
                    String proyeccion2[]={ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME,ContactsContract.CommonDataKinds.Phone.NUMBER};
                    String filtro2=ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME +" = ?";
                    String args_filtro2[]={nombre};
                    ContentResolver cr2 = getContentResolver();
                    Cursor cur2 = cr2.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, proyeccion2, filtro2, args_filtro2, null);
                    if(cur2.getCount() >0)
                    {
                        String tlf = (cur2.getString(cur2.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER)));
                        lista_telefonosSMS.add(tlf);
                    }
                    cur2.close();
                }
            }
        }
        cur.close();
    }

    @Override
    public void onItemClick(AdapterView<?> a, View view, int position, long id)
    {
        String nombre = a.getItemAtPosition(position).toString();
        Buscar(view,nombre);
        if(!lista_nombresContactos.contains(nombre))
        {
          Toast.makeText(this, "No existe el contacto o no dispone de teléfono", Toast.LENGTH_SHORT).show();
        }
        else
        {
            String apellidosSMS = apellidos.get(position);
            String tlf = lista_telefonosSMS.get(position);
            Intent intent= new Intent(MainActivity.this, enviarSms.class);
            intent.putExtra("NOMBRE",nombre);
            intent.putExtra("APELLIDOS",apellidosSMS);
            intent.putExtra("TLF",tlf);
            launcherSMS.launch(intent);
        }
    }
    ActivityResultLauncher<Intent> launcherSMS = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>()
            {
                @Override
                public void onActivityResult(ActivityResult result)
                {
                    if (result.getResultCode() == Activity.RESULT_OK)
                    {
                        Toast.makeText(MainActivity.this, "Mensaje enviado correctamente", Toast.LENGTH_SHORT).show();
                    }
                }
            });
    public class AdaptadorPersonalizado extends ArrayAdapter<String>
    {
        public AdaptadorPersonalizado(Context ctx, int txtViewResourceId, List<String> objects)
        {
            super(ctx, txtViewResourceId, objects);
        }

        @Override public View getDropDownView(int position, View cnvtView, ViewGroup prnt)
        {
            return crearFilaPersonalizada(position, cnvtView, prnt);
        }
        @Override public View getView(int pos, View cnvtView, ViewGroup prnt)
        {
            return crearFilaPersonalizada(pos, cnvtView, prnt);
        }

        public View crearFilaPersonalizada(int position, View convertView, ViewGroup parent)
        {
            LayoutInflater inflater = getLayoutInflater();
            View miFila = inflater.inflate(R.layout.fila, parent, false);
            TextView nombre = (TextView) miFila.findViewById(R.id.tvNombre);
            TextView apellido = (TextView) miFila.findViewById(R.id.tvApellidos);
            TextView localidad = (TextView) miFila.findViewById(R.id.tvLocalidad);
            TextView provincia = (TextView) miFila.findViewById(R.id.tvProvincia);
            if(!nombres.isEmpty())
            {
                nombre.setText(nombres.get(position));
                apellido.setText(apellidos.get(position));
                localidad.setText(localidades.get(position));
                provincia.setText("("+provincias.get(position)+")");
            }
            else
            {
                nombre.setText("No hay registros"+"\n"+ "Todavía");
                apellido.setText("");
                localidad.setText("");
                provincia.setText("");
            }
            return miFila;
        }
    }
}
