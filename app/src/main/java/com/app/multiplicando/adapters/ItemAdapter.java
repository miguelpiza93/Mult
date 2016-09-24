package com.app.multiplicando.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.app.multiplicando.R;
import com.app.multiplicando.interfaces.IAdapterComunication;

import java.util.List;

/**
 * Created by Miguel on 23/02/2016.
 */
public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.JuegoViewHolder>  {

    private List<Item> items;
    private static IAdapterComunication listener;

    public ItemAdapter(IAdapterComunication listener, List<Item> juegos){
        ItemAdapter.listener = listener;
        this.items = juegos;
    }

    @Override
    public JuegoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_view_layout, parent, false);
        return new JuegoViewHolder(v);
    }

    @Override
    public void onBindViewHolder(JuegoViewHolder viewHolder, int i) {
        viewHolder.nombre.setText(items.get(i).getNombre());
        viewHolder.descripcion.setText(items.get(i).getDescripcion());
        viewHolder.numero = items.get(i).getNumero();
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public static class JuegoViewHolder extends RecyclerView.ViewHolder{

        public TextView nombre;
        public TextView descripcion;
        public int numero;

        public JuegoViewHolder(final View itemView) {
            super(itemView);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override public void onClick(View v) {
                    listener.callBack(numero);
                }
            });

            nombre = (TextView) itemView.findViewById(R.id.nombre);
            descripcion = (TextView) itemView.findViewById(R.id.descripcion);
        }
    }
}
