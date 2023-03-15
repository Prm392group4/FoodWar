package com.example.foodwar.menu;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.example.foodwar.R;
import com.example.foodwar.blogs_management.MainActivityBlogs;
import com.google.android.material.navigation.NavigationView;

public class SettingsFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view =inflater.inflate(R.layout.fragment_settings, container, false);
        Intent intent =  new Intent(view.getContext(), MainDarkMode.class);
        startActivity(intent);

        return view;
    }


}
