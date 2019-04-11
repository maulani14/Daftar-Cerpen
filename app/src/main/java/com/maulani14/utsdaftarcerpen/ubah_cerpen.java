package com.maulani14.utsdaftarcerpen;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.maulani14.utsdaftarcerpen.Model.Database;

public class ubah_cerpen extends AppCompatActivity {

    EditText tfJudul,tfSumber,tfIsi;
    Button btnSimpan;
    Database db;
    Cursor cursor;
    String judul;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ubah_cerpen);

        Intent intent = getIntent();
        judul = intent.getStringExtra("judul");
        String sumber = intent.getStringExtra("sumber");
        String isi = intent.getStringExtra("isi");

        tfJudul = (EditText) findViewById(R.id.tfJudulUbah);
        tfSumber = (EditText) findViewById(R.id.tfSumberUbah);
        tfIsi= (EditText) findViewById(R.id.tfIsiUbah);
        btnSimpan = (Button) findViewById(R.id.btnUbahCerpen);

        tfJudul.setText(judul);
        tfSumber.setText(sumber);
        tfIsi.setText(isi);
        db = new Database(this);

        btnSimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase dbnya = db.getWritableDatabase();
                dbnya.execSQL("update note set judul='"+ tfJudul.getText() +"', sumber='"+ tfSumber.getText() +"', isi='"+ tfIsi.getText() +"' where judul='"+ judul +"'");
                finish();
                Toast.makeText(ubah_cerpen.this,"OK",Toast.LENGTH_LONG).show();
            }
        });
    }
}
