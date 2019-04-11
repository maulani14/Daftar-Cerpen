package com.maulani14.utsdaftarcerpen;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.maulani14.utsdaftarcerpen.Model.Database;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class cerpen extends AppCompatActivity {
    RecyclerView recyclerView;
    NoteAdapter adapter;
    ArrayList<Note> note;
    Database db;
    Cursor cursor;
    TextView tvNote;
    Button btnTambah,btnHapus;
    RecyclerView.LayoutManager layoutManager;
    private static final String LOG_TAG = MainActivity.class.getSimpleName ();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cerpen);

        db = new Database(this);

        SharedPreferences spPengguna = cerpen.this.getSharedPreferences("Userlogin", Context.MODE_PRIVATE);
        String namaSedangLogin = spPengguna.getString("sedangLogin", "Defaultnya Manusia");

        TextView textUser = (TextView) findViewById(R.id.tfUserLogin);
        //TextView rvNote = (TextView) findViewById(R.id.rvNote);

        textUser.setText("Anda Login Sebagai : " + namaSedangLogin);

        recyclerView = (RecyclerView) findViewById(R.id.rvNote);
        tvNote = (TextView) findViewById(R.id.tvNote);
        btnTambah = (Button) findViewById(R.id.btnTambah);
        btnHapus= (Button) findViewById(R.id.btnHapus);

        btnTambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(cerpen.this, tambah_cerpen.class);
                startActivity(intent);
                //finish();
            }
        });

        btnHapus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase dbsql = db.getReadableDatabase();
                String sql = "delete from note";
                dbsql.execSQL(sql);
                addDataDummy();
            }
        });

        addDataDummy();
    }

    @Override
    protected void onResume() {
        super.onResume();
        addDataDummy();
    }

    private void addDataDummy() {
        note = new ArrayList<>();
        SQLiteDatabase dbsql = db.getReadableDatabase();
        cursor = dbsql.rawQuery("SELECT * FROM note",null);
        cursor.moveToFirst();

        if(cursor.getCount() > 0){
            do {

                note.add(new Note(cursor.getString(cursor.getColumnIndex("judul")),cursor.getString(cursor.getColumnIndex("isi")),cursor.getString(cursor.getColumnIndex("sumber"))));

            } while (cursor.moveToNext());
        }

        adapter = new NoteAdapter(note);

        layoutManager = new LinearLayoutManager(cerpen.this);

        recyclerView.setAdapter(adapter);

        recyclerView.setLayoutManager(layoutManager);

        if(note.size() > 0){
            this.tvNote.setText("Jumlah Cerpen : " + note.size());
        }else{
            adapter.notifyDataSetChanged();
            this.tvNote.setText("Tidak ada Cerpen untuk ditampilkan. : ");
        }

    }

    public void clickLogout ( View view ) {
        Log.d(LOG_TAG, "Logout");
        startActivity(new Intent(this,MainActivity.class)
                .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK));
        finish();
    }
}
