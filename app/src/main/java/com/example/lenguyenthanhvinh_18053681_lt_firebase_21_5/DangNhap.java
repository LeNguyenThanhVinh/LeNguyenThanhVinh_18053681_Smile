package com.example.lenguyenthanhvinh_18053681_lt_firebase_21_5;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class DangNhap extends AppCompatActivity {

    EditText txtEmailDangNhap, txtPasswordDangNhap;
    Button btnDN;
    private FirebaseAuth auth;
    public static String email="Vinh";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_nhap);
        auth = FirebaseAuth.getInstance();

        txtEmailDangNhap = findViewById(R.id.txtEmailDangNhap);
        txtPasswordDangNhap = findViewById(R.id.txtPasswordDangNhap);
        btnDN = findViewById(R.id.btnDN);

        btnDN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                email = txtEmailDangNhap.getText().toString();
                final String password = txtPasswordDangNhap.getText().toString();

                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(getApplicationContext(), "Enter email address!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(password)) {
                    Toast.makeText(getApplicationContext(), "Enter password!", Toast.LENGTH_SHORT).show();
                    return;
                }
                auth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener(DangNhap.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                // If sign in fails, display a message to the user. If sign in succeeds
                                // the auth state listener will be notified and logic to handle the
                                // signed in user can be handled in the listener.

                                if (!task.isSuccessful()) {
                                    // there was an error
                                    Toast.makeText(DangNhap.this, "Sai tài khoản hoặc mật khẩu", Toast.LENGTH_SHORT).show();
                                } else {
                                    Intent intent = new Intent(DangNhap.this, ChonCamXuc.class);
                                    intent.putExtra("VinhVinhVinhVinh","a");
                                    startActivity(intent);
                                    finish();
                                }
                            }
                        });
            }

        });
    }
}