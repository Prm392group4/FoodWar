package com.example.foodwar.user_management;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.foodwar.R;
import com.example.foodwar.entity.UserProfile;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

public class ProfileActivity extends AppCompatActivity {
    DatabaseReference reference;
    FirebaseDatabase database;
    TextView profileName, profileEmail, profileNum, adressProfile;
    Button editProfilebtn;
    String nameUser, emailUser, usernameUser, passwordUser;
    TextView titleName, titleUserEmail;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        profileName = findViewById(R.id.profileName);
        profileEmail = findViewById(R.id.profileEmail);
        profileNum = findViewById(R.id.phoneProfile);
        adressProfile = findViewById(R.id.adressProfile);
//        titleName = findViewById(R.id.titleName);
//        titleUserEmail = findViewById(R.id.titleEmail);
       // editProfilebtn = findViewById(R.id.editButton);
        showAllUserData();
//        editProfilebtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                passUserData();
//            }
//        });


    }

    public void showAllUserData() {
        Intent intent = getIntent();
       // String name = intent.getStringExtra("name");
        String emailUser = intent.getStringExtra("email");
//        String mobile_num = intent.getStringExtra("mobile_num");
//        String adress = intent.getStringExtra("adress");
        //profileName.setText(name);
        profileEmail.setText(emailUser);
//        adressProfile.setText(adress);
//        profileNum.setText(mobile_num);

//        database = FirebaseDatabase.getInstance();
//        reference = database.getReference().child("user_data");
//        reference.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                UserProfile userProfile = snapshot.getValue(UserProfile.class);
//                profileName.setText(userProfile.getname());
//                profileEmail.setText(userProfile.getemail());
//                adressProfile.setText(userProfile.getadress());
//                profileNum.setText(userProfile.getmobile_num());
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//                System.out.println("The read failed: " + error.getCode());
//            }
//        });
    }


//    public void passUserData() {
//        String userUsername = profileName.getText().toString().trim();
//        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("user_data");
//        Query checkUserDatabase = reference.orderByChild("name").equalTo(userUsername);
//        checkUserDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                if (snapshot.exists()) {
//                    String nameFromDB = snapshot.child(userUsername).child("name").getValue(String.class);
//                    String emailFromDB = snapshot.child(userUsername).child("email").getValue(String.class);
//                    String adress = snapshot.child(userUsername).child("adress").getValue(String.class);
//                    String phone = snapshot.child(userUsername).child("mobile_num").getValue(String.class);
//                    Intent intent = new Intent(ProfileActivity.this, EditProfileActivity.class);
//                    intent.putExtra("name", nameFromDB);
//                    intent.putExtra("email", emailFromDB);
//                    intent.putExtra("adress", adress);
//                    intent.putExtra("mobile_num", phone);
//                    startActivity(intent);
//                }
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//            }
//        });
//
//    }
}