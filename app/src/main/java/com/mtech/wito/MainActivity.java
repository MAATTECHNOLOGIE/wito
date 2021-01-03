package com.mtech.wito;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {

    public CardView contacts;
    public CardView afrique;
    public CardView products;
    public CardView services;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Chargement de l'activity contacts
        contacts = (CardView) findViewById(R.id.contact);
        contacts.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {
                Snackbar snackbarCt =  Snackbar.make(findViewById(R.id.mainapps),"Chargement..",Snackbar.LENGTH_SHORT);
                snackbarCt.show();
                Intent intent = new Intent(MainActivity.this, contacts.class);
                startActivity(intent);
            }
        });

        // Chargement de l'activity afrique
        afrique = (CardView) findViewById(R.id.afrique);
        afrique.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,afrique.class);
                startActivity(intent);
            }
        });

        // Chargement de l'activity produits
        products = (CardView) findViewById(R.id.prod);
        products.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,products.class);
                startActivity(intent);
            }
        });

        // Chargement de l'activity services
        services = (CardView) findViewById(R.id.service);
        services.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,services.class);
                startActivity(intent);
            }
        });

    }





}