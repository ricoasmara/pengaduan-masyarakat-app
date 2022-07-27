package com.example.pengaduanmasyakarat;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class RegisterActivity extends AppCompatActivity {
    TextView log;
    EditText inputemail, inputpassword, inputpassword2,inputnik,inputnikkk,inputnama,inputagama,inputtgl,inputnomer, inputalamat;
    String regemail, regpassword, regpassword2 ,nik,nikkk,nama,agama,tgllahir,nomer,alamat;
    Button btndaftar;
    FirebaseAuth mAuth;
    FirebaseFirestore db= FirebaseFirestore.getInstance();
    ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        mAuth = FirebaseAuth.getInstance();
        log=(TextView) findViewById(R.id.loginakun) ;
        inputnik =findViewById(R.id.nik);
        inputnikkk =findViewById(R.id.nikkk);
        inputnama =findViewById(R.id.nama);
        inputagama=findViewById(R.id.agama);
        inputtgl =findViewById(R.id.tgllahir);
        inputnomer =findViewById(R.id.nomer);
        inputalamat =findViewById(R.id.alamat);
        inputemail=findViewById(R.id.regemail);
        inputpassword=findViewById(R.id.regpassword);
        inputpassword2=findViewById(R.id.regpassword2);

        //message
        progressDialog = new ProgressDialog(RegisterActivity.this);
        progressDialog.setTitle("Loading...");
        progressDialog.setMessage("Menyimpan Data");

        log.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               startActivity(new Intent(getApplicationContext(), LoginActivity.class));
            }
        });

        btndaftar = findViewById(R.id.btndaftar);
        btndaftar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registrasi();
            if(inputemail.getText().length()>0 && inputnik.getText().length()>0 && inputnikkk.getText().length()>0&& inputnama.getText().length()>0&& inputagama.getText().length()>0&& inputtgl.getText().length()>0&& inputnomer.getText().length()>0&&inputalamat.getText().length()>0){
                Datawarga(inputemail.getText().toString(),inputnik.getText().toString(),inputnikkk.getText().toString(),inputnama.getText().toString(),inputagama.getText().toString(),inputtgl.getText().toString(),inputnomer.getText().toString(),inputalamat.getText().toString());

            }else{
                Toast.makeText(getApplicationContext(),"Silahkan isi Semua data dengan benar",Toast.LENGTH_LONG).show();
            }
            }
        });


    }

    private void Datawarga(String inputemail,String inputnik, String inputnikkk,String inputnama, String inputagama, String inputtgl, String inputnomer, String inputalamat){
        Map<String, Object> user = new HashMap<>();
        user.put("Email",inputemail);
        user.put("Nik KTP",inputnik);
        user.put("Nik Kartu Keluarga",inputnikkk);
        user.put("Nama",inputnama);
        user.put("Agama",inputagama);
        user.put("Tempat dan Tanggal Lahir",inputtgl);
        user.put("Nomer Aktif",inputnomer);
        user.put("Alamat",inputalamat);

        db.collection("Data Warga").add(user).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
            @Override
            public void onSuccess(DocumentReference documentReference) {
                Toast.makeText(getApplicationContext(),"Berhasil Simpan",Toast.LENGTH_SHORT).show();

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getApplicationContext(), e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void registrasi() {
        regemail = inputemail.getText().toString();
        regpassword =inputpassword.getText().toString();
        regpassword2= inputpassword2.getText().toString();

        mAuth.createUserWithEmailAndPassword(regemail,regpassword2).addOnCompleteListener(this, new OnCompleteListener<AuthResult>(){
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {

                    String uid=task.getResult().getUser().getUid();
                    Users users=new Users(uid,inputemail.getText().toString(),inputnik.getText().toString(),inputnikkk.getText().toString(),inputnama.getText().toString(),inputtgl.getText().toString(),inputnomer.getText().toString(),inputalamat.getText().toString(),1);

                    FirebaseDatabase.getInstance().getReference().child("Users").child(uid).setValue(users);

                    // Sign in success, update UI with the signed-in user's information
                    Toast.makeText(RegisterActivity.this, "Register Berhasil", Toast.LENGTH_SHORT).show();
                } else {
                    // If sign in fails, display a message to the user.
                    Toast.makeText(RegisterActivity.this, "Register Gagal", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}