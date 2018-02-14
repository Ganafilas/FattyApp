package com.example.alumno.fattyapp;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private String kg;
    private String altura;
    private String nombre;

    private boolean soyMacho;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SharedPreferences settings=getSharedPreferences("prefs", Context.MODE_PRIVATE);
        boolean firstRun=settings.getBoolean("firstRun",true);

        kg = settings.getString("kg","no hay nada");
        altura = settings.getString("altura","no hay nada");
        nombre = settings.getString("nombre","no hay nada");
        soyMacho = settings.getBoolean("soyMacho", soyMacho);

        SharedPreferences.Editor editor;

        if(firstRun == true){//Si se ejecuta por primera vez
            editor=settings.edit();
            editor.putBoolean("firstRun",false);
            editor.commit();

            Intent i=new Intent(this,explicacion_app1.class);
            startActivity(i);
            finish();

        } else { //Sino, se guardarán los datos y se mostrarán en el menú principal
            Intent i = getIntent();

            if(i.getBooleanExtra("vengoDelRegistro",false)){
                editor=settings.edit();
                editor.putString("kg", i.getStringExtra("kg"));
                editor.putString("altura", i.getStringExtra("altura"));
                editor.putString("nombre", i.getStringExtra("nombre"));
                editor.putBoolean("soyMacho", i.getBooleanExtra("sexo", soyMacho));
                editor.commit();

                kg = settings.getString("kg","no hay nada");
                altura = settings.getString("altura","no hay nada");
                nombre = settings.getString("nombre","no hay nada");
                soyMacho = settings.getBoolean("soyMacho", soyMacho);

                avanzarAMainActivity();
            } else {
                avanzarAMainActivity();
            }
        }
    }

    public void avanzarAMainActivity () {
        Intent i = new Intent(this,Menu_Principal.class);
        i.putExtra("kg", kg);
        i.putExtra("altura", altura);
        i.putExtra("nombre", nombre);
        i.putExtra("sexo", soyMacho);
        startActivity(i);
        finish();
    }
}




