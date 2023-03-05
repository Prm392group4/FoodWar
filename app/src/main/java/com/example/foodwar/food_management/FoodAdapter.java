package com.example.foodwar.food_management;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.foodwar.R;

import java.util.List;

public class FoodAdapter extends BaseAdapter {
    private Context context;
    private int layout;
    private List<Food> foodList;

    public FoodAdapter(Context context, int layout, List<Food> foodList) {
        this.context = context;
        this.layout = layout;
        this.foodList = foodList;
    }

    @Override
    public int getCount() {
        return foodList.size();
    }

    @Override
    public Object getItem(int i) {
        return foodList.get(i);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(layout, null);

        TextView tv_name = (TextView) view.findViewById(R.id.textview_name);
        TextView tv_price = (TextView) view.findViewById(R.id.textview_price);
        ImageView iv_image = (ImageView) view.findViewById(R.id.imageview_image);

        Food food = foodList.get(i);

        tv_name.setText(food.getName());
        tv_price.setText(food.getPrice());
        iv_image.setImageResource(food.getImage());
        return view;
    }
}
