package com.example.lenguyenthanhvinh_18053681_lt_firebase_21_5;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class ChonCamXuc extends AppCompatActivity {

    ImageView imgNormal, imgHappy, imgUnhappy;
    Button btnDN;
    DatabaseReference mData;
    int normal, happy, unhappy;
    String name = "Thành Vinh";
    String email = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chon_cam_xuc);
        imgNormal = findViewById(R.id.imgNormal);
        imgHappy = findViewById(R.id.imgHappy);
        imgUnhappy = findViewById(R.id.imgUnhappy);
        btnDN = findViewById(R.id.btnDN);
//        Intent intent = getIntent();
//        email = intent.getStringExtra("a");
        email = DangNhap.email;
        mData = FirebaseDatabase.getInstance().getReference();

        Query query = mData.orderByChild("Email").equalTo(email);
        ValueEventListener valueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot ds : dataSnapshot.getChildren()) {
                    name = ds.getKey();
                    //Log.d(TAG, key);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                //Log.d(TAG, databaseError.getMessage());
            }
        };
        query.addListenerForSingleValueEvent(valueEventListener);

        mData.child(name).child("Cảm xúc").child("Normal").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                normal = Integer.parseInt(snapshot.getValue().toString());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        mData.child(name).child("Cảm xúc").child("Unhappy").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                unhappy = Integer.parseInt(snapshot.getValue().toString());

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        mData.child(name).child("Cảm xúc").child("Happy").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                happy = Integer.parseInt(snapshot.getValue().toString());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        imgNormal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                normal++;
                mData.child(name).child("Cảm xúc").child("Normal").setValue(normal);
                Toast.makeText(ChonCamXuc.this,"Cảm xúc normal: " +normal, Toast.LENGTH_SHORT).show();
            }
        });

        imgUnhappy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                unhappy++;
                mData.child(name).child("Cảm xúc").child("Unhappy").setValue(unhappy);
                Toast.makeText(ChonCamXuc.this,"Cảm xúc unhappy: " +unhappy, Toast.LENGTH_SHORT).show();
            }
        });

        imgHappy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                happy++;
                mData.child(name).child("Cảm xúc").child("Happy").setValue(happy);
                Toast.makeText(ChonCamXuc.this,"Cảm xúc happy: " +happy, Toast.LENGTH_SHORT).show();
            }
        });

        btnDN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ChonCamXuc.this, email, Toast.LENGTH_SHORT).show();
            }
        });
    }
}