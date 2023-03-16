package com.example.foodwar.food_management;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;

import com.example.foodwar.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class FeedBackActivity extends AppCompatActivity {
    EditText feedBack;
    Button submit;
    RatingBar rateBar;
    ArrayList<FeedBack> listFeedBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed_back);
        ActionToolBar();
        Init();
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pushData();
            }
        });
    }

    //create back button
    private void ActionToolBar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolBar_feedBack);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void pushData() {
        // Write a message to the database
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference();
        String content = feedBack.getText().toString();
        float ratingStar = rateBar.getRating();

        // Create FeedbackModel object
        FeedBack feedback = new FeedBack(content, ratingStar);

        if (!feedBack.getText().toString().isEmpty()||!rateBar.toString().isEmpty()) {
            myRef.child("FeedBack").push().setValue(feedback)
                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            // Feedback saved successfully
                            Toast.makeText(FeedBackActivity.this, "Feedback submitted successfully", Toast.LENGTH_SHORT).show();
                            finish();
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            // Failed to save feedback
                            Toast.makeText(FeedBackActivity.this, "Failed to submit feedback", Toast.LENGTH_SHORT).show();
                        }
                    });
        }else{
            Toast.makeText(FeedBackActivity.this, "Fill in FeedBack or Rate Star", Toast.LENGTH_SHORT).show();
        }
    }

    private void readDatabase() {
        // Read from the database
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("FeedBack");
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                String value = dataSnapshot.getValue(String.class);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Failed to read value
            }
        });
    }

    private void Init() {
        feedBack = findViewById(R.id.feedBack);
        rateBar = findViewById(R.id.ratingBar);
        submit = findViewById(R.id.submit);
    }
}