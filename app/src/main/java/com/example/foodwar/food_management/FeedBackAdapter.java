package com.example.foodwar.food_management;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.example.foodwar.R;

import java.util.List;

public class FeedBackAdapter extends ArrayAdapter<FeedBack> {

    private Context context;
    private List<FeedBack> feedbackList;

    public FeedBackAdapter(Context context, List<FeedBack> feedbackList) {
        super(context, 0, feedbackList);
        this.context = context;
        this.feedbackList = feedbackList;
    }

    @Override
    public View getView(int position, @Nullable View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.activity_row_feedback, parent, false);
        }

        // Lấy các views trong layout
        TextView textViewContent = view.findViewById(R.id.textview_feedback);
        RatingBar ratingBar = view.findViewById(R.id.ratingStar);

        // Lấy đối tượng Feedback tại vị trí position
        FeedBack feedback = feedbackList.get(position);

        // Hiển thị dữ liệu lên các views
        textViewContent.setText(feedback.getFeedBack());
        ratingBar.setRating(feedback.getRatingStar());

        return view;
    }
}

