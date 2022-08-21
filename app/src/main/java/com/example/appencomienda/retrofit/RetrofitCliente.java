package com.example.appencomienda.retrofit;


import com.example.appencomienda.api.API;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitCliente {
    //192.168.100.168 ales
    //http://192.168.100.187:8080/
    //https://adamencomiendas2.herokuapp.com
    private static  final String BASE_URL = "https://adamencomiendas2.herokuapp.com";
    private static RetrofitCliente mInstance;

    private Retrofit retrofit;

    private RetrofitCliente() {
        //Aqui vamos a instanciar y dfe paso setear valores
        retrofit = new Retrofit.Builder()//consuuctor
                .baseUrl(BASE_URL)//aqui la url del servidor
                .addConverterFactory(GsonConverterFactory.create()) //Aqui si no que hara me falta teoria pero creo es el convertidor a json
                .build(); // aqui construir ps que mas
                
    }
    public static synchronized RetrofitCliente getInstance() {
        //Aca se verifica si ha sido creado ti´po en java normal para verficar si esta o no
        if (mInstance == null) { //caso contrario se crera
            mInstance = new RetrofitCliente(); //y aqui se met su vaina
        }
        return mInstance; //Se devuelve ps a quien quiera por ejemplo para que usen y siempre se dara lo mismo
        //y asi se evita estar creando a cada rato classic
    }
    public API getAPI () {
        return retrofit.create(API.class);
    }
}
