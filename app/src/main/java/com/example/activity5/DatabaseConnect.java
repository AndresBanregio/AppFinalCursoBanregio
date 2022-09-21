package com.example.activity5;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;


public class DatabaseConnect extends SQLiteOpenHelper {
    private static final String TableLibros = "create table libros(titulo text, autor text,editorial text, year text)";
    public DatabaseConnect(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(TableLibros);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
    }
public void agregarUsuario(String titulo, String autor, String editorial, String año){
SQLiteDatabase bd = getWritableDatabase();
if(bd!=null){
    bd.execSQL("Insert into libros values(`"+titulo+"`,`"+autor+"`,`"+editorial+"`,`\"+año+\"`)");
    bd.close();
}
}
}
