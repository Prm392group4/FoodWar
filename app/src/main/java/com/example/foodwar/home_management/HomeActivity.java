package com.example.foodwar.home_management;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;
import com.example.foodwar.R;
import com.example.foodwar.blogs_management.MainActivityBlogs;
import com.example.foodwar.blogs_management.MainupBlogsItems;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class HomeActivity extends AppCompatActivity implements
        LocationListener {

    private GridView gridView;
    private List<Food> foods = new ArrayList<>();
    private FoodAdapter foodAdapter;
    private Button food_bnt, drink_btn, fruit_btn;
    private ImageButton btn_blogs;
    private FloatingActionButton btn_blog_float_add;

    private static final int PERMISSION_REQUEST_LOCATION = 1;
    private Button locationTextView;
    private LocationManager locationManager;

    private ImageSlider imageSlider;
    FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();

    FirebaseFirestore db = FirebaseFirestore.getInstance();
    CollectionReference foodsRef = db.collection("foods");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_main);

        //Go to add blogs
        btn_blogs = findViewById(R.id.btn_add_blogs);
        btn_blogs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, MainActivityBlogs.class);
                startActivity(intent);
            }
        });

        // Go to add blogs float
        btn_blog_float_add = findViewById(R.id.btn_add_blogs_float);
        btn_blog_float_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, MainupBlogsItems.class);
                startActivity(intent);
            }
        });



        //Go to search activity
        TextView editTextSearch = findViewById(R.id.home_searchTextView);
        editTextSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, SearchActivity.class);
                startActivity(intent);
            }
        });

        //Go to search by location activity
        Button btn_location = findViewById(R.id.home_btn_location);
        btn_location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, SearchByLocationActivity.class);
                startActivity(intent);
            }
        });

        // Back to hoome
        ImageButton btn_home = findViewById(R.id.homeButton);
        btn_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, HomeActivity.class);
                startActivity(intent);
            }
        });

        // Back to privious

        ImageButton back_privious = (ImageButton) findViewById(R.id.backButton);
        back_privious.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                startActivity(intent);
                finish();
            }
        });


        // img slide
        imageSlider = findViewById(R.id.home_imgslideshow);
        ArrayList<SlideModel> slideModels = new ArrayList<>();

        firebaseFirestore.collection("imageslide").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {

                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot queryDocumentSnapshot : task.getResult()) {
                                slideModels.add(new SlideModel(queryDocumentSnapshot.getString("url"), ScaleTypes.FIT));
                                imageSlider.setImageList(slideModels, ScaleTypes.FIT);
                            }
                        } else {
                            Log.e("HomeActivity", "Error getting documents.", task.getException());
                            Toast.makeText(HomeActivity.this, "can not load img", Toast.LENGTH_SHORT).show();
                        }
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(HomeActivity.this, "can not load img", Toast.LENGTH_SHORT).show();
                    }
                });


        //Show list all foods
        gridView = findViewById(R.id.foodGridView);
        foodAdapter = new FoodAdapter(this, foods);
        gridView.setAdapter(foodAdapter);

        food_bnt = findViewById(R.id.home_foodButton);
        drink_btn = findViewById(R.id.home_drinkButton);
        fruit_btn = findViewById(R.id.home_fruitButton);
        foodsRef.get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot querySnapshot) {
                List<Food> foods = new ArrayList<>();
                for (DocumentSnapshot document : querySnapshot.getDocuments()) {
                    Food food = document.toObject(Food.class);
                    foods.add(food);
                }
                FoodAdapter adapter = new FoodAdapter(HomeActivity.this, foods);
                gridView.setAdapter(adapter);


                food_bnt.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Query query = FirebaseFirestore.getInstance().collection("foods").whereEqualTo("category", "food");
                        query.get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                            @Override
                            public void onSuccess(QuerySnapshot querySnapshot) {
                                List<Food> searchResults = new ArrayList<>();
                                for (DocumentSnapshot document : querySnapshot.getDocuments()) {
                                    Food food = document.toObject(Food.class);
                                    searchResults.add(food);
                                }
                                FoodAdapter adapter = new FoodAdapter(HomeActivity.this, searchResults);
                                gridView.setAdapter(adapter);
                                adapter.notifyDataSetChanged();
                                Log.d("SearchResults", searchResults.toString());
                            }
                        });
                    }
                });

                drink_btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Query query = FirebaseFirestore.getInstance().collection("foods").whereEqualTo("category", "drink");
                        query.get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                            @Override
                            public void onSuccess(QuerySnapshot querySnapshot) {
                                List<Food> searchResults = new ArrayList<>();
                                for (DocumentSnapshot document : querySnapshot.getDocuments()) {
                                    Food food = document.toObject(Food.class);
                                    searchResults.add(food);
                                }
                                FoodAdapter adapter = new FoodAdapter(HomeActivity.this, searchResults);
                                gridView.setAdapter(adapter);
                                adapter.notifyDataSetChanged();
                                Log.d("SearchResults", searchResults.toString());
                            }
                        });
                    }
                });

                fruit_btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Query query = FirebaseFirestore.getInstance().collection("foods").whereEqualTo("category", "fruit");
                        query.get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                            @Override
                            public void onSuccess(QuerySnapshot querySnapshot) {
                                List<Food> searchResults = new ArrayList<>();
                                for (DocumentSnapshot document : querySnapshot.getDocuments()) {
                                    Food food = document.toObject(Food.class);
                                    searchResults.add(food);
                                }
                                FoodAdapter adapter = new FoodAdapter(HomeActivity.this, searchResults);
                                gridView.setAdapter(adapter);
                                adapter.notifyDataSetChanged();
                                Log.d("SearchResults", searchResults.toString());
                            }
                        });
                    }
                });
            }
        });


        // Hiển Thị khu vực hiện tại.
        locationTextView = findViewById(R.id.home_btn_location);

        // Kiểm tra quyền truy cập vị trí
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // Nếu chưa có quyền truy cập vị trí, yêu cầu người dùng cho phép
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, PERMISSION_REQUEST_LOCATION);
            return;
        }

        // Lấy thông tin vị trí hiện tại
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, this);
        Location location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        if (location != null) {
            updateLocation(location);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @android.support.annotation.NonNull String[] permissions, @android.support.annotation.NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == PERMISSION_REQUEST_LOCATION) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Nếu người dùng cho phép truy cập vị trí, lấy thông tin vị trí hiện tại
                if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling
                    //    ActivityCompat#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for ActivityCompat#requestPermissions for more details.
                    return;
                }
                locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, this);
                Location location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
                if (location != null) {
                    updateLocation(location);
                }
            } else {
                // Nếu người dùng không cho phép truy cập vị trí, hiển thị thông báo
                Toast.makeText(this, "Bạn cần cấp quyền truy cập vị trí để sử dụng tính năng này", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void onLocationChanged(Location location) {
        updateLocation(location);
    }

    private void updateLocation(Location location) {
        Geocoder geocoder = new Geocoder(this, Locale.getDefault());
        try {
            List<Address> addresses = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);
            if (addresses.size() > 0) {
                Address address = addresses.get(0);
                String state = address.getAdminArea();
                String addressText ="Tp."+ state ;
                locationTextView.setText(addressText);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }


}