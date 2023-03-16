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
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import com.example.foodwar.R;
import com.example.foodwar.user_management.UserProfileMain;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;


public class SearchByLocationActivity extends AppCompatActivity implements
        LocationListener {

    private static final int PERMISSION_REQUEST_LOCATION = 1;
    private TextView locationTextView;
    private LocationManager locationManager;
    private GridView gridView;
    private FoodAdapter foodAdapter;
    private List<Food> foods = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_foods_search_location);
        locationTextView = findViewById(R.id.location_text_view);

        // Go to fooddetail
        gridView = findViewById(R.id.locationGridview);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Food food = (Food) parent.getItemAtPosition(position);
                Intent intent = new Intent(SearchByLocationActivity.this, HomeFoodDetail.class);
                intent.putExtra("name", food.getName());
                intent.putExtra("image", food.getImage());
                intent.putExtra("description",food.getDescription());
                intent.putExtra("category",food.getCategory());
                intent.putExtra("price",food.getPrice());
                intent.putExtra("restaurant",food.getRestaurant());
                startActivity(intent);
            }
        });


        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String data = extras.getString("data");
            gridView = findViewById(R.id.locationGridview);
            foodAdapter = new FoodAdapter(this, foods);
            gridView.setAdapter(foodAdapter);

            FirebaseDatabase database = FirebaseDatabase.getInstance();
            DatabaseReference restaurantsRef = database.getReference("restaurants");
            DatabaseReference foodsRef = database.getReference("foods");
            Query query = restaurantsRef.orderByChild("address").equalTo(data);
            query.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    List<String> restaurantIds = new ArrayList<>();
                    for (DataSnapshot restaurantSnapshot : dataSnapshot.getChildren()) {
                        String restaurantId = restaurantSnapshot.getKey();
                        restaurantIds.add(restaurantId);
                        Log.d("TAG", "Danh sach nha hang " +  restaurantIds);
                    }
                    Query query = foodsRef.orderByChild("resID").equalTo(1);
                    query.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            Log.d("TAG", "Number of child nodes: " + dataSnapshot.getChildrenCount());
                            for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                                Food food = snapshot.getValue(Food.class);
                                foods.add(food);
                                Log.d("TAG", "danh sách foods: " + foods);
                            }
                            foodAdapter.notifyDataSetChanged(); // Notify the adapter that the data has changed
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {
                            Log.w("TAG", "Failed to read value.", databaseError.toException());
                        }
                    });
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                    Log.w("TAG", "Failed to read value.", databaseError.toException());
                }
            });

        }

        //Goto profile
        ImageButton btnuserProfile = findViewById(R.id.profileButton);
        btnuserProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SearchByLocationActivity.this, UserProfileMain.class);
                startActivity(intent);
            }
        });
        // Back to home
        ImageButton btn_home = findViewById(R.id.homeButton);
        btn_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SearchByLocationActivity.this, HomeActivity.class);
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
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
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
                String country = address.getCountryName();
                String addressText = "Khu vực của bạn: " + state + ", " + country;
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
