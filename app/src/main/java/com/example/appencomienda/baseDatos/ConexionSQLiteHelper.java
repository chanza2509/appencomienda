package com.example.appencomienda.baseDatos;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class ConexionSQLiteHelper extends SQLiteOpenHelper {

    final String TABLA_TOKEN= "Create table IF NOT EXISTS login (id INTEGER primary key autoincrement, loginx text)";

    public ConexionSQLiteHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
            db.execSQL(TABLA_TOKEN);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int versionOld, int versionNueva) {

    }
}
