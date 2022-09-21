package com.example.activity5;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private Button newUser,btnEdit, btnDelete;
    private ListView lv1Users;
    ArrayList<Entidad> usersList = new ArrayList<Entidad>();
    private Adaptador adaptador;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.Theme_Activity5);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        newUser=(Button) findViewById(R.id.buttonNewUser);
        lv1Users=(ListView) findViewById(R.id.lvUsers);
        btnEdit=(Button) findViewById(R.id.buttonItemEdit);
        btnDelete =(Button)findViewById(R.id.buttonItemDelete);

        DatabaseConnect databaseConnect = new DatabaseConnect(this,"libros",null,1);
        SQLiteDatabase db = databaseConnect.getWritableDatabase();
        Cursor registrer = db.rawQuery("Select * from libros",null);
        if(registrer.moveToFirst()){
            do{
                usersList.add(new Entidad(registrer.getString(0)));//registrer.getString(0));
            }while (registrer.moveToNext());
            db.close();
            //ArrayAdapter<String> adapter= new ArrayAdapter(this,R.layout.lit_item_users,usersList);
            adaptador = new Adaptador(this,usersList);
            lv1Users.setAdapter(adaptador);
        }
        lv1Users.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(MainActivity.this,MainActivity3.class);
                intent.putExtra("titulo",usersList.get(i));
                startActivity(intent);
                Toast.makeText(MainActivity.this,usersList.get(i).getName(),Toast.LENGTH_LONG).show();
            }
        });
        newUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,MainActivity2.class);
                startActivity(intent);
            }
        });
    }
    public void editItem(View view){
        final int position = lv1Users.getPositionForView((View) view.getParent());
        Intent intent = new Intent(this, MainActivity3.class);
        intent.putExtra("titulo",usersList.get(position).getName());
        startActivity(intent);
        Toast.makeText(MainActivity.this,usersList.get(position).getName(),Toast.LENGTH_LONG).show();
    }
    public void deleteItem(View view){
        final int position = lv1Users.getPositionForView((View) view.getParent());
        final DatabaseConnect databaseConnect = new DatabaseConnect(this,"libros",null,1);
        SQLiteDatabase bd = databaseConnect.getWritableDatabase();
        AlertDialog.Builder alerta = new AlertDialog.Builder(MainActivity.this);
        alerta.setMessage("Seguro quieres eliminar el libro").setCancelable(false)
                .setPositiveButton("Si", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                       bd.delete("libros","titulo='" +usersList.get(position).getName()+ "'",null);
                        Toast.makeText(getApplicationContext(),"Se elimino libro",Toast.LENGTH_LONG).show();
                        bd.close();
                        startActivity(new Intent(MainActivity.this,MainActivity.class));
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                }).show();
    }
}