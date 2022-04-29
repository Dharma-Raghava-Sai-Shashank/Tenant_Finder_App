package com.example.tenantfinderapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

public class MainActivity extends AppCompatActivity {
    TextView a1 ;
    TextView a2  ;
    TextView a3 ;
    TextView a4 ;
    TextView a5 ;
    FirebaseAuth fAuth;
    FirebaseFirestore fstore;
    String userID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        a1 = findViewById(R.id.ap11);
        a2 = findViewById(R.id.ap12);
        a3 = findViewById(R.id.ap13);
        a4 = findViewById(R.id.ap14);
        a5 = findViewById(R.id.ap15);
        fAuth =  FirebaseAuth.getInstance();
        fstore = FirebaseFirestore.getInstance();
        userID = fAuth.getCurrentUser().getUid();

        DocumentReference documentReference = fstore.collection("Owner Property").document(userID);
        documentReference.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException error) {
                a1.setText(documentSnapshot.getString("Type of House"));
                a2.setText("Owner Name : "+documentSnapshot.getString("Owner Name"));
                a3.setText("Phone : "+documentSnapshot.getString("Phone"));
                a4.setText("Price : "+documentSnapshot.getString("Price"));
                a5.setText("Address : "+documentSnapshot.getString("Address"));
            }
        });

    }


    public void logout (View view){
        FirebaseAuth.getInstance().signOut();
        startActivity(new Intent(getApplicationContext(),Login.class));
        finish();
    }
    public void profile (View view){
        startActivity(new Intent(getApplicationContext(),FinalProfile.class));
        finish();
    }
    public void add (View view){
        startActivity(new Intent(getApplicationContext(),Add.class));
        finish();
    }
}