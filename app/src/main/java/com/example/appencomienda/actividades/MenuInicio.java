package com.example.appencomienda.actividades;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import com.example.appencomienda.R;
import com.example.appencomienda.baseDatos.ConexionSQLiteHelper;

public class MenuInicio extends AppCompatActivity {
    private enum BTNevent {
        LOGIN, RASTREAR;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_menu_inicio);
        islogin();
        findViewById(R.id.miLoginAdmi).setOnClickListener(View -> buttonEvent(BTNevent.LOGIN));
        findViewById(R.id.miRastrear).setOnClickListener(View -> buttonEvent(BTNevent.RASTREAR));

    }
    void islogin(){
        ConexionSQLiteHelper admin = new ConexionSQLiteHelper(this, "administracion", null, 1);
        SQLiteDatabase BaseDeDatos = admin.getWritableDatabase();
        Cursor fila = BaseDeDatos.rawQuery("select loginx from login", null);
        //startActivity(new Intent(this, Menu.class));
        System.out.println("El tamaño de la fila es: " + fila.getCount());
        if(fila.getCount() > 0){
            startActivity(new Intent(this, Menu.class));

        }
    }

    private void buttonEvent(BTNevent btnEvent){
        Intent i = null;
        switch (btnEvent){
            case LOGIN:
                i = new Intent(this, MainActivity.class); break;
            case RASTREAR:
                i = new Intent(this, BuscarActivity.class); break;

        }
        startActivity(i);
    }
}