package com.example.pengaduanmasyakarat;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.Toast;

import com.example.pengaduanmasyakarat.adapter.LaporanwargaAdapater;
import com.example.pengaduanmasyakarat.model.laporanuser;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class RiwayatLaporan extends AppCompatActivity {
private RecyclerView recyclerView;
private FirebaseFirestore db=FirebaseFirestore.getInstance();
private List<laporanuser>list= new ArrayList<>();
private LaporanwargaAdapater laporanwargaAdapater;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_riwayat_laporan);

        recyclerView=findViewById(R.id.Recycleriwayat);
        laporanwargaAdapater = new LaporanwargaAdapater(getApplicationContext(),list);

        RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.VERTICAL,false);
        RecyclerView.ItemDecoration decoration = new DividerItemDecoration(getApplicationContext(),DividerItemDecoration.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addItemDecoration(decoration);
        recyclerView.setAdapter(laporanwargaAdapater);

        db.collection("Laporan Masyarakat").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
            if (task.isSuccessful()){
                for (QueryDocumentSnapshot document: task.getResult()){
                    laporanuser laporan = new laporanuser(document.getString("Nama"),document.getString("Tanggal Pengaduan"),document.getString("Jam Pengaduan"),document.getString("Laporan"),document.getString("Solusi"));
                    laporan.setId(document.getId());
                    list.add(laporan);
                }
                 laporanwargaAdapater.notifyDataSetChanged();
            }else{
                Toast.makeText(RiwayatLaporan.this, "data error", Toast.LENGTH_SHORT).show();
            }
            }
        });
    }
}