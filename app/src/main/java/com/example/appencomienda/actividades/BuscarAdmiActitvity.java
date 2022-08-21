package com.example.appencomienda.actividades;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.appencomienda.R;
import com.example.appencomienda.list.EncomiendaAdapter;
import com.example.appencomienda.modelo.Encomienda;
import com.example.appencomienda.modelo.EnvioDatos;
import com.example.appencomienda.retrofit.RetrofitCliente;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BuscarAdmiActitvity extends AppCompatActivity {
    private ListView listaView;
    private List<Encomienda> lstEncomienda;
    private Context thiscontext;
    EditText dni;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buscar_admi_actitvity);
        listaView = (ListView) findViewById(R.id.busc_listView_admi);
        thiscontext= this;
        this.dni = findViewById(R.id.busc_dni);

        findViewById(R.id.busc_btn_busc_admi).setOnClickListener(View -> buscar());
        findViewById(R.id.buscVolver_admi).setOnClickListener(View -> volver());
    }

    public void volver( ){
        Intent i = new Intent(this, Menu.class);
        startActivity(i);

    }
    public void buscar(){

        try {
            String dnitxt = this.dni.getText().toString().trim();

            if(dnitxt.isEmpty()){
                dni.setError("Ingrese el DNI");
                //Aca un reques focus ps para que se enconque ahi
                dni.requestFocus();
                //un return para que se termine
                return;
            }
            EnvioDatos env = new EnvioDatos();
            env.setDni(dnitxt);
            retrofit2.Call<EnvioDatos> call =  RetrofitCliente.getInstance().getAPI().getByDni(env);

            call.enqueue(new Callback<EnvioDatos>() {
                @Override
                public void onResponse(Call<EnvioDatos> call, Response<EnvioDatos> response) {
                    // hideProgress();
                    try {
                        if(response.code() == 200) {
                            //  String a = "This is my message" + response.isSuccessful() + " ";
                            //  Log.i("myTag", a);
                            if(response.body().getListaEncomiendas() == null){
                                System.out.println("Es null");
                            }
                            lstEncomienda = response.body().getListaEncomiendas();
                            if(lstEncomienda.size() ==0){
                                Toast.makeText(getBaseContext(), "No se encontro ninguna encomienda con el dni ingresado", Toast.LENGTH_SHORT).show();
                            }

                            //   Log.i("myTag", "tamaño " + response.body().getListaEncomiendas().size());

                            //     System.out.println("Entro perfectamente");
                            EncomiendaAdapter adapter = new EncomiendaAdapter(thiscontext, lstEncomienda);
                            listaView.setAdapter(adapter);

                            listaView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                @Override
                                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {


                                    Encomienda enco = lstEncomienda.get(i);
                                //    Toast.makeText(getBaseContext(), enco.getEncomienda(), Toast.LENGTH_SHORT).show();
                                    Intent inte = new Intent(thiscontext, EncomiendaEdit2.class);
                                    inte.putExtra("id", enco.getId());
                                    startActivity(inte);


                                }
                            });
                        }else{
                            Toast.makeText(getBaseContext(), "error al obtener data, hthpCode: "+ response.code(), Toast.LENGTH_LONG).show();
                        }
                    }catch(Exception e){
                        System.out.println("Es error perfectamente"+ e.getMessage());
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
    }
}