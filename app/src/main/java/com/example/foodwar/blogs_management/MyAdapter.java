package com.example.foodwar.blogs_management;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.foodwar.R;



public class MyAdapter extends RecyclerView.Adapter<MyViewHolder> {
    private Context context;
    private List<blogEntity> dataList;


    public void setSearchList(List<blogEntity> dataSearchList) {
        this.dataList = dataSearchList;
        notifyDataSetChanged();
    }

    public MyAdapter(Context context, List<blogEntity> dataList) {
        this.context = context;
        this.dataList = dataList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_item_blog, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.recCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(context, DetailActivityBlogs.class);
                intent.putExtra("image_blog", dataList.get(holder.getAdapterPosition()).getImage_blog());
                intent.putExtra("blog_name", dataList.get(holder.getAdapterPosition()).getBlog_name());
                intent.putExtra("blog_description", dataList.get(holder.getAdapterPosition()).getBlog_description());
                intent.putExtra("author", dataList.get(holder.getAdapterPosition()).getAuthor());
                intent.putExtra("publisher", dataList.get(holder.getAdapterPosition()).getPublisher());
                context.startActivity(intent);
            }
        });
        // set text for card recycleview

        Glide.with(context).load(dataList.get(position).getImage_blog()).into(holder.recImage);
        holder.recTitle.setText(dataList.get(holder.getAdapterPosition()).blog_name);
        holder.recAuthor.setText(dataList.get(holder.getAdapterPosition()).author);


    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }
}

class MyViewHolder extends RecyclerView.ViewHolder {
    ImageView recImage;
    TextView recTitle, recDesc, recAuthor;
    CardView recCard;

    public MyViewHolder(@NonNull View itemView) {

        super(itemView);
        recImage = itemView.findViewById(R.id.recImage);
        recTitle = itemView.findViewById(R.id.recTitle);
        recDesc = itemView.findViewById(R.id.recDesc);
        recCard = itemView.findViewById(R.id.recCard);
        recAuthor = itemView.findViewById(R.id.recAuthor);





    }
}
