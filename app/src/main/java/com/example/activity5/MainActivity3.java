package com.example.activity5;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EdgeEffect;
import android.widget.EditText;
import android.widget.ImageButton;

public class MainActivity3 extends AppCompatActivity {
private EditText titulo, autor, editorial, año;
private Button editar;
private ImageButton imageButton;
    Entidad item;
    private Bitmap bitmap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        titulo = (EditText) findViewById(R.id.editTextTituloE);
        autor = (EditText) findViewById(R.id.editTextAutorE);
        editorial = (EditText) findViewById(R.id.editTextEditorialE);
        año = (EditText) findViewById(R.id.editTextAñoE);
        editar = (Button) findViewById(R.id.buttonEditE);
        imageButton=(ImageButton) findViewById(R.id.imageButton1E);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                intent.setType("image/");
                startActivityForResult(intent.createChooser(intent,"Selecciona la app"),10);
            }
        });
        editar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseConnect databaseConnect = new DatabaseConnect(MainActivity3.this, "libros", null, 1);
                SQLiteDatabase db = databaseConnect.getWritableDatabase();
                ContentValues registro = new ContentValues();
                registro.put("titulo",titulo.getText().toString());
                registro.put("autor",autor.getText().toString());
                registro.put("editorial",editorial.getText().toString());
                registro.put("year",año.getText().toString());
                //item = (Entidad) getIntent().getSerializableExtra("nombre");
                db.update("libros", registro, "titulo='" + getIntent().getStringExtra("titulo") + "'", null);
                System.out.println("**********" + item.getName());
                db.close();
                Intent intent = new Intent(MainActivity3.this, MainActivity.class);
                finish();
                startActivity(intent);
            }
        });
        }
    protected  void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode,resultCode,data);
        if(resultCode==RESULT_OK){
            Uri path = data.getData();
            imageButton.setImageURI(path);
            bitmap = imageButton.getDrawingCache();
        }
    }
}