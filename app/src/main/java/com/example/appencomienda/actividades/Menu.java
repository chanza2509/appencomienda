package com.example.appencomienda.actividades;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import com.example.appencomienda.R;
import com.example.appencomienda.baseDatos.ConexionSQLiteHelper;

public class Menu extends AppCompatActivity {
    private enum BTNevent {
        INFORMES, REG_ENCOMIENDA, ENCOMIENDAS,BUSCAR;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        findViewById(R.id.BTNmenuEncomiendas).setOnClickListener(View -> buttonEvent(BTNevent.ENCOMIENDAS));
        findViewById(R.id.BTNmenuInforme).setOnClickListener(View -> buttonEvent(BTNevent.INFORMES));
        findViewById(R.id.BTNmenuRegEncomienda).setOnClickListener(View -> buttonEvent(BTNevent.REG_ENCOMIENDA));
        findViewById(R.id.BTNmenuOutLogin).setOnClickListener(View -> outLogin());
        findViewById(R.id.BTNmenuBuscar).setOnClickListener(View -> buttonEvent(BTNevent.BUSCAR));



    }
    void outLogin() {
        ConexionSQLiteHelper admin = new ConexionSQLiteHelper(this, "administracion", null, 1);
        SQLiteDatabase BaseDeDatos = admin.getWritableDatabase();
        int muertos =BaseDeDatos.delete("login","", null);
        startActivity(new Intent(Menu.this, MenuInicio.class));
    }

    private void buttonEvent(BTNevent btnEvent){
        Intent i = null;
        switch (btnEvent){
            case INFORMES:
                i = new Intent(Menu.this, Informes.class); break;
            case ENCOMIENDAS:
                i = new Intent(Menu.this, EncomiendasHome.class); break;
            case REG_ENCOMIENDA:
                i = new Intent(Menu.this, RegistroEncomiendaActivity.class); break;
            case BUSCAR:
                i = new Intent(Menu.this, BuscarAdmiActitvity.class); break;
        }
        startActivity(i);
    }
}