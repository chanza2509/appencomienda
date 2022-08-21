package com.example.appencomienda.list;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.appencomienda.R;
import com.example.appencomienda.modelo.Encomienda;

import java.util.List;

public class EncomiendaAdapter extends BaseAdapter {

    Context context;
    List<Encomienda> lista;

    public EncomiendaAdapter(Context context, List<Encomienda> list) {
        this.context = context;
        this.lista = list;
    }

    @Override
    public int getCount() {
        return lista.size();
    }
    /*
    @Override
    public int getItemCount() {
        return list.size();
    }*/
    @Override
    public Object getItem(int i) {
        return i;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

       String[] Estado = {"Enviado","Cancelado","Recibido","Entregado"};
        TextView encomienda;
        TextView dni;
        TextView nombre;
        TextView precio;
        TextView estado;
        TextView destino;

        Encomienda encomiendaobj = lista.get(i);


        if(view == null){
            view = LayoutInflater.from(context).inflate(R.layout.item_inmueble, null);
        }

        encomienda =view.findViewById(R.id.tvEncomiendaItem);
        dni = view.findViewById(R.id.tvDNIItem);
        nombre = view.findViewById(R.id.tvNombresItem);
        precio = view.findViewById(R.id.tvPrecioItem);
        estado = view.findViewById(R.id.tvEstadoItem);
        destino = view.findViewById(R.id.tvDestinoItem);
        ImageView ivEstado = view.findViewById(R.id.ivEstadoItem);

        switch (encomiendaobj.getIdEstado()){
            case 1:
                ivEstado.setImageResource(R.drawable.e1);
                break;
            case 2: ivEstado.setImageResource(R.drawable.e2);break;
            case 3: ivEstado.setImageResource(R.drawable.recibido); break;
            case 4: ivEstado.setImageResource(R.drawable.e3); break;

        }

        encomienda.setText(  "Encomienda: "+encomiendaobj.getEncomienda());
        dni.setText(" Dni: " + encomiendaobj.getDni());
        nombre.setText("Datos: " +encomiendaobj.getNombres() + " " + encomiendaobj.getApellidos() );
        precio.setText("Precio: "+ encomiendaobj.getPrecio());
        destino.setText("Destino: "+ encomiendaobj.getDestino());
        estado.setText("Estado: " +Estado[encomiendaobj.getIdEstado()-1]);
        return view;

    }
}
