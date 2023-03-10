package com.example.foodwar.menu;

import android.annotation.SuppressLint;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.foodwar.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import java.text.DateFormat;
import java.util.Calendar;

public class getAccountMenu extends AppCompatActivity {
    ImageView imageAvatarNav;
    TextView UserNameNav,emailNav;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nav_header);
        imageAvatarNav = findViewById(R.id.imageAvatarNav);
        UserNameNav = findViewById(R.id.UserNameNav);
        emailNav= findViewById(R.id.emailNav);
        FirebaseUser user= FirebaseAuth.getInstance().getCurrentUser();
        FirebaseAuth firebaseAuth;
        emailNav.setText(user.getEmail());



    }
}
