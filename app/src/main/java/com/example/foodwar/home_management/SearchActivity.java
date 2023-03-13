package com.example.foodwar.home_management;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.example.foodwar.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.EventListener;
import java.util.List;

public class SearchActivity extends AppCompatActivity {
    private GridView gridView;
    private List<Food> foods = new ArrayList<>();
    private FoodAdapter foodAdapter;

    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    private DatabaseReference foodsRef = database.getReference("foods");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_foods_search);

        // Back to home
        ImageButton btn_home = findViewById(R.id.homeButton);
        btn_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SearchActivity.this, HomeActivity.class);
                startActivity(intent);
            }
        });

        // Back to previous
        ImageButton back_privious = (ImageButton) findViewById(R.id.backButton);
        back_privious.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), SearchActivity.class);
                startActivity(intent);
                finish();
            }
        });

        // Show all foods
        gridView = findViewById(R.id.foodGridView);
        foodAdapter = new FoodAdapter(this, foods);
        gridView.setAdapter(foodAdapter);

        // Load data from Realtime Database
        foodsRef.orderByChild("name").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                foods.clear(); // Clear the existing data in the foods list before adding new data
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Food food = snapshot.getValue(Food.class);
                    foods.add(food);
                }
                foodAdapter.notifyDataSetChanged(); // Notify the adapter that the data has changed
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.w("SearchActivity", "loadPost:onCancelled", databaseError.toException());
            }
        });

//        // Search
//        EditText editTextSearch = findViewById(R.id.searchEditText);
//        editTextSearch.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//
//            }
//
//            @Override
//            public void onTextChanged(CharSequence s, int start, int before, int count) {
//                String keyword = s.toString().trim();
//                Query query = foodsRef.orderByChild("name");
//                query.addListenerForSingleValueEvent(new ValueEventListener() {
//                    @Override
//                    public void onDataChange(DataSnapshot dataSnapshot) {
//                        if (gridView == null || foodAdapter == null)
//                            return; // Check null before updating data
//                        List<Food> searchResults = new ArrayList<>();
//                        for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
//                            // Convert DataSnapshot to Food object and add it to the results list
//                            Food food = snapshot.getValue(Food.class);
//                            searchResults.add(food);
//                        }
//                        // Display the search results in the GridView
//                        foods.clear(); // Clear the existing data in the foods list before adding new data
//                        foods.addAll(searchResults);
//                        foodAdapter.notifyDataSetChanged(); // Notify the adapter that the data has changed
//                        Log.d("SearchResults", searchResults.toString());
//                    }
//
//                    @Override
//                    public void onCancelled(DatabaseError databaseError) {
//                        Log.w("SearchActivity", "searchFoods:onCancelled", databaseError.toException());
//                    }
//                });
//            }
//
//            @Override
//            public void afterTextChanged(Editable s) {
//
//            }
//        });
    }
}