package com.example.foodwar.food_management;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.example.foodwar.R;


public class DetailFood extends AppCompatActivity {
    Toolbar toolbar;
    ImageView imageView_restaurant, imageView_feedBack, imageView;
    TextView textView_price, textView_nameProduct, textView_description;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_food);
        ActionToolBar();

        imageView = findViewById(R.id.detail_imageView);
        textView_nameProduct = findViewById(R.id.textView_nameProduct);
        textView_price = findViewById(R.id.detail_textView_price);
        textView_description = findViewById(R.id.textView_description);
//        textView_category = findViewById(R.id.home_food_detail_cat);
//        textView_restaurant = findViewById(R.id.home_food_detail_res);
        // Lấy dữ liệu được truyền từ Intent
        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        String image = intent.getStringExtra("image");
        String des = intent.getStringExtra("des");
//        String id = intent.getStringExtra("id");
//        String category = intent.getStringExtra("category");
        String price = intent.getStringExtra("price");
//        String restaurant = intent.getStringExtra("restaurant");

        // Hiển thị thông tin chi tiết lên màn hình
        textView_nameProduct.setText(name);
        textView_description.setText(des);
        Glide.with(this).load(image).into(imageView);
//        textView_category.setText(category);
//        textView_restaurant.setText(restaurant);
        textView_price.setText(price);





        imageView_restaurant = findViewById(R.id.imageview_restaurant);
        imageView_feedBack = findViewById(R.id.imageView_feedBack);

        imageView = findViewById(R.id.detail_imageView);
        textView_price = findViewById(R.id.detail_textView_price);
        textView_nameProduct = findViewById(R.id.textView_nameProduct);
        textView_description = findViewById(R.id.textView_description);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null){
            textView_price.setText(bundle.getString("Price"));
            textView_nameProduct.setText(bundle.getString("Name"));
            textView_description.setText(bundle.getString("Description"));
            Glide.with(this).load(bundle.getString("Image")).into(imageView);
        }

        imageView_restaurant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DetailFood.this, RestaurantActivity.class);
                startActivity(intent);
            }
        });

        imageView_feedBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DetailFood.this, FeedBackActivity.class);
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