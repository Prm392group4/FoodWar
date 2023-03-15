package com.example.foodwar.menu;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.example.foodwar.R;
import com.example.foodwar.blogs_management.MainActivityBlogs;
import com.example.foodwar.blogs_management.MainupBlogsItems;
import com.google.android.material.navigation.NavigationView;

public class ShareFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view = inflater.inflate(R.layout.activity_upload_blogs, container, false);
        Intent intent = new Intent(view.getContext(), MainupBlogsItems.class);
        startActivity(intent);
        return view;
    }


}
