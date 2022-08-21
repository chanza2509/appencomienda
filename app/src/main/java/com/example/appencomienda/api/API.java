package com.example.appencomienda.api;

import com.example.appencomienda.modelo.BaseResponse;
import com.example.appencomienda.modelo.Encomienda;
import com.example.appencomienda.modelo.EnvioDatos;
import com.example.appencomienda.modelo.RespuestaLogin;
import com.example.appencomienda.modelo.Usuario;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface API {
    /*
    @POST("api/sensor/getDatosPorFecha")
        // @HTTP(method = "GET", path = "api/sensor/getDatosPorFecha", hasBody = true)
    Call<BaseResponse> getDatosPorFecha(
            @Body BaseResponse sensorTemp
    );
*/

    @GET("/api/encomienda/listar")
    Call<EnvioDatos> getEncomiendas( );

    @POST("api/usuario/login")
    Call<RespuestaLogin> checkUser (
            @Body Usuario user
    );
    @POST("api/encomienda/reportebydia")
    Call<EnvioDatos> getByFechaEncomienda (
            @Body EnvioDatos envio
    );
    @POST("api/encomienda/listarbydni")
    Call<EnvioDatos> getByDni(
            @Body EnvioDatos envio
    );
    @POST("api/encomienda/registro")
    Call<Void> registroEncomienda (
            @Body Encomienda envio
    );
        @POST("api/encomienda/editar")
    Call<Void> editarEncomienda (
            @Body Encomienda envio
    );
    @GET("/api/encomienda/listar/{id}")
    Call<Encomienda> getEncomiedabyId(@Path("id")  int id);
    /*
    @GET("/channels/1376563/feeds.json?api_key=MJ0KEQB7ZTU4BTFS")
    Call<BaseResponse> getDatosQuinto(
    );
    */


}
