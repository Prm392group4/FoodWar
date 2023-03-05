package com.example.foodwar.user_management;

import android.os.Handler;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.airbnb.lottie.LottieAnimationView;
import com.example.foodwar.R;

public class spashScreen extends AppCompatActivity {

    ImageView logo,appName,splashImg;
    LottieAnimationView lottieAnimationView;

    Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_spash_screen);

        logo= findViewById(R.id.logo);
        appName= findViewById(R.id.app_name);
        splashImg= findViewById(R.id.img);
        lottieAnimationView= findViewById(R.id.lottie);

        splashImg.animate().translationY(-2100).setDuration(1000).setStartDelay(4000);

        logo.animate().translationY(2000).setDuration(1000).setStartDelay(4000);

        appName.animate().translationY(1400).setDuration(1000).setStartDelay(4000);

        lottieAnimationView.animate().translationY(1400).setDuration(1000).setStartDelay(4000);

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(spashScreen.this, IntroActivity.class);
                startActivity(intent);

            }
        };
        handler.postDelayed(runnable, 4500);

    }





}

