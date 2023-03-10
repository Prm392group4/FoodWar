package com.example.foodwar.user_management;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.example.foodwar.R;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class IntroActivity extends AppCompatActivity {
    private ViewPager screenPager;
    IntroViewPagerAdapter introViewPagerAdapter ;
    TabLayout tabIndicator;
    Button btnNext, btnGetStarted;
    int position = 0;
    Animation btnAnim;
    TextView tvSkip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // make the activity on full screen
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
//block this code for represent purpose
        // when this activity is about to be launch we need to check if its openened before or not
//        if (restorePrefData()) {
//
//            Intent loginActivity = new Intent(getApplicationContext(), LoginActivity.class );
//            startActivity(loginActivity);
//            finish();
//        }

        setContentView(R.layout.activity_intro);

        // hide the action bar
//        getSupportActionBar().hide();

        // ini views
        btnNext = findViewById(R.id.btn_next);
        btnGetStarted = findViewById(R.id.btn_get_started);
        tabIndicator = findViewById(R.id.tab_indicator);
        btnAnim = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.button_animation);
        tvSkip = findViewById(R.id.tv_skip);

        // fill list screen
        final List<ScreenItem> mList = new ArrayList<>();
        mList.add(new ScreenItem("Reivew Ch??n Th???t"," t???t c??? ????? ??n s??? ???????c ????nh gi?? b???i nh???ng ng?????i d??ng v?? c??? nh???ng chuy??n gia ???m th???c uy t??n t??? nhi???u mi???n ???m th???c kh??c nhau", R.drawable.img1));
        mList.add(new ScreenItem("C???p nh???t nhanh ch??ng","review s??? ???????c c???p nh???t theo th???i gian th???c. B???n s??? th???y review c???a m??nh v?? nh???ng ng?????i d??ng kh??c ngay l???p t???c khi b???n ???n n??t", R.drawable.img_reivew3));
        mList.add(new ScreenItem("??a d???ng m??n ??n","c??c m??n ??n ???????c t???ng h???p t??? m???i mi???n tr??n ?????t n?????c. H??y y??n t??m r???ng b???n s??? lu??n t??m th???y m??n ??n b???n c???n ??? ????y", R.drawable.img_variety_food));
        mList.add(new ScreenItem("H??y C??ng T??m","D?? ??t hay nhi???u th?? l???i review c???a b???n s??? ???nh h?????ng ?????n c??c nh???ng ng?????i d??ng kh??c n??n h??y tu??n th??? ti??u chu???n c???ng ?????ng v?? h??y ngh?? k?? tr?????c khi ????nh gi??", R.drawable.img_congtam));

        // setup viewpager
        screenPager =findViewById(R.id.screen_viewpager);
        introViewPagerAdapter = new IntroViewPagerAdapter(this, mList);
        screenPager.setAdapter(introViewPagerAdapter);

        // setup tablayout with viewpager
        tabIndicator.setupWithViewPager(screenPager);

        // next button click Listener
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                position = screenPager.getCurrentItem();
                if (position < mList.size()) {

                    position++;
                    screenPager.setCurrentItem(position);
                }

                if (position == mList.size()-1) { // when we rech to the last screen
                    // TODO : show the GETSTARTED Button and hide the indicator and the next button
                    loaddLastScreen();
                }
            }
        });

        // tablayout add change listener
        tabIndicator.addOnTabSelectedListener(new TabLayout.BaseOnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

                if (tab.getPosition() == mList.size()-1) {
                    loaddLastScreen();
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        // Get Started button click listener
        btnGetStarted.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //open main activity
                Intent loginActivity = new Intent(getApplicationContext(),LoginActivity.class);
                startActivity(loginActivity);
             //hold data of user open the app for the first time
//                savePrefsData();
                finish();
            }
        });

        // skip button click listener
        tvSkip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                screenPager.setCurrentItem(mList.size());
            }
        });

        //check login


    }
//block this code for represent purpose
//    private boolean restorePrefData() {
//        SharedPreferences pref = getApplicationContext().getSharedPreferences("myPrefs",MODE_PRIVATE);
//        Boolean isIntroActivityOpnendBefore = pref.getBoolean("isIntroOpnend",false);
//        return  isIntroActivityOpnendBefore;
//    }
//
//    private void savePrefsData() {
//        SharedPreferences pref = getApplicationContext().getSharedPreferences("myPrefs",MODE_PRIVATE);
//        SharedPreferences.Editor editor = pref.edit();
//        editor.putBoolean("isIntroOpnend",true);
//        editor.commit();
//    }

    // show the GETSTARTED Button and hide the indicator and the next button
    private void loaddLastScreen() {

        btnNext.setVisibility(View.INVISIBLE);
        btnGetStarted.setVisibility(View.VISIBLE);
        tvSkip.setVisibility(View.INVISIBLE);
        tabIndicator.setVisibility(View.INVISIBLE);
        // TODO : ADD an animation the getstarted button
        // setup animation
        btnGetStarted.setAnimation(btnAnim);
    }




}
