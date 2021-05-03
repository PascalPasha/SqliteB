package com.example.sqliteb.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupMenu;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sqliteb.MainActivity;
import com.example.sqliteb.R;
import com.example.sqliteb.database.DBController;
import com.example.sqliteb.database.Teman;
import com.example.sqliteb.edit_teman;

import java.util.ArrayList;
import java.util.HashMap;

public class TemanAdapter extends RecyclerView.Adapter<TemanAdapter.TemanViewHolder> {
    private ArrayList<Teman> listData;
    private Context control;

    public TemanAdapter(ArrayList<Teman> listData) {
        this.listData = listData;
    }

    public TemanAdapter (Context control, ArrayList<Teman> listData){

        this.listData = listData;
    }
    @Override
    public TemanAdapter.TemanViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInf = LayoutInflater.from(parent.getContext());
        View v = layoutInf.inflate(R.layout.row_data_teman,parent,false);
        control = parent.getContext();
        return new TemanViewHolder(v);
    }

    @Override
    public void onBindViewHolder(TemanAdapter.TemanViewHolder holder, int position) {
        String nma,tlp,id;

        id = listData.get(position).getId();
        nma = listData.get(position).getNama();
        tlp = listData.get(position).getTelepon();
        DBController db = new DBController(control);

        holder.namaTxtView.setText(nma);
        holder.namaTxtView.setTextSize(30);
        holder.teleponTxtView.setText(tlp);

        holder.card.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                PopupMenu popupMenu = new PopupMenu(control, holder.card);
                popupMenu.inflate(R.menu.popup_menu);
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()){
                            case R.id.mnEdit:
                                Intent i = new Intent(control, edit_teman.class);
                                i.putExtra("id",id);
                                i.putExtra("nama",nma);
                                i.putExtra("telpon",tlp);
                                control.startActivity(i);
                                break;
                            case R.id.mnHapus:
                                HashMap<String,String > values = new HashMap<>();
                                values.put("id",id);
                                db.DeleteData(values);
                                Intent j = new Intent(control, MainActivity.class);
                                control.startActivity(j);
                                break;
                        }
                        return true;
                    }
                });
                popupMenu.show();
                return false;
            }
        });
    }

    @Override
    public int getItemCount() {
        return (listData != null)?listData.size() : 0;
    }





    public class TemanViewHolder extends RecyclerView.ViewHolder {
        private CardView card;
        private TextView namaTxtView, teleponTxtView;

        public TemanViewHolder(View view) {
            super(view);
            card = (CardView) view.findViewById(R.id.kartu);
            namaTxtView = (TextView) view.findViewById(R.id.textNama);
            teleponTxtView = (TextView) view.findViewById(R.id.textTelepon);

        }
    }
}