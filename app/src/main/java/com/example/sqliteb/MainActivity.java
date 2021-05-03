package com.example.sqliteb;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.sqliteb.adapter.TemanAdapter;
import com.example.sqliteb.database.DBController;
import com.example.sqliteb.database.Teman;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends Activity {
    RecyclerView recyclerview;
    TemanAdapter adaptertmn;
    ArrayList<Teman> temanArrayList;
    FloatingActionButton fabbtn;

    DBController controller = new DBController(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerview = findViewById(R.id.recyclerview);
        fabbtn = findViewById(R.id.FloatingActionButton);

        BacaData();
        adaptertmn = new TemanAdapter(temanArrayList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(MainActivity.this);
        recyclerview.setLayoutManager(layoutManager);
        recyclerview.setAdapter(adaptertmn);




        fabbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,temanbaru.class);
                startActivity(intent);
            }
        });
    }

    public void BacaData(){
        ArrayList<HashMap<String,String>> daftarTeman = controller.getAllTeman();
        temanArrayList = new ArrayList<>();

        for (int i=0;i<daftarTeman.size();i++){
            Teman teman = new Teman();


            teman.setId(daftarTeman.get(i).get("id").toString());
            teman.setNama(daftarTeman.get(i).get("nama").toString());
            teman.setTelepon(daftarTeman.get(i).get("telepon").toString());


            temanArrayList.add(teman);
        }
    }

}