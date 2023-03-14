package com.example.foodwar.menu;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Switch;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.Toolbar;

import com.example.foodwar.R;
import com.google.android.material.switchmaterial.SwitchMaterial;

public class MainDarkMode extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lightmode_layout);
        SwitchMaterial switchMaterial;
        switchMaterial = findViewById(R.id.switcher);
        switchMaterial.setChecked(false);
//        Toolbar toolbar = findViewById(R.id.toolbardarkmode); //Ignore red line errors
//        setSupportActionBar(toolbar);
        switchMaterial.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isCheckKed) {
                if (isCheckKed){
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                    Intent intent = new Intent(MainDarkMode.this,MainMenu.class);
                }else {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);


                }
            }
        });

    }



    }
