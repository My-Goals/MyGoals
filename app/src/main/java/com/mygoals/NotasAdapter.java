package com.mygoals;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class NotasAdapter extends RecyclerView.Adapter<NotasAdapter.ViewHolder>{
    private List<Notas> dataList;

    public List<Notas> getDataList() {
        return dataList;
    }

    public void setDataList(List<Notas> dataList) {
        this.dataList = dataList;
    }

    public NotasAdapter(List<Notas> dataList) {
        this.dataList = dataList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Notas data = dataList.get(position);
        holder.nota.setText(data.getNota());
        holder.fecha.setText(data.getDate());

    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView nota;
        TextView fecha;

        public ViewHolder(View itemView) {
            super(itemView);
            nota= itemView.findViewById(R.id.nota);
            fecha= itemView.findViewById(R.id.fecha);
        }
    }
}
