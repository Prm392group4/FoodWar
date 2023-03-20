package com.example.foodwar.food_management;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.foodwar.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class RestaurantActivity extends AppCompatActivity {
    ImageView img_restaurant;
    TextView textView_address, textView_phone, textView_restaurant;
    RecyclerView recyclerView;
    ArrayList<Food> listFood;
    ArrayList<Restaurant> listRestaurant;
    FoodAdapterDetail adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurent);
        ActionToolBar();
        Init();
        GridLayoutManager gridLayoutManager = new GridLayoutManager(RestaurantActivity.this, 1);
        recyclerView.setLayoutManager(gridLayoutManager);
        listFood = new ArrayList<>();
        adapter = new FoodAdapterDetail(RestaurantActivity.this, listFood);
        recyclerView.setAdapter(adapter);
        getResFromRealTimeDatabase();
        getListFoodFromRealTimeDatabase();
        // ấn số điện thoại sẽ chuyển qua gọi
        textView_phone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String phoneNumber = textView_phone.getText().toString();
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", phoneNumber, null));
                startActivity(intent);
            }
        });
        // ấn vào địa chỉ sẽ sang map
        textView_address.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String address = ((TextView) view).getText().toString();
                Uri uri = Uri.parse("geo:0,0?q=" + address);
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });
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
    //get restaurant information
    private void getResFromRealTimeDatabase() {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference foods_ref = database.getReference("restaurants/3");
        foods_ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Restaurant restaurant = snapshot.getValue(Restaurant.class);
                textView_address.setText(restaurant.getAddress());
                textView_restaurant.setText(restaurant.getName());
                textView_phone.setText(restaurant.getPhone());
//                Glide.with(RestaurantActivity.this).load(restaurant.getImage()).into(img_restaurant);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(RestaurantActivity.this, "load list food failed", Toast.LENGTH_SHORT).show();
            }
        });
    }
    //get foods data from firebase
    private void getListFoodFromRealTimeDatabase(){
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference foods_ref  = database.getReference("foods");
        foods_ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(listFood!=null){
                    listFood.clear();
                }
                for (DataSnapshot dataSnapshot: snapshot.getChildren()) {
                    Food food = dataSnapshot.getValue(Food.class);
                    listFood.add(food);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(RestaurantActivity.this, "load list food failed", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void Init() {
        recyclerView = findViewById(R.id.recyclerView);
        img_restaurant = findViewById(R.id.imageview_image);
        textView_address = findViewById(R.id.textview_address);
        textView_restaurant = findViewById(R.id.textView_restaurant);
        textView_phone = findViewById(R.id.textview_phone);
    }
}

