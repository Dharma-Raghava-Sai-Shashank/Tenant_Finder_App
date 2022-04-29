package com.example.tenantfinderapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

public class FinalProfile extends AppCompatActivity
{
    TextView a1 ;
    TextView a2  ;
    TextView a3 ;
    TextView a4 ;
    FirebaseAuth fAuth;
    FirebaseFirestore fstore;
    String userID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final_profile);

        a1 = findViewById(R.id.a1);
        a2 = findViewById(R.id.a2);
        a3 = findViewById(R.id.a3);
        a4 = findViewById(R.id.a4);
        fAuth =  FirebaseAuth.getInstance();
        fstore = FirebaseFirestore.getInstance();
        userID = fAuth.getCurrentUser().getUid();

        DocumentReference documentReference = fstore.collection("Users").document(userID);
        documentReference.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException error) {
                a1.setText("Name : "+ documentSnapshot.getString("Full Name"));
                a2.setText("Address : "+ documentSnapshot.getString("Address"));
                a3.setText("Phone : "+ documentSnapshot.getString("Phone"));
                a4.setText("Email : "+ documentSnapshot.getString("Email"));
            }
        });
    }
    public void edit (View view){
        startActivity(new Intent(getApplicationContext(),Profile.class));
        finish();
    }
    public void back (View view){
        startActivity(new Intent(getApplicationContext(),MainActivity.class));
        finish();
    }
}