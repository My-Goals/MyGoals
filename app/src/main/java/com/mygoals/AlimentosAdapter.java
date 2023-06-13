package com.mygoals;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class AlimentosAdapter extends RecyclerView.Adapter<AlimentosAdapter.AlimentoViewHolder> {

    private Context context;
    private List<Alimento> alimentos;

    public AlimentosAdapter(Context context, List<Alimento> alimentos) {
        this.context = context;
        this.alimentos = alimentos;
    }

    @NonNull
    @Override
    public AlimentoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.item_resultados, parent, false);
        return new AlimentoViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull AlimentoViewHolder holder, int position) {
        Alimento alimento = alimentos.get(position);

        holder.textViewNombre.setText(alimento.getNombre());
        holder.textViewGrasas.setText("Grasas: " + alimento.getGrasas());
        holder.textViewProteinas.setText("Proteínas: " + alimento.getProteinas());
        holder.textViewCarbohidratos.setText("Carbohidratos: " + alimento.getCarbohidratos());
        holder.textViewKcal.setText("Kcal: " + alimento.getEnergia());
    }

    @Override
    public int getItemCount() {
        return alimentos.size();
    }

    public class AlimentoViewHolder extends RecyclerView.ViewHolder {
        TextView textViewNombre, textViewGrasas, textViewProteinas, textViewCarbohidratos, textViewKcal;

        public AlimentoViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewNombre = itemView.findViewById(R.id.textViewNombre);
            textViewGrasas = itemView.findViewById(R.id.textViewGrasas);
            textViewProteinas = itemView.findViewById(R.id.textViewProteinas);
            textViewCarbohidratos = itemView.findViewById(R.id.textViewCarbohidratos);
            textViewKcal = itemView.findViewById(R.id.textViewKcal);
        }
    }
}