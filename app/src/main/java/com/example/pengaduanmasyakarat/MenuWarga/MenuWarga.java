package com.example.pengaduanmasyakarat.MenuWarga;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.pengaduanmasyakarat.LaporanWarga;
import com.example.pengaduanmasyakarat.R;
import com.example.pengaduanmasyakarat.RiwayatLaporan;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

public class MenuWarga extends AppCompatActivity {
TextView MenuPengaduan,MenuLaporan,datas;
private FirebaseFirestore db=FirebaseFirestore.getInstance();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_warga);



        MenuPengaduan=findViewById(R.id.menupengaduan);
        MenuLaporan=findViewById(R.id.menulaporan);
        datas=findViewById(R.id.data);

        MenuPengaduan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), InputPengaduan.class));
            }
        });
        MenuLaporan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), RiwayatLaporan.class));
            }
        });



    }
}