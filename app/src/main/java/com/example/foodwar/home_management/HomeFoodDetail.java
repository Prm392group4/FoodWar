package com.example.foodwar.home_management;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.bumptech.glide.Glide;
import com.example.foodwar.R;
import com.example.foodwar.food_management.DetailFood;
import com.example.foodwar.food_management.FeedBack;
import com.example.foodwar.food_management.FeedBackActivity;
import com.example.foodwar.food_management.Restaurant;
import com.example.foodwar.food_management.RestaurantActivity;

public class HomeFoodDetail extends AppCompatActivity {
    Toolbar toolbar;
    ImageView imageView_restaurant, imageView_feedBack, imageView;
    TextView textView_price, textView_nameProduct, textView_description,
    textView_category,textView_restaurant ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_food_details);
        ActionToolBar();
        imageView = findViewById(R.id.home_food_detail_imgv);
        textView_nameProduct = findViewById(R.id.home_food_detail_name);
        textView_price = findViewById(R.id.home_food_detail_price);
        textView_description = findViewById(R.id.home_food_detail_des);
        textView_category = findViewById(R.id.home_food_detail_cat);
        textView_restaurant = findViewById(R.id.home_food_detail_res);
        // Lấy dữ liệu được truyền từ Intent
        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        String image = intent.getStringExtra("image");
        String description = intent.getStringExtra("description");
        String category = intent.getStringExtra("category");
        String price = intent.getStringExtra("price");
        String restaurant = intent.getStringExtra("restaurant");

        // Hiển thị thông tin chi tiết lên màn hình
        textView_nameProduct.setText(name);
        textView_description.setText(description);
        Glide.with(this).load(image).into(imageView);
        textView_category.setText(category);
        textView_restaurant.setText(restaurant);
        textView_price.setText(price);

        imageView_restaurant = findViewById(R.id.imageview_restaurant);
        imageView_restaurant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeFoodDetail.this, RestaurantActivity.class);
                startActivity(intent);
            }
        });

        imageView_feedBack = findViewById(R.id.imageView_feedBack);
        imageView_feedBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeFoodDetail.this, FeedBackActivity.class);
                startActivity(intent);
            }
        });

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
