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

public class tambah_cerpen extends AppCompatActivity {

    EditText tfJudul,tfSumber,tfIsi;
    Button btnSimpan;
    Database db;
   // Cursor cursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah_cerpen);

        db = new Database(this);

        tfJudul = (EditText) findViewById(R.id.tfJudulTambah);
        tfSumber = (EditText) findViewById(R.id.tfSumberTambah);
        tfIsi= (EditText) findViewById(R.id.tfIsiTambah);
        btnSimpan = (Button) findViewById(R.id.btnTambahCerpen);

        btnSimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SQLiteDatabase dbnya = db.getWritableDatabase();

//                cursor = dbnya.rawQuery("select * from note",null);
//                int nomor = cursor.getCount();
                dbnya.execSQL("insert into note(judul, sumber, isi) values('" + tfJudul.getText().toString() + "','" + tfSumber.getText().toString() + "','" + tfIsi.getText().toString() + "')");

                Toast.makeText(getApplicationContext(), "Berhasil Menambah Cerpen", Toast.LENGTH_LONG).show();

                Intent intent = new Intent(tambah_cerpen.this, cerpen.class);
                startActivity(intent);
                finish();
            }
        });


    }
}
