package com.example.foodwar.food_management;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.foodwar.R;

import java.util.ArrayList;


public class Restaurant extends AppCompatActivity {
    ListView listView_food;
    ArrayList<Food> foods;
    FoodAdapter adapter;
    ImageView imageView_feedBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurent);
        ActionToolBar();
        Init();
        LoadData();
        imageView_feedBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Restaurant.this,FeedBack.class);
                startActivity(intent);
            }
        });
    }


    private void ActionToolBar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarRestaurant);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void LoadData() {
        adapter = new FoodAdapter(this,R.layout.activity_row_food,foods);
        listView_food.setAdapter(adapter);
    }

    private void Init() {
        imageView_feedBack = findViewById(R.id.imageView_feedBack);
        listView_food = findViewById(R.id.listView_food);
        foods =new ArrayList<>();
        foods.add(new Food("Banh ngot","23000đ",R.drawable.img_food));
        foods.add(new Food("Banh ngot","23000đ",R.drawable.img_food));
        foods.add(new Food("Banh ngot","23000đ",R.drawable.img_food));
    }
}