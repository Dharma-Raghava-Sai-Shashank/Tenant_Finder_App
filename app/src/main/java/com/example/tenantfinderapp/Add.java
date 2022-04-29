package com.example.tenantfinderapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class Add extends AppCompatActivity
{

    EditText r1,r2,r3,r4,r5;
    FirebaseFirestore fstore;
    String userID;
    FirebaseAuth fAuth;
    Button Add;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        r1 = findViewById(R.id.rp1);
        r2 = findViewById(R.id.rp2);
        r3 = findViewById(R.id.rp3);
        r4 = findViewById(R.id.rp4);
        r5 = findViewById(R.id.rp5);
        Add = findViewById(R.id.addp);
        fAuth =  FirebaseAuth.getInstance();
        fstore = FirebaseFirestore.getInstance();

        Add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String R1 = r1.getText().toString() ;
                String R2 = r2.getText().toString() ;
                String R3 = r3.getText().toString() ;
                String R4 = r4.getText().toString() ;
                String R5 = r5.getText().toString() ;

                userID = fAuth.getCurrentUser().getUid();
                DocumentReference documentReference = fstore.collection("Owner Property").document(userID);
                Map<String,Object> user = new HashMap<>();
                user.put("Owner Name",R2);
                user.put("Phone",R3);
                user.put("Price",R4);
                user.put("Address",R5 );
                user.put("Type of House",R1);
                documentReference.set(user);

                startActivity(new Intent(Add.this.getApplicationContext(), MainActivity.class));
            }
        });

    
        
    }
    public void Back (View view){
        startActivity(new Intent(getApplicationContext(),MainActivity.class));
        finish();
    }

}