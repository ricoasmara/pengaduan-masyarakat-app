package com.example.pengaduanmasyakarat.MenuAdmin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.widget.Toast;

import com.example.pengaduanmasyakarat.R;
import com.example.pengaduanmasyakarat.adapter.DataAdapter;
import com.example.pengaduanmasyakarat.model.Datauser;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class Datawarga extends AppCompatActivity {
private RecyclerView recyclerView;
private FirebaseFirestore db = FirebaseFirestore.getInstance();
private List<Datauser>list = new ArrayList<>();
private DataAdapter dataAdapter;
private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_datawarga);
        recyclerView=findViewById(R.id.RDatawarga);
        progressDialog=new ProgressDialog(Datawarga.this);
        progressDialog.setTitle("Loading...");
        progressDialog.setMessage("Mengambil Data");

        dataAdapter= new DataAdapter(getApplicationContext(),list);

        RecyclerView.LayoutManager layoutManager= new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL,false);
        RecyclerView.ItemDecoration decoration = new DividerItemDecoration(getApplicationContext(),DividerItemDecoration.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addItemDecoration(decoration);
        recyclerView.setAdapter(dataAdapter);
        //mengambil Data
        progressDialog.show();
        db.collection("Data Warga")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @SuppressLint("NotifyDataSetChanged")
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                    list.clear();
                        if (task.isSuccessful()){
                        for (QueryDocumentSnapshot document : task.getResult()){
                            Datauser datauser= new Datauser(document.getString("Email"),document.getString("Nik KTP"),document.getString("Nik Kartu Keluarga"),document.getString("Nama"),document.getString("Agama"),document.getString("Tempat dan Tanggal Lahir"),document.getString("Nomer Aktif"),document.getString("Alamat"));
                            list.add(datauser);
                        }
                        dataAdapter.notifyDataSetChanged();
                    }else{
                        Toast.makeText(Datawarga.this, "Data gagal di ambil", Toast.LENGTH_SHORT).show();
                    }
                        progressDialog.dismiss();
                    }
                });
    }
}