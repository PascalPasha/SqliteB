package com.example.sqliteb;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.sqliteb.database.DBController;
import com.google.android.material.textfield.TextInputEditText;

import java.util.HashMap;

public class temanbaru extends Activity {
    TextInputEditText Nama, Telepon;
    Button Simpan;
    String nma,tlp;


    DBController controller = new DBController(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teman_baru);

        Nama = (TextInputEditText)findViewById(R.id.etNama);
        Telepon = (TextInputEditText)findViewById(R.id.etTelpon);
        Simpan = (Button)findViewById(R.id.buttonSave);

        Simpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Nama.getText().toString().isEmpty() || Telepon.getText().toString().isEmpty()){
                    Toast.makeText(getApplicationContext(),"Data belum Komplit !", Toast.LENGTH_SHORT).show();
                }else {
                    nma = Nama.getText().toString();
                    tlp = Telepon.getText().toString();

                    HashMap<String,String> ivalues = new HashMap<>();
                    ivalues.put("nama",nma);
                    ivalues.put("telpon",tlp);

                    controller.InsertData(ivalues);
                    callHome();
                }
            }
        });
    }

    public void callHome(){
        Intent intent = new Intent(temanbaru.this,MainActivity.class);
        startActivity(intent);
        finish();
    }

}