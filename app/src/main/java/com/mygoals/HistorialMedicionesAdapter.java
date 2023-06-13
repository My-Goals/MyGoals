package com.mygoals;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class HistorialMedicionesAdapter extends RecyclerView.Adapter<HistorialMedicionesAdapter.ViewHolder> {
    private List<Medicion> mediciones;

    public HistorialMedicionesAdapter(List<Medicion> mediciones) {
        this.mediciones = mediciones;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_historial, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Medicion medicion = mediciones.get(position);
        holder.bind(medicion);
    }

    @Override
    public int getItemCount() {
        return mediciones.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView textViewFecha;
        private TextView textViewIMC;
        private TextView textViewGC;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewFecha = itemView.findViewById(R.id.textViewFecha);
            textViewIMC = itemView.findViewById(R.id.textViewIMC);
            textViewGC = itemView.findViewById(R.id.textViewGC);
        }

        public void bind(Medicion medicion) {
            textViewFecha.setText(medicion.getFecha());
            textViewIMC.setText(medicion.getImc());
            textViewGC.setText(medicion.getGc());
        }
    }
}
