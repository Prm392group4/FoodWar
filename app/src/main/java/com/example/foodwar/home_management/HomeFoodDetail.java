package com.example.foodwar.home_management;



import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.foodwar.R;
import com.example.foodwar.food_management.FeedBack;
import com.example.foodwar.food_management.FeedBackActivity;
import com.example.foodwar.food_management.FeedBackAdapter;
import com.example.foodwar.food_management.RestaurantActivity;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;



public class HomeFoodDetail extends AppCompatActivity {
    Toolbar toolbar;
    ImageView imageView_restaurant, imageView_feedBack, imageView;
    TextView textView_price, textView_nameProduct, textView_description;
    ListView lv_feedback;
    List<FeedBack> feedbackList = new ArrayList<>();
    FeedBackAdapter feedBackAdapter;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_food);
        ActionToolBar();
        init();

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            textView_price.setText(bundle.getString("Price"));
            textView_nameProduct.setText(bundle.getString("Name"));
            textView_description.setText(bundle.getString("Description"));
            Glide.with(this).load(bundle.getString("Image")).into(imageView);
        }

        imageView_restaurant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeFoodDetail.this, RestaurantActivity.class);
                startActivity(intent);
            }
        });

        imageView_feedBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeFoodDetail.this, FeedBackActivity.class);
                startActivity(intent);
            }
        });
        textView_description.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textView_description.setMaxLines(Integer.MAX_VALUE);
                textView_description.setEllipsize(null);
            }
        });
        // Kết nối tới Firebase Realtime Database
        databaseReference = FirebaseDatabase.getInstance().getReference().child("FeedBack");

        // Lấy dữ liệu từ Firebase Realtime Database
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                feedbackList.clear();
                for (DataSnapshot feedbackSnapshot : snapshot.getChildren()) {
                    FeedBack feedback = feedbackSnapshot.getValue(FeedBack.class);
                    feedbackList.add(feedback);
                }
                feedBackAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.d("MainActivity", error.getMessage());
            }
        });

        // Thiết lập adapter cho ListView
        feedBackAdapter = new FeedBackAdapter(this, feedbackList);
        lv_feedback.setAdapter(feedBackAdapter);
    }


    private void init() {
        imageView = findViewById(R.id.detail_imageView);
        textView_nameProduct = findViewById(R.id.textView_nameProduct);
        textView_price = findViewById(R.id.detail_textView_price);
        textView_description = findViewById(R.id.textView_description);
        imageView_restaurant = findViewById(R.id.imageview_restaurant);
        imageView_feedBack = findViewById(R.id.imageView_feedBack);
        lv_feedback = findViewById(R.id.lv_feedback);
    }

    //create back button
    private void ActionToolBar() {
        toolbar = findViewById(R.id.toolBar_detailFood);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

}
