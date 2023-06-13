package com.mygoals;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsViewHolder> implements View.OnClickListener, View.OnLongClickListener {

    private ArrayList<News> data;
    private View.OnClickListener listener;
    private View.OnLongClickListener longListener;


    public void setOnClickListener(View.OnClickListener listener) {
        this.listener = listener;
    }

    public void setOnLongClickListener(View.OnLongClickListener longListener) {
        this.longListener = longListener;
    }

    public NewsAdapter(ArrayList<News> datos) {
        this.data = datos;
    }

    @Override
    public void onClick(View view) {
        if (listener != null) {
            listener.onClick(view);
        }
    }

    public boolean onLongClick(View v) {
        if (longListener != null) {
            longListener.onLongClick(v);
            return true;
        }
        return false;
    }


    public static class NewsViewHolder extends RecyclerView.ViewHolder {
        private TextView txtTitle;
        private TextView txtDescription;
        private TextView txtLink;
        private ImageView picture;
        public NewsViewHolder(View itemView) {
            super(itemView);
            this.txtTitle = itemView.findViewById(R.id.title);
            this.txtDescription = itemView.findViewById(R.id.description);
            this.txtLink = itemView.findViewById(R.id.link);
            this.picture = (ImageView) itemView.findViewById(R.id.picture);

        }

        public void bindTitular(News news) {
            txtTitle.setText(news.getTitle());
            txtDescription.setText(news.getDescription());
            txtLink.setText(news.getLink());
            Glide.with(itemView.getContext()).load(news.getPicture()).into(picture);

        }


    }

    @Override
    public NewsViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_component, viewGroup, false);
        itemView.setOnClickListener(this);
        itemView.setOnLongClickListener(this);

        NewsViewHolder tvh = new NewsViewHolder(itemView);
        return tvh;
    }

    @Override
    public void onBindViewHolder(@NonNull NewsViewHolder holder, int position) {
        News news = data.get(position);
        holder.bindTitular(news);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }


}
