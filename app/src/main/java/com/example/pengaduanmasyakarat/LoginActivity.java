package com.example.pengaduanmasyakarat;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pengaduanmasyakarat.MenuAdmin.MenuAdmin;
import com.example.pengaduanmasyakarat.MenuWarga.MenuWarga;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class LoginActivity extends AppCompatActivity {
    TextView reg;
    EditText inputemail,inputpassword ,daftarakun;
    String etemail,etpassword;
    Button btnlogin;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mAuth = FirebaseAuth.getInstance();
        inputemail = findViewById(R.id.etemail);
        inputpassword= findViewById(R.id.etpassword);
        btnlogin = findViewById(R.id.btnlogin);
        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()){
                    case R.id.btnlogin:
                        inputemail.setText(inputemail.getText().toString()+"@warga.com");
                        break;

                }
                ceklogin();
            }
        });
        reg=(TextView) findViewById(R.id.daftarakun);
        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this,RegisterActivity.class));
            }
        });


    }



    private void ceklogin() {
        etemail=inputemail.getText().toString();
        etpassword=inputpassword.getText().toString();

        mAuth.signInWithEmailAndPassword(etemail,etpassword).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {

                    String uid= task.getResult().getUser().getUid();
                    FirebaseDatabase firebaseDatabase= FirebaseDatabase.getInstance();
                    firebaseDatabase.getReference().child("Users").child(uid).child("usertype").addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            int usertype=snapshot.getValue(Integer.class);
                            if (usertype==0){
                                startActivity(new Intent(LoginActivity.this, MenuAdmin.class));
                            }

                            if (usertype==1){
                                startActivity(new Intent(LoginActivity.this, MenuWarga.class));
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });

                    Toast.makeText(LoginActivity.this, "Login Berhasil", Toast.LENGTH_SHORT).show();

                } else {
                    Toast.makeText(LoginActivity.this, "Login Gagal,Email atau Password salah", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}