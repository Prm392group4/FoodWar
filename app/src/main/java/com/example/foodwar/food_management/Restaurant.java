package com.example.foodwar.food_management;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.foodwar.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class Restaurant extends AppCompatActivity {
    RecyclerView recyclerView;
    ArrayList<Food> listFood;
    FoodAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurent);
        ActionToolBar();
        Init();
        GridLayoutManager gridLayoutManager = new GridLayoutManager(Restaurant.this, 1);
        recyclerView.setLayoutManager(gridLayoutManager);
        listFood = new ArrayList<>();
        adapter = new FoodAdapter(Restaurant.this, listFood);
        recyclerView.setAdapter(adapter);
        getListFoodFromRealTimeDatabase();
    }

    //Create back button
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

    //get data from firebase
    private void getListFoodFromRealTimeDatabase(){
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("foods");
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot: snapshot.getChildren()) {
                    Food food = dataSnapshot.getValue(Food.class);
                    listFood.add(food);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(Restaurant.this, "load list food failed", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void Init() {
        recyclerView = findViewById(R.id.recyclerView);
    }
}