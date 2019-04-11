package com.maulani14.utsdaftarcerpen;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.maulani14.utsdaftarcerpen.Model.Database;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    public List<User> pengguna;
    public EditText tfNama,tfPassword;
    public Button btnMasuk;
   // Database db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pengguna = new ArrayList<>();
        pengguna.add(new User("anis", "12345"));

        //nama disesuaikan dengan nama id yang ada di activity main
        tfNama = (EditText) findViewById(R.id.tfNama);
        tfPassword = (EditText) findViewById(R.id.tfPassword);
        btnMasuk = (Button) findViewById(R.id.btnMasuk);

        btnMasuk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //untuk mengecek apakah data yang di masukan user sama dengan data yang ada pada pengguna
                //jika iya maka data akan disimpan pada variabel tmpPengguna.
                User tmpPengguna = null;
                for(User u:pengguna){
                    if(u.getNama().equals(tfNama.getText().toString()) && u.getPassword().equals(tfPassword.getText().toString())){
                        tmpPengguna = u;
                    }
                }
                //untuk menyimpan data user yang ada pada tmpPenguna untuk ditampilkan namanya pada activity cerpen
                if(tmpPengguna != null){
                    SharedPreferences spPengguna = MainActivity.this.getSharedPreferences("Userlogin", Context.MODE_PRIVATE);
                    SharedPreferences.Editor edit = spPengguna.edit();
                    edit.putString("sedangLogin",tmpPengguna.getNama());
                    edit.apply();

                    Intent intent = new Intent(MainActivity.this, cerpen.class);
                    startActivity(intent);
                    finish();
                }else{
                    Toast.makeText(MainActivity.this, "Usernam atau Password Salah", Toast.LENGTH_LONG).show();
                }
            }
        });

       // db = new Database(this);

    }
}
