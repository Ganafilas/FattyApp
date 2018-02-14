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

    /*
    private TextView tvKG;
    private TextView tvAltura;
    private TextView tvNombre;
    */

    private String kg;
    private String altura;
    private String nombre;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /*
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        */

        SharedPreferences settings=getSharedPreferences("prefs", Context.MODE_PRIVATE);
        boolean firstRun=settings.getBoolean("firstRun",true);

        kg = settings.getString("kg","no hay nada");
        altura = settings.getString("altura","no hay nada");
        nombre = settings.getString("nombre","no hay nada");

        /*
        tvKG = (TextView)findViewById(R.id.tvKG);
        tvAltura = (TextView)findViewById(R.id.tvAltura);
        tvNombre = (TextView)findViewById(R.id.tvNombre);

        tvKG.setText(kg);
        tvAltura.setText(altura);
        tvNombre.setText(nombre);
        */

        SharedPreferences.Editor editor;

        if(firstRun==true){//si se ejecuta por primera vez
            editor=settings.edit();
            editor.putBoolean("firstRun",false);
            editor.commit();

            Intent i=new Intent(this,explicacion_app1.class);
            startActivity(i);
            finish();

        } else {

            Intent i = getIntent();

            if(i.getBooleanExtra("vengoDelRegistro",false)){
                editor=settings.edit();
                editor.putString("kg", i.getStringExtra("kg"));
                editor.putString("altura", i.getStringExtra("altura"));
                editor.putString("nombre", i.getStringExtra("nombre"));
                editor.commit();

                kg = settings.getString("kg","no hay nada");
                altura = settings.getString("altura","no hay nada");
                nombre = settings.getString("nombre","no hay nada");

                avanzarAMainActivity();

                /*
                tvKG.setText(i.getStringExtra("kg"));
                tvAltura.setText( i.getStringExtra("altura"));
                tvNombre.setText( i.getStringExtra("nombre"));
                */
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
        startActivity(i);
        finish();
    }
}




