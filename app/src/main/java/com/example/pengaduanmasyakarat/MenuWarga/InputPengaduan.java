package com.example.pengaduanmasyakarat.MenuWarga;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.pengaduanmasyakarat.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class InputPengaduan extends AppCompatActivity {
    private TextView tgl,jam;
    private EditText nama,laporan,solusi;
    private Button btnlapor;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private ProgressDialog progressDialog;
    private String id="";
    private String uid="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_pengaduan);

        nama=findViewById(R.id.nama);
        tgl=findViewById(R.id.tgl);
        jam=findViewById(R.id.jam);
        laporan=findViewById(R.id.laporan);
        solusi=findViewById(R.id.solusi);
        btnlapor=findViewById(R.id.btnlapor);

        progressDialog = new ProgressDialog(InputPengaduan.this);
        progressDialog.setTitle("Loading...");
        progressDialog.setMessage("Menyimpan Data...");
        //Date
        final Calendar calendar = Calendar.getInstance();
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);

        tgl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog dialog= new DatePickerDialog(InputPengaduan.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        month= month+1;
                        String date= day+"/"+month+"/"+year;
                        tgl.setText(date);
                    }
                },year,month,day);
                dialog.show();
            }
        });

        jam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TimePickerDialog timePickerDialog =new TimePickerDialog(InputPengaduan.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

                        Calendar calendar= Calendar.getInstance();
                        calendar.set(0,0,0,hourOfDay,minute );

                        SimpleDateFormat sdf =new SimpleDateFormat("hh:mm: aa");
                        jam.setText("Time:"+sdf.format(calendar.getTime()));
                    }
                },12,0,true);
                timePickerDialog.show();
            }
        });

        btnlapor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(nama.getText().length()>0 &&tgl.getText().length()>0 && jam.getText().length()>0 && laporan.getText().length()>0){
                    lapor(nama.getText().toString(),tgl.getText().toString(),jam.getText().toString(),laporan.getText().toString(),solusi.getText().toString());
                }
            }
        });
        Intent intent= getIntent();
        if (intent!=null){
            id=intent.getStringExtra("id");
            nama.setText(intent.getStringExtra("Nama"));
            tgl.setText(intent.getStringExtra("Tanggal Pengaduan"));
            jam.setText(intent.getStringExtra("Jam Pengaduan"));
            laporan.setText(intent.getStringExtra("Laporan"));
            solusi.setText(intent.getStringExtra("Solusi"));

        }
    }

    private void lapor(String nama, String tgl, String jam, String laporan , String solusi) {
        Map<String, Object> user = new HashMap<>();
        user.put("Nama", nama);
        user.put("Tanggal Pengaduan", tgl);
        user.put("Jam Pengaduan", jam);
        user.put("Laporan", laporan);
        user.put("Solusi", solusi);


        progressDialog.show();
        if (id!=null) {
            db.collection("Laporan Masyarakat").document(id).set(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
               if (task.isSuccessful()){
                   Toast.makeText(InputPengaduan.this, "Berhasil Edit", Toast.LENGTH_SHORT).show();
                   finish();
               }else{
                   Toast.makeText(InputPengaduan.this, "Gagal Edit", Toast.LENGTH_SHORT).show();
               }
                }
            });
        } else {
            db.collection("Laporan Masyarakat").add(user).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                @Override
                public void onSuccess(DocumentReference documentReference) {
                    progressDialog.dismiss();
                    Toast.makeText(InputPengaduan.this, "Berhasil Lapor", Toast.LENGTH_SHORT).show();
                    finish();

                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    progressDialog.dismiss();
                    Toast.makeText(getApplicationContext(), e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}