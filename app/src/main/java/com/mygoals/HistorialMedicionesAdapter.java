package com.mygoals;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class HistorialMedicionesAdapter extends RecyclerView.Adapter<HistorialMedicionesAdapter.ItemViewHolder> {

    private ArrayList<Medicion> itemList;

    public HistorialMedicionesAdapter(ArrayList<Medicion> itemList) {
        this.itemList = itemList;
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_historial, parent, false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        Medicion item = itemList.get(position);
        holder.textFecha.setText(item.getFecha());
        holder.textIMC.setText(item.getImc());
        holder.textGC.setText(item.getGc());
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public static class ItemViewHolder extends RecyclerView.ViewHolder {
        TextView textFecha;
        TextView textIMC;
        TextView textGC;

        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            textFecha = itemView.findViewById(R.id.textViewFecha);
            textIMC = itemView.findViewById(R.id.textViewIMC);
            textGC = itemView.findViewById(R.id.textViewGC);
        }
    }
}
