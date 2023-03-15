package com.example.foodwar.blogs_management;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.foodwar.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.MutableData;
import com.google.firebase.database.Transaction;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.text.DateFormat;
import java.util.Calendar;


public class DetailActivityBlogs extends AppCompatActivity {
    TextView detailDesc, detailTitle, publisher, author;
    ImageView detailImage;
    int likeCount = 0;
    Button likeButton, dislikebutton;
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
        author = findViewById(R.id.authorItems);
        likeButton = findViewById(R.id.buttonLike);
        dislikebutton= findViewById(R.id.buttonUnLike);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            // kieu string
            // set len items card
            // String currentDate = DateFormat.getDateTimeInstance().format(Calendar.getInstance().getTime());

            publisher.setText(bundle.getString("publisher"));
            detailDesc.setText(bundle.getString("blog_description"));
            imageURL = bundle.getString("image_blog");
            author.setText(bundle.getString("author"));
            Glide.with(this).load(bundle.getString("image_blog")).into(detailImage);
            //key = bundle.getString("Key");
            detailTitle.setText(bundle.getString("blog_name"));
        }


        TextView likecount;
        likecount = findViewById(R.id.textViewlike);
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("post/1");
        myRef.child("like").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // Lấy giá trị hiện tại của lượt thích (like)
                Integer currentLikes = dataSnapshot.getValue(Integer.class);

                // Cập nhật giá trị mới cho lượt thích (like)
                likecount.setText(String.valueOf(currentLikes)+" Like ");
//
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Toast.makeText(DetailActivityBlogs.this, "Fail!", Toast.LENGTH_SHORT).show();

            }
        });

        likeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //likeCount++;
                //String likeCountText = likeCount + " likes";
                //likecount.setText(likeCountText);
                myRef.child("like").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        // Lấy giá trị hiện tại của lượt thích (like)
                        Integer currentLikes = dataSnapshot.getValue(Integer.class);

                        // Cập nhật giá trị mới cho lượt thích (like)
                        myRef.child("like").setValue(currentLikes + 1);
                        likecount.setText(String.valueOf(currentLikes + 1 +" Like"));

                        Toast.makeText(DetailActivityBlogs.this, "Liked", Toast.LENGTH_SHORT).show();
                        likeButton.setEnabled(false);
                        dislikebutton.setEnabled(true);
                        ColorStateList colorStateList = ColorStateList.valueOf(Color.rgb(255,147,163));

                        // Thiết lập màu nền tô cho nút
                        likeButton.setBackgroundTintList(colorStateList);

//
                    }

                    @Override
                    public void onCancelled(DatabaseError error) {
                        // Failed to read value
                        Toast.makeText(DetailActivityBlogs.this, "Fail!", Toast.LENGTH_SHORT).show();

                    }
                });


            }
        });


        dislikebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //likeCount++;
                //String likeCountText = likeCount + " likes";
                //likecount.setText(likeCountText);
                myRef.child("like").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        // Lấy giá trị hiện tại của lượt thích (like)
                        Integer currentLikes = dataSnapshot.getValue(Integer.class);

                        // Cập nhật giá trị mới cho lượt thích (like)
                        myRef.child("like").setValue(currentLikes - 1);
                        likecount.setText(String.valueOf(currentLikes - 1)+" Like");

                        Toast.makeText(DetailActivityBlogs.this, "UnLiked", Toast.LENGTH_SHORT).show();
                        dislikebutton.setEnabled(false);
                        likeButton.setEnabled(true);
                        ColorStateList colorStateList = ColorStateList.valueOf(Color.GRAY);

                        // Thiết lập màu nền tô cho nút
                        dislikebutton.setBackgroundTintList(colorStateList);

//
                    }

                    @Override
                    public void onCancelled(DatabaseError error) {
                        // Failed to read value
                        Toast.makeText(DetailActivityBlogs.this, "Fail!", Toast.LENGTH_SHORT).show();

                    }
                });


            }
        });



    }
}
