package com.example.appencomienda.actividades;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.appencomienda.R;
import com.example.appencomienda.baseDatos.ConexionSQLiteHelper;
import com.example.appencomienda.modelo.RespuestaLogin;
import com.example.appencomienda.modelo.Usuario;
import com.example.appencomienda.retrofit.RetrofitCliente;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainActivity extends AppCompatActivity {
    EditText username, pass;
    Context thisContext;
    //DaoUsuario dao;
    public void onlogin(View view){


        Toast.makeText(MainActivity.this, "Se registro Correctamente", Toast.LENGTH_LONG).show();

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
     //   R.layout.activity_login;
        thisContext = this;
        setContentView(R.layout.activity_main);
    //    islogin();
        username = findViewById(R.id.usernameLoginText);
        pass = findViewById(R.id.passLoginText);
        findViewById(R.id.loginBtn).setOnClickListener(View -> logear());
        findViewById(R.id.loginVolver).setOnClickListener(View -> volver());
       // dao = new DaoUsuario(this);
    }

    public void volver( ){
        Intent i = new Intent(this, MenuInicio.class);
        startActivity(i);
    }


    void buscar(){
        startActivity(new Intent(this, BuscarActivity.class));
    }

    public void logear() {

        //Aqui se jala sus datos de eso EditText es como txtps tienes los mismos metodos y haces el mismo chiste
        String userName = username.getText().toString().trim();
        //Aqui mas de lo mimso
        String password = pass.getText().toString().trim();
        //       String name = etPassword.getText().toString().trim();
        //Aqui verificamos si el txt esta vacio
        if (userName.isEmpty()) {
            //Aqui le manda un error  ps
            username.setError("Usuario requerido");
            //Aca un reques focus ps para que se enconque ahi
            username.requestFocus();
            //un return para que se termine
            return;
        } else if (password.isEmpty()) { /// y si la contra hace lo mismo tmb
            pass.setError("Password requerida");
            pass.requestFocus();
            return;
        }
        //Aqui si viene hacemos un llamdo call a nuestro
        retrofit2.Call<RespuestaLogin> call = RetrofitCliente
                .getInstance() //aQUI SE LLAMA A LA INSTANCIA QUE Creamos y lo instancaimos
                .getAPI() //aui le pedimos que nos pase el objeto Api que ha sido creado junto con ahi
                .checkUser(new Usuario(userName, password)); //AQUI CHEquea y puedes ir a ver a RetroFitCliente
        //Y chuequear todo esa función
        //eSTO CREO ESTA EN CADENA
        call.enqueue(new Callback<RespuestaLogin>() {
            @Override
            public void onResponse(Call<RespuestaLogin> call, Response<RespuestaLogin> response) {
                String s = "", jwt = "";
                try {
                    //Aqui nosotros vamos a capturar ps lo que devuelve los datos que creo siempre devuelve
                    System.out.println("code: " + response.code());
                    if(response.body().isEstado ()==true){
                        Toast.makeText(MainActivity.this, "El usuario se logeo con exito !", Toast.LENGTH_LONG).show();



//                        jwt= response.body().getToken();


                       ConexionSQLiteHelper admin = new ConexionSQLiteHelper(thisContext, "administracion", null, 1);
                        SQLiteDatabase BaseDeDatos = admin.getWritableDatabase();
                        ContentValues registro = new ContentValues();
                        registro.put("loginx", "1");

                       BaseDeDatos.insert("login", null, registro);

                        BaseDeDatos.close();
                        startActivity(new Intent(MainActivity.this, Menu.class));

                     //   System.out.println("El jwt de logeo nativo: " + jwt);
                    } else{

                   //     s= response.body().getToken();
                        Toast.makeText(MainActivity.this, "Credenciales incorrectas, intente otra vez!", Toast.LENGTH_LONG).show();

                    }



                } catch (Exception e) {
                    //aQUI EL ERROR QUE LANZA
                    System.out.println("El error are :"+e.getMessage());
                    e.printStackTrace();
                }
                //Aqui esta comparando ps si existe y en caso que no sea asi no entr


            }

            @Override
            public void onFailure(Call<RespuestaLogin> call, Throwable t) {
                Toast.makeText(MainActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });

    }



}