package com.example.foodwar.blogs_management;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.foodwar.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.text.DateFormat;
import java.util.Calendar;


public class DetailActivityBlogs extends AppCompatActivity {
    TextView detailDesc, detailTitle, publisher, author;
    ImageView detailImage;
    Button likeButton;
    com.github.clans.fab.FloatingActionButton deleteButton , editButton;
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
        author= findViewById(R.id.authorItems);
        deleteButton =  findViewById(R.id.deleteButton);
        editButton = findViewById(R.id.editButton);
        likeButton= findViewById(R.id.buttonLike);

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
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final DatabaseReference reference = FirebaseDatabase.getInstance().getReference("blogs");
                FirebaseStorage storage = FirebaseStorage.getInstance();
                StorageReference storageReference = storage.getReferenceFromUrl(imageURL);
                storageReference.delete().addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        reference.child(key).removeValue();
                        Toast.makeText(DetailActivityBlogs.this, "Deleted", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(getApplicationContext(), MainActivityBlogs.class));
                        finish();
                    }
                });
            }
        });
        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DetailActivityBlogs.this, UpdateActivity.class)
                        .putExtra("blog_name", detailTitle.getText().toString())
                        .putExtra("blog_description", detailDesc.getText().toString())
                        .putExtra("author", author.getText().toString())
                        .putExtra("image_blog", imageURL)
                        .putExtra("Key", key);
                startActivity(intent);
            }
        });


    }
}
