package com.example.menu;

import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.menu.databinding.ActivityMainBinding;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration appBarConfiguration;
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.toolbar);

        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        appBarConfiguration = new AppBarConfiguration.Builder(navController.getGraph()).build();
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);

        binding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAnchorView(R.id.fab)
                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int iden= item.getItemId();

        if (iden==R.id.Facturas) {

            Toast.makeText(getApplicationContext(), "Pulsado Facturas",
                    Toast.LENGTH_LONG).show();
            return true;
        }
        if (iden==R.id.Clientes) {

            Toast.makeText(getApplicationContext(), "Pulsado Clientes",
                    Toast.LENGTH_LONG).show();
            return true;
        }

        if (iden==R.id.check1) {
            item.setChecked(!item.isChecked());
                Toast.makeText(getApplicationContext(), "Pulsado confCliente",
                        Toast.LENGTH_LONG).show();
                return true;
            }
        if (iden==R.id.check2) {
            item.setChecked(!item.isChecked());
            Toast.makeText(getApplicationContext(), "Pulsado confFacturas",
                    Toast.LENGTH_LONG).show();
            return true;
        }
        if (iden==R.id.radio1) {
            item.setChecked(true);
            Toast.makeText(getApplicationContext(), "Pulsado confPedidos",
                    Toast.LENGTH_LONG).show();
            return true;
        }
        if (iden==R.id.radio2) {
            item.setChecked(true);
            Toast.makeText(getApplicationContext(), "Pulsado confPedidos",
                    Toast.LENGTH_LONG).show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, appBarConfiguration)
                || super.onSupportNavigateUp();
    }
}