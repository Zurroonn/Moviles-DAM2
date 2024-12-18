package com.example.proyectoconjunto;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;

public class MainActivity extends AppCompatActivity {
ImageView img1;
ImageView img2;
ImageView img3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        img1 = findViewById(R.id.imageView);
        Glide.with(this) // Contexto
                .load("https://2.bp.blogspot.com/-oHFSibUxw0E/VmBkYkDWMyI/AAAAAAAAFZs/UGwI6Uh-VeY/s320/android1.png") // URL de la imagen
                .placeholder(R.drawable.cargando) // Imagen de carga
                .error(R.drawable.error) // Imagen de error
                .into(img1); // Tu ImageView
        img2 = findViewById(R.id.imageView2);
        Picasso.get() // Contexto
                .load("htt://1.bp.blogspot.com/-iCRAMdzP0Ys/YXWu1pW41BI/AAAAAAAAPHs/mGiZABP24IUZlZ_i_Ln-ANcRxUlXdR-EgCLcBGAsYHQ/s320/gato.jpg") // URL de la imagen
                .placeholder(R.drawable.cargando) // Imagen de carga
                .error(R.drawable.error) // Imagen de error
                .into(img2); // Tu ImageView
        img3 = findViewById(R.id.imageView3);
        Picasso.get() // Contexto
                .load("htt://1.bp.blogspot.com/-iCRAMdzP0Ys/YXWu1pW41BI/AAAAAAAAPHs/mGiZABP24IUZlZ_i_Ln-ANcRxUlXdR-EgCLcBGAsYHQ/s320/gato.jpg") // URL de la imagen
                .placeholder(R.drawable.cargando) // Imagen de carga
                .error(R.drawable.error) // Imagen de error
                .into(img3); // Tu ImageView



            ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
                Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
                v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
                return insets;
            });

        }
    }

