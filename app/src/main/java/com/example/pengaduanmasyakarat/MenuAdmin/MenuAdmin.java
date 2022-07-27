package com.example.pengaduanmasyakarat.MenuAdmin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.pengaduanmasyakarat.LaporanWarga;
import com.example.pengaduanmasyakarat.R;

public class MenuAdmin extends AppCompatActivity {
TextView datawarga,laporanwarga,rekaplaporan;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_admin);
        datawarga=findViewById(R.id.menudatawarga);
        laporanwarga=findViewById(R.id.menulaporanwarga);
        rekaplaporan=findViewById(R.id.rekaplaporanwarga);


        datawarga.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MenuAdmin.this, Datawarga.class));
            }
        });
        laporanwarga.setOnClickListener(view -> startActivity(new Intent(MenuAdmin.this, LaporanWarga.class)));
    }
}