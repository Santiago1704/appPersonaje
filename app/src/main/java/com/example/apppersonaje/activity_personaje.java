package com.example.apppersonaje;

import android.annotation.SuppressLint;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Locale;

public class activity_personaje extends AppCompatActivity {
    private ImageView imageView;
    private TextView textViewDescripcion;
    private Drawable[] imagenes;
    private String[] descripciones;
    private int currentImageIndex = 0;
    private boolean isEnglish = false;

    @SuppressLint("UseCompatLoadingForDrawables")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_personaje);
        ImageView imageView = findViewById(R.id.imageView);
        textViewDescripcion = findViewById(R.id.textView2);

        imagenes = new Drawable[5];
        imagenes[0] = getResources().getDrawable(R.drawable.juventus);
        imagenes[1] = getResources().getDrawable(R.drawable.manchester);
        imagenes[2] = getResources().getDrawable(R.drawable.real);
        imagenes[3] = getResources().getDrawable(R.drawable.imagen2);

        cargarDescripciones();

        imageView.setImageDrawable(imagenes[currentImageIndex]);
        textViewDescripcion.setText(descripciones[currentImageIndex]);

        Button buttonnext = findViewById(R.id.button3);
        buttonnext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (currentImageIndex < imagenes.length - 1) {
                    currentImageIndex++;
                    imageView.setImageDrawable(imagenes[currentImageIndex]);
                    textViewDescripcion.setText(descripciones[currentImageIndex]);
                }
            }
        });

        Button btnAnterior = findViewById(R.id.button2);
        btnAnterior.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (currentImageIndex > 0) {
                    currentImageIndex--;
                    imageView.setImageDrawable(imagenes[currentImageIndex]);
                    textViewDescripcion.setText(descripciones[currentImageIndex]);
                }
            }
        });

        Button buttonChangeLanguage = findViewById(R.id.button_change_language);
        buttonChangeLanguage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isEnglish) {
                    setLocale("es");
                    isEnglish = false;
                } else {
                    setLocale("en");
                    isEnglish = true;
                }
            }
        });


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    private void cargarDescripciones() {
        descripciones = new String[]{
                getString(R.string.description_juventus),
                getString(R.string.description_manchester),
                getString(R.string.description_real),
                getString(R.string.description_other)
        };
    }

    private void setLocale(String lang) {
        Locale myLocale = new Locale(lang);
        Resources res = getResources();
        DisplayMetrics dm = res.getDisplayMetrics();
        Configuration conf = res.getConfiguration();
        conf.locale = myLocale;
        res.updateConfiguration(conf, dm);

        recreate();
    }
}