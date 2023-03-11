package com.example.foodwar.home_management;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageButton;
import androidx.appcompat.app.AppCompatActivity;
import com.example.foodwar.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class SearchActivity extends AppCompatActivity {

    private GridView gridView;
    private List<Food> foods = new ArrayList<>();
    private FoodAdapter foodAdapter;

    FirebaseFirestore db = FirebaseFirestore.getInstance();
    CollectionReference foodsRef = db.collection("foods");



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_foods_search);

        // Back to hoome
        ImageButton btn_home = findViewById(R.id.homeButton);
        btn_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SearchActivity.this, HomeActivity.class);
                startActivity(intent);
            }
        });


        // Back to privious

        ImageButton back_privious =(ImageButton) findViewById(R.id.backButton);
        back_privious.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),SearchActivity.class);
                startActivity(intent);
                finish();
            }
        });

        //Show list all foods
        gridView = findViewById(R.id.foodGridView);

        foodAdapter = new FoodAdapter(this, foods);
        gridView.setAdapter(foodAdapter);

        foodsRef.get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot querySnapshot) {
                List<Food> foods = new ArrayList<>();
                for (DocumentSnapshot document : querySnapshot.getDocuments()) {
                    Food food = document.toObject(Food.class);
                    foods.add(food);
                }
                FoodAdapter adapter = new FoodAdapter(SearchActivity.this, foods);
                gridView.setAdapter(adapter);
            }
        });


        //Search
        EditText editTextSearch = findViewById(R.id.searchEditText);
        editTextSearch.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String keyword = s.toString().trim();
                Query query = FirebaseFirestore.getInstance().collection("foods")
                        .whereGreaterThanOrEqualTo("name", keyword)
                        .whereLessThanOrEqualTo("name", keyword + "\uf8ff");
                query.get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot querySnapshot) {
                        List<Food> searchResults = new ArrayList<>();
                        for (DocumentSnapshot document : querySnapshot.getDocuments()) {

                            // Chuyển đổi dữ liệu từ DocumentSnapshot thành đối tượng Food và thêm vào danh sách kết quả
                            Food food = document.toObject(Food.class);
                            searchResults.add(food);

                        }
                        // Hiển thị danh sách kết quả tìm kiếm trong GridView
                        FoodAdapter adapter = new FoodAdapter(SearchActivity.this, searchResults);
                        gridView.setAdapter(adapter);
                        adapter.notifyDataSetChanged();
                        Log.d("SearchResults", searchResults.toString());
                    }
                });
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
     }
    }
