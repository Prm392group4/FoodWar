package com.example.foodwar.food_management;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodwar.R;

import java.util.List;

public class FoodAdapter extends RecyclerView.Adapter<FoodAdapter.FoodViewHolder> {

    private Context context;
    private List<Food> listFood;

    public FoodAdapter(Context context, List<Food> listFood) {
        this.context = context;
        this.listFood = listFood;
    }

    @NonNull
    @Override
    public FoodViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_row_food,parent,false);
        return new FoodViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FoodViewHolder holder, int position) {
        Glide.with(context).load(listFood.get(position).getImage()).into(holder.imageview_image);
        holder.textview_name.setText(listFood.get(position).getName());
        holder.textview_price.setText(listFood.get(position).getPrice());
        holder.recCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, DetailFood.class);
                intent.putExtra("Description", listFood.get(holder.getAdapterPosition()).getDescription());
                intent.putExtra("Name", listFood.get(holder.getAdapterPosition()).getName());
                intent.putExtra("Price",listFood.get(holder.getAdapterPosition()).getPrice());
                intent.putExtra("Image", listFood.get(holder.getAdapterPosition()).getImage());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listFood.size();
    }

    public class FoodViewHolder extends RecyclerView.ViewHolder{

        ImageView imageview_image;
        TextView textview_name, textview_price;
        CardView recCard;

        public FoodViewHolder(@NonNull View itemView) {
            super(itemView);
            imageview_image = itemView.findViewById(R.id.imageview_image);
            textview_name = itemView.findViewById(R.id.textview_name);
            textview_price = itemView.findViewById(R.id.textview_price);
            recCard = itemView.findViewById(R.id.recCard);
        }
    }
}

