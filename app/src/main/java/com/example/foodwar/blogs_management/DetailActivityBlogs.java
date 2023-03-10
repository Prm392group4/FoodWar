package com.example.foodwar.blogs_management;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.foodwar.R;

import java.text.DateFormat;
import java.util.Calendar;


public class DetailActivityBlogs extends AppCompatActivity {
    TextView detailDesc, detailTitle, publisher;
    ImageView detailImage;
    String imageURL = "";
    String key = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_blogs);


        detailDesc = findViewById(R.id.detailDesc);
        detailTitle = findViewById(R.id.detailTitle);
        detailImage = findViewById(R.id.detailImage);
        publisher = findViewById(R.id.publisher);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            // kieu string
            // set len items card
            String currentDate = DateFormat.getDateTimeInstance().format(Calendar.getInstance().getTime());

            publisher.setText(bundle.getString("publisher"));
            detailDesc.setText(bundle.getString("blog_description"));
            imageURL= bundle.getString("image_blog");
            Glide.with(this).load(bundle.getString("image_blog")).into(detailImage);
            //detailImage.setImageResource(R.drawable.avatardefaul);
            detailTitle.setText(bundle.getString("blog_name"));
        }


    }
}
