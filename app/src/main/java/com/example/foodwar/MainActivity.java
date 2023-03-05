package com.example.foodwar;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.foodwar.food_management.DetailFood;

public class MainActivity extends AppCompatActivity {
    Button button_food_detail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        button_food_detail = findViewById(R.id.button_food_detail);
        button_food_detail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, DetailFood.class);
                startActivity(intent);
            }
        });
    }
}