package com.example.appevacortei.Adaptador;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.appevacortei.Modelos.TuristicosQuevedo;
import com.example.appevacortei.R;

import java.util.List;


public class Turistico_adaptador extends RecyclerView.Adapter<Turistico_adaptador.LugarTuristicoViewHolder> {
    private Context Ctx;
    private List<TuristicosQuevedo> lstLugar;

    public Turistico_adaptador(Context mCtx, List<TuristicosQuevedo> lstLugarT) {
        this.lstLugar = lstLugarT;
        Ctx = mCtx;
    }

    @Override
    public LugarTuristicoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(Ctx);
        View view = inflater.inflate(R.layout.seleccionar_ly, null);
        return new LugarTuristicoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(LugarTuristicoViewHolder holder, int position) {

        TuristicosQuevedo lg = lstLugar.get(position);

        holder.textViewName.setText(lg.getNombre());
        holder.textViewDireccion.setText(lg.getDireccion());
        holder.textTelefono.setText(lg.getTelfono());
        Glide.with(Ctx)
                .load(lg.getURLlogo())
                .into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return lstLugar.size();
    }

    class LugarTuristicoViewHolder extends RecyclerView.ViewHolder {
        TextView textViewName, textViewDireccion, textTelefono;
        ImageView imageView;
        public LugarTuristicoViewHolder(View itemView) {
            super(itemView);
            textViewName= itemView.findViewById(R.id.txtName);
            textViewDireccion = itemView.findViewById(R.id.txtDireccion);
            textTelefono = itemView.findViewById(R.id.txtTelefono);
            imageView = itemView.findViewById(R.id.imgLg);
        }
    }
}