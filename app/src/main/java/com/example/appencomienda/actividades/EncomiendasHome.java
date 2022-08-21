package com.example.appencomienda.actividades;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.appencomienda.R;
import com.example.appencomienda.list.EncomiendaAdapter;
import com.example.appencomienda.modelo.Encomienda;
import com.example.appencomienda.modelo.EnvioDatos;
import com.example.appencomienda.retrofit.RetrofitCliente;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EncomiendasHome extends AppCompatActivity {

    private ListView listaView;
    private List<Encomienda> lstEncomienda;
    private Context thiscontext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_encomiendas_home);

        listaView = (ListView) findViewById(R.id.listaInmuebles);
        thiscontext= this;

        SetearData();

        findViewById(R.id.EncVolver).setOnClickListener(View -> volver());
    }
    public void volver( ){
        Intent i = new Intent(this, Menu.class);
        startActivity(i);
    }

    private  void SetearData(){

        try {
            retrofit2.Call<EnvioDatos> call =  RetrofitCliente.getInstance().getAPI().getEncomiendas();

            call.enqueue(new Callback<EnvioDatos>() {
                @Override
                public void onResponse(Call<EnvioDatos> call, Response<EnvioDatos> response) {
                   // hideProgress();
                    try {
                        String  a=   "This is my message"+ response.isSuccessful() + " ";
                       Log.i("myTag", a);

                        lstEncomienda = response.body().getListaEncomiendas();


                        Log.i("myTag", "tamaño "+ response.body().getListaEncomiendas().size());

                        System.out.println("Entro perfectamente");
                        EncomiendaAdapter adapter = new EncomiendaAdapter(thiscontext,lstEncomienda);
                        listaView.setAdapter(adapter);

                        listaView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
                            @Override
                            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                                Encomienda enco = lstEncomienda.get(i);
                               // Toast.makeText(getBaseContext(), enco.getEncomienda(), Toast.LENGTH_SHORT).show();
                                Intent inte = new Intent(thiscontext, EncomiendaEdit.class);
                                inte.putExtra("id", enco.getId());
                                startActivity(inte);

                            }
                        } );

                    }catch(Exception e){
                        System.out.println("Es error perfectamente");
                            Toast.makeText(getBaseContext(), "error al obtener data"+ e.getMessage(), Toast.LENGTH_LONG).show();
                    }

                    }

                @Override
                public void onFailure(Call<EnvioDatos> call, Throwable t) {
                    //hideProgress();
                   // showProgress("Errror personajes.......");
                    Toast.makeText(getBaseContext(), "error al obtener data", Toast.LENGTH_SHORT).show();
                }
            });
        }catch (Exception e){
            System.out.println("Es error perfectamente");
           // hideProgress();
            //System.out.println("sssssssssssssSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSerror]: " + e.getMessage() );
        }


        //return lstEncomienda;
    }
}