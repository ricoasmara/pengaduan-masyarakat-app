package com.example.pengaduanmasyakarat;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    TextView Tanggal;
    EditText Put;
    Button simpan;
    FirebaseFirestore db= FirebaseFirestore.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Put=findViewById(R.id.put);
        Tanggal=findViewById(R.id.tanggal);
        simpan=findViewById(R.id.simpan);

        final Calendar calendar = Calendar.getInstance();
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);

        Tanggal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog dialog = new DatePickerDialog(MainActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int  dayOfMonth) {
                        month= month+1;
                        String date= day+"/"+month+"/"+year;
                        Tanggal.setText(date);
                    }
                },year,month,day);
                dialog.show();
            }
        });


        simpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(Put.getText().length()>0 && Tanggal.getText().length()>0){
                    saveD(Put.getText().toString(),Tanggal.getText().toString());
                }else{

                }
            }
        });

    }
    private void saveD(String Put ,String Tanggal){
        Map<String, Object> user = new HashMap<>();
        user.put("Nama", Put);
        user.put("Tanggal",Tanggal);
        db.collection("test").add(user)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {

                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

            }
        });
    }
}

//progressDialog= new ProgressDialog(LaporanWarga.this);
//     progressDialog.setTitle("Loading");
//     progressDialog.setMessage("Mengambil Data");
//
//     laporanwargaAdapater= new LaporanwargaAdapater(getApplicationContext(),listlaporan);
//
//     RecyclerView.LayoutManager layoutManager= new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.VERTICAL,false);
//     RecyclerView.ItemDecoration decoration= new DividerItemDecoration(getApplicationContext(),DividerItemDecoration.VERTICAL);
//     recyclerView.setLayoutManager(layoutManager);
//     recyclerView.addItemDecoration(decoration);
//     recyclerView.setAdapter(laporanwargaAdapater);
//
//     progressDialog.show();
//        db.collection("Laporan Masyarakat").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
//            @SuppressLint("NotifyDataSetChanged")
//            @Override
//            public void onComplete(@NonNull Task<QuerySnapshot> task) {
//                listlaporan.clear();
//                if (task.isSuccessful()){
//                    for (QueryDocumentSnapshot document: task.getResult()){
//                        laporanuser laporan = new laporanuser(document.getString("Nama"),document.getString("Tanggal Pengaduan"),document.getString("Jam Pengaduan"),document.getString("Laporan"),document.getString("Solusi"));
//                        listlaporan.add(laporan);
//
//                    }
//                    laporanwargaAdapater.notifyDataSetChanged();
//                }else {
//                    Toast.makeText(getApplicationContext(),"Data gagal diambil", Toast.LENGTH_SHORT).show();
//                }
//                progressDialog.dismiss();
//            }
//        });