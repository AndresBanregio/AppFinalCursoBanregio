package com.example.activity5;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class MainActivity2 extends AppCompatActivity {
    private EditText titulo, autor, editorial, año;
    private Button registrar;
    private Bitmap bitmap;
    private ImageButton imageButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        titulo=(EditText) findViewById(R.id.editTextTituloE);
        autor=(EditText) findViewById(R.id.editTextAutorE);
        editorial=(EditText) findViewById(R.id.editTextEditorialE);
        año=(EditText) findViewById(R.id.editTextAñoE);
        registrar=(Button) findViewById(R.id.buttonEditE);
    }
    public void regitrarUsuario(View view){
        final DatabaseConnect databaseConnect = new DatabaseConnect(this,"libros",null,1);
        SQLiteDatabase bd = databaseConnect.getWritableDatabase();
        ContentValues registro = new ContentValues();
        registro.put("titulo",titulo.getText().toString());
        registro.put("autor",autor.getText().toString());
        registro.put("editorial",editorial.getText().toString());
        registro.put("year",año.getText().toString());
        bd.insert("libros",null,registro);
        Toast.makeText(getApplicationContext(),"Se agrego nuevo usuario",Toast.LENGTH_LONG).show();
        finish();
        startActivity(new Intent(this,MainActivity.class));
    }
}