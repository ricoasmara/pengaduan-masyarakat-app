package com.example.pengaduanmasyakarat;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.pengaduanmasyakarat.MenuWarga.InputPengaduan;
import com.example.pengaduanmasyakarat.adapter.LaporanwargaAdapater;
import com.example.pengaduanmasyakarat.model.Datauser;
import com.example.pengaduanmasyakarat.model.laporanuser;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class LaporanWarga extends AppCompatActivity {
private RecyclerView recyclerView;
private FirebaseFirestore db= FirebaseFirestore.getInstance();
private List<laporanuser> listlaporan = new ArrayList<>();


private LaporanwargaAdapater laporanwargaAdapater;
private ProgressDialog progressDialog,hapus;
private FloatingActionButton fabadd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_laporan_warga);
     recyclerView= findViewById(R.id.Recyclewarga);

     progressDialog= new ProgressDialog(LaporanWarga.this);
     progressDialog.setTitle("Loading");
     progressDialog.setMessage("Mengambil Data");
     hapus= new ProgressDialog(LaporanWarga.this);
     hapus.setTitle("Loading");
        hapus.setMessage("Menghapus Data");

     laporanwargaAdapater= new LaporanwargaAdapater(getApplicationContext(),listlaporan);
     laporanwargaAdapater.setDialog(new LaporanwargaAdapater.Dialog() {
       //Edit dan hapus
         @Override
         public void onClick(int pos) {
             final CharSequence[] dialogItem ={"Edit","Hapus"};
             AlertDialog.Builder dialog= new AlertDialog.Builder(LaporanWarga.this);
             dialog.setItems(dialogItem, new DialogInterface.OnClickListener() {
                 @Override
                 public void onClick(DialogInterface dialogInterface, int i) {
                    switch (i){
                        case 0:
                            Intent intent= new Intent(getApplicationContext(), InputPengaduan.class);
                            intent.putExtra("id",listlaporan.get(pos).getId());
                            intent.putExtra("Nama",listlaporan.get(pos).getNama());
                            intent.putExtra("Tanggal Pengaduan",listlaporan.get(pos).getTgl());
                            intent.putExtra("Jam Pengaduan",listlaporan.get(pos).getJam());
                            intent.putExtra("Laporan",listlaporan.get(pos).getLaporan());
                            intent.putExtra("Solusi",listlaporan.get(pos).getSolusi());
                            startActivity(intent);
                            break;
                        case 1:
                            deletelaporan(listlaporan.get(pos).getId());
                            break;
                    }
                 }
             });
             dialog.show();
         }
     });

     RecyclerView.LayoutManager layoutManager= new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.VERTICAL,false);
     RecyclerView.ItemDecoration decoration= new DividerItemDecoration(getApplicationContext(),DividerItemDecoration.VERTICAL);
     recyclerView.setLayoutManager(layoutManager);
     recyclerView.addItemDecoration(decoration);
     recyclerView.setAdapter(laporanwargaAdapater);

    }

    @Override
    protected void onStart() {
        super.onStart();
        getLaporan();
    }

    private void getLaporan(){
        //RecycleView laporan
        progressDialog.show();
        db.collection("Laporan Masyarakat").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                listlaporan.clear();
                if (task.isSuccessful()){
                    for (QueryDocumentSnapshot document: task.getResult()){
                        laporanuser laporan = new laporanuser(document.getString("Nama"),document.getString("Tanggal Pengaduan"),document.getString("Jam Pengaduan"),document.getString("Laporan"),document.getString("Solusi"));
                        laporan.setId(document.getId());
                        listlaporan.add(laporan);

                    }
                    laporanwargaAdapater.notifyDataSetChanged();
                }else {
                    Toast.makeText(getApplicationContext(),"Data gagal diambil", Toast.LENGTH_SHORT).show();
                }
                progressDialog.dismiss();
            }
        });
    }
    //deletelaporan
    private void deletelaporan(String id){
        hapus.show();
        db.collection("Laporan Masyarakat").document(id).delete().addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(!task.isSuccessful()){
                    Toast.makeText(LaporanWarga.this, "Data Gagal dihapus", Toast.LENGTH_SHORT).show();
                }
                hapus.dismiss();
                getLaporan();
            }
        });

    }
}
