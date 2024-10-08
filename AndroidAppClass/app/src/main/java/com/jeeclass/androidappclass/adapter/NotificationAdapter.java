package com.jeeclass.androidappclass.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.jeeclass.androidappclass.Notification;
import com.jeeclass.androidappclass.R;
import com.jeeclass.androidappclass.model.NotificationModel;

import java.util.List;

public class NotificationAdapter extends RecyclerView.Adapter<NotificationAdapter.ViewHolder> {

    private List<NotificationModel> notificationList;
    private Context context;

    public NotificationAdapter(List<NotificationModel> notificationList, Context context) {
        this.notificationList = notificationList;
        this.context = context;
    }

    @NonNull
    @Override
    public NotificationAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.notification_card, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NotificationAdapter.ViewHolder holder, int position) {

        NotificationModel notificationModel = notificationList.get(position);
        holder.textDate.setText(notificationModel.getDate());
        holder.textNews.setText(notificationModel.getNews());
    }

    @Override
    public int getItemCount() {
        return notificationList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        TextView textTitle;
        TextView textDate;
        TextView textNews;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            textDate = itemView.findViewById(R.id.notification_time);
            textNews = itemView.findViewById(R.id.notification_news);
            textTitle = itemView.findViewById(R.id.notification_title);
        }
    }
}
