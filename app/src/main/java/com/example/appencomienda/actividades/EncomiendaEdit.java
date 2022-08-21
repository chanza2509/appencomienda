package com.example.appencomienda.actividades;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.appencomienda.R;
import com.example.appencomienda.modelo.Encomienda;
import com.example.appencomienda.retrofit.RetrofitCliente;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EncomiendaEdit extends AppCompatActivity {
    Spinner spinner;
    ArrayList<String> Estados = new ArrayList<>();
    TextView dni, nombres, apellidos, precio, encomiendaTv, destino;
    Encomienda encomiendaObj;
    int id;
    Context thiscontext;
    ArrayAdapter<String> adapterSpinner;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_encomienda_edit);
        //Obtenemos ID
        this.id = (getIntent().getExtras()).getInt("id");
        findViewById(R.id.editVolver).setOnClickListener(View -> volver());
        findViewById(R.id.edit_btn_Editar).setOnClickListener(View -> editar());


        //Vamos a setear los tviewer
        this.dni = findViewById(R.id.editDni);
        this.nombres = findViewById(R.id.editNombres);
        this.apellidos = findViewById(R.id.editApellidos);
        this.precio = findViewById(R.id.editPrecio);
        this.encomiendaTv = findViewById(R.id.editEncomienda);
        this.destino = findViewById(R.id.editDestino);



        Toast.makeText(getBaseContext(), id + " ", Toast.LENGTH_SHORT).show();
        spinner = findViewById(R.id.tvspinnerEstado);

        this.thiscontext = this;
        this.Estados.add("Transito");
        this.Estados.add("Cancelado");
        this.Estados.add("Recibido");
        this.Estados.add("Entregado");
        adapterSpinner = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item,this.Estados);
        spinner.setAdapter(adapterSpinner);
        cargarData();

    }

    public void volver( ){
        Intent i = new Intent(this, EncomiendasHome.class);
        startActivity(i);
    }

    public void cargarData(){
        try {
            retrofit2.Call<Encomienda> call =  RetrofitCliente.getInstance().getAPI().getEncomiedabyId(this.id);

            call.enqueue(new Callback<Encomienda>() {
                @Override
                public void onResponse(Call<Encomienda> call, Response<Encomienda> response) {
                    // hideProgress();
                    try {
                        String  a=   "This is my message"+ response.isSuccessful() + " ";
                        Log.i("myTag", a);

                        encomiendaObj = response.body();
                        dni.setText(encomiendaObj.getDni() );
                        nombres.setText(encomiendaObj.getNombres());
                        apellidos.setText(encomiendaObj.getApellidos());
                        precio.setText(""+encomiendaObj.getPrecio());
                        encomiendaTv.setText(encomiendaObj.getEncomienda());
                        destino.setText(encomiendaObj.getDestino());
                      //  int spinnerPosition = adapterSpinner.getPosition(compareValue);
                        spinner.setSelection(encomiendaObj.getIdEstado() - 1);
                    //    Log.i("myTag", "tamaño "+ response.body().getListaEncomiendas().size());

                        System.out.println("Entro perfectamente");
                        //EncomiendaAdapter adapter = new EncomiendaAdapter(thiscontext,lstEncomienda);
                       // listaView.setAdapter(adapter);



                    }catch(Exception e){
                        System.out.println("Es error perfectamente");
                        Toast.makeText(getBaseContext(), "error al obtener data"+ e.getMessage(), Toast.LENGTH_LONG).show();
                    }

                }

                @Override
                public void onFailure(Call<Encomienda> call, Throwable t) {
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

    void editar(){

        String dnitxt = this.dni.getText().toString().trim();
        //Aqui mas de lo mimso
        String nombrestxt = nombres.getText().toString().trim();
        String apellidostxt = apellidos.getText().toString().trim();
        String preciotxt = precio.getText().toString().trim();
        String encomiendtxt = encomiendaTv.getText().toString().trim();
        String EstadoSelect = (String) spinner.getSelectedItem();
        String destinotxt = destino.getText().toString().trim();

        //       String name = etPassword.getText().toString().trim();
        //Aqui verificamos si el txt esta vacio
        if (dnitxt.isEmpty()) {
            //Aqui le manda un error  ps
            dni.setError("Ingrese el DNI");
            //Aca un reques focus ps para que se enconque ahi
            dni.requestFocus();
            //un return para que se termine
            return;
        }
        else if (nombrestxt.isEmpty()) {
            //Aqui le manda un error  ps
            nombres.setError("Ingrese los nombres");
            //Aca un reques focus ps para que se enconque ahi
            nombres.requestFocus();
            //un return para que se termine
            return;
        } else if (apellidostxt.isEmpty()) {
            //Aqui le manda un error  ps
            apellidos.setError("Ingrese los apellidos");
            //Aca un reques focus ps para que se enconque ahi
            apellidos.requestFocus();
            //un return para que se termine
            return;
        }
        else if (preciotxt.isEmpty()) {
            //Aqui le manda un error  ps
            precio.setError("Ingrese el precio");
            //Aca un reques focus ps para que se enconque ahi
            precio.requestFocus();
            //un return para que se termine
            return;
        }
        else if (encomiendtxt.isEmpty()) {
            //Aqui le manda un error  ps
            encomiendaTv.setError("Ingrese el nombre de la encomienda");
            //Aca un reques focus ps para que se enconque ahi
            encomiendaTv.requestFocus();
            //un return para que se termine
            return;
        }else if (destinotxt.isEmpty()) {
            //Aqui le manda un error  ps
            destino.setError("Ingrese el destino de la encomienda");
            //Aca un reques focus ps para que se enconque ahi
            destino.requestFocus();
            //un return para que se termine
            return;
        }

        Encomienda objEncomienda = new Encomienda();
        objEncomienda.setId(this.id);
        objEncomienda.setApellidos(apellidostxt);
        objEncomienda.setDni(dnitxt);
        objEncomienda.setEncomienda(encomiendtxt);
        objEncomienda.setNombres(nombrestxt);
        objEncomienda.setIdEstado(Estados.indexOf(EstadoSelect) + 1);
        double x =  Double.parseDouble(""+preciotxt);
        objEncomienda.setPrecio(x);
        objEncomienda.setDestino(destinotxt);
        // objEncomienda.setFechaRegistro(new Date());
        retrofit2.Call<Void> call =  RetrofitCliente.getInstance().getAPI().editarEncomienda(objEncomienda);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                // hideProgress();
                try {
                    String  a=   "This is my message"+ response.isSuccessful() + " ";
                    Log.i("myTag", ""+ response.code());
                    if(response.code() == 200){
                        Toast.makeText(getBaseContext(), "Se Edito correctamente", Toast.LENGTH_LONG).show();
                        startActivity(new Intent(thiscontext, EncomiendasHome.class));
                    }
                    else{
                        Toast.makeText(getBaseContext(), "error al editar, http error:"+ response.code(), Toast.LENGTH_LONG).show();
                    }



                }catch(Exception e){
                    System.out.println("Es error perfectamente");
                    Toast.makeText(getBaseContext(), "error al editar"+ e.getMessage(), Toast.LENGTH_LONG).show();
                }

            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                //hideProgress();
                System.out.println("Es error perfectamente"+t.getMessage());
                Toast.makeText(getBaseContext(), "error al obtener data abajo"+ t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });





    }
}