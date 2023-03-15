package com.example.foodwar.user_management;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.foodwar.R;
import com.example.foodwar.admin_activity.FoodActivity;
import com.example.foodwar.admin_activity.StoreActivity;
import com.example.foodwar.admin_activity.UserActivity;
import com.example.foodwar.databinding.ActivityAdminBinding;

public class AdminActivity extends AppCompatActivity {

    Button button, button2, button3;
    private ActivityAdminBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityAdminBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // USER
        binding.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AdminActivity.this, UserActivity.class);
                startActivity(intent);
            }
        });

        // STORE
        binding.button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AdminActivity.this, StoreActivity.class);
                startActivity(intent);
            }
        });

        // FOOD
        binding.button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AdminActivity.this, FoodActivity.class);
                startActivity(intent);
            }
        });
        binding.button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                System.exit(0);
            }
        });

    }
}