package com.maulani14.utsdaftarcerpen;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.maulani14.utsdaftarcerpen.Model.Database;

public class detailNote extends AppCompatActivity {
    String id;
    Database db;
    String judul,sumber,isi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_note);

        Intent intent = getIntent();
        id = intent.getStringExtra("id");
        judul = intent.getStringExtra("judul");
        sumber = intent.getStringExtra("sumber");
        isi = intent.getStringExtra("isi");

        TextView tfJudul = (TextView) findViewById(R.id.tvJudul);
        TextView tfSumber= (TextView) findViewById(R.id.tvSumber);
        TextView tfIsi = (TextView) findViewById(R.id.tvIsi);
        Button btnUbah = (Button) findViewById(R.id.btnUbah);
        Button btnHapus = (Button) findViewById(R.id.btnHapus);

        tfJudul.setText("Judul : " + judul);
        tfSumber.setText("Sumber :" +  sumber);
        tfIsi.setText("Isi : " + isi);

        db = new Database(this);
        //put untuk mengirimkan data
        btnUbah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = detailNote.this;
                Intent i = new Intent(context, ubah_cerpen.class);
                i.putExtra("judul", judul);
                i.putExtra("sumber", sumber);
                i.putExtra("isi", isi);
                context.startActivity(i);
                finish();
//                Toast.makeText(detailNote.this, "Id nya : " + id, Toast.LENGTH_SHORT).show();
            }
        });

        btnHapus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase dbsql = db.getReadableDatabase();
                dbsql.delete("note","judul = '" + judul + "'",null);
                Toast.makeText(detailNote.this,"Berhasil dihapus!", Toast.LENGTH_LONG).show();
                finish();
            }
        });


    }
}
