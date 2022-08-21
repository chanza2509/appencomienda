package com.example.appencomienda.actividades;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.appencomienda.R;
import com.example.appencomienda.modelo.Encomienda;
import com.example.appencomienda.modelo.EnvioDatos;
import com.example.appencomienda.modelo.RespuestaLogin;
import com.example.appencomienda.retrofit.RetrofitCliente;

import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegistroEncomiendaActivity extends AppCompatActivity {
    TextView dni, nombres, apellidos, precio, encomiendaTv,destino;
    Context thiscontext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_encomienda);

        this.dni = findViewById(R.id.regDni);
        this.nombres = findViewById(R.id.regNombres);
        this.apellidos = findViewById(R.id.regApellidos);
        this.precio = findViewById(R.id.regPrecio);
        this.encomiendaTv = findViewById(R.id.regEncomienda);
        this.destino = findViewById(R.id.regDestino);
        this.thiscontext = this;

        findViewById(R.id.btnRegEncomienda).setOnClickListener(View -> registrar());
        findViewById(R.id.reg_volver).setOnClickListener(View -> volver());
    }
    public void volver( ){
        Intent i = new Intent(this, Menu.class);
        startActivity(i);
    }


public void registrar(){
    //Aqui se jala sus datos de eso EditText es como txtps tienes los mismos metodos y haces el mismo chiste
    String dnitxt = this.dni.getText().toString().trim();
    //Aqui mas de lo mimso
    String nombrestxt = nombres.getText().toString().trim();
    String apellidostxt = apellidos.getText().toString().trim();
    String preciotxt = precio.getText().toString().trim();
    String encomiendtxt = encomiendaTv.getText().toString().trim();
    String destinotxt = this.destino.getText().toString().trim();
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
    }
    else if (destinotxt.isEmpty()) {
        //Aqui le manda un error  ps
        encomiendaTv.setError("Ingrese el destino");
        //Aca un reques focus ps para que se enconque ahi
        encomiendaTv.requestFocus();
        //un return para que se termine
        return;
    }
    Encomienda objEncomienda = new Encomienda();
    objEncomienda.setApellidos(apellidostxt);
    objEncomienda.setDni(dnitxt);
    objEncomienda.setEncomienda(encomiendtxt);
    objEncomienda.setNombres(nombrestxt);
    objEncomienda.setDestino(destinotxt);
    objEncomienda.setIdEstado(1);
    double x =  Double.parseDouble(""+preciotxt);
    objEncomienda.setPrecio(x);
   // objEncomienda.setFechaRegistro(new Date());
    retrofit2.Call<Void> call =  RetrofitCliente.getInstance().getAPI().registroEncomienda(objEncomienda);
    call.enqueue(new Callback<Void>() {
        @Override
        public void onResponse(Call<Void> call, Response<Void> response) {
            // hideProgress();
            try {
                String  a=   "This is my message"+ response.isSuccessful() + " ";
                Log.i("myTag", ""+ response.code());
                if(response.code() == 200){
                    Toast.makeText(getBaseContext(), "Se registro correctamente", Toast.LENGTH_LONG).show();
                    startActivity(new Intent(thiscontext, Menu.class));
                }
                else{
                    Toast.makeText(getBaseContext(), "error al obtener data, http error:"+ response.code(), Toast.LENGTH_LONG).show();
                }



            }catch(Exception e){
                System.out.println("Es error perfectamente");
                Toast.makeText(getBaseContext(), "error al obtener data"+ e.getMessage(), Toast.LENGTH_LONG).show();
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