package com.example.foodwar.home_management;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.foodwar.R;

import java.util.List;

public class FoodAdapter extends BaseAdapter {
    private Context context;
    private List<Food> foods;

    public FoodAdapter(Context context, List<Food> foods) {
        this.context = context;
        this.foods = foods;
    }

    @Override
    public int getCount() {
        return foods.size();
    }

    @Override
    public Object getItem(int position) {
        return foods.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.grid_item_food, null);
        }
        Food food = foods.get(position);
        ImageView imageView = view.findViewById(R.id.foodImage);
        TextView textView = view.findViewById(R.id.foodName);
        TextView des = view.findViewById(R.id.description);
        Glide.with(context).load(food.getImage()).into(imageView);
        textView.setText(food.getName());
        des.setText(food.getDes());
        return view;
    }
}
