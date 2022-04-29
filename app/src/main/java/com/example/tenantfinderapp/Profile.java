package com.example.tenantfinderapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class Profile extends AppCompatActivity
{
        EditText r1,r2,r3,r4;
        Button Submit;
        FirebaseFirestore fstore;
        String userID;
        FirebaseAuth fAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        r1 = findViewById(R.id.r1);
        r2 = findViewById(R.id.r2);
        r3 = findViewById(R.id.r3);
        r4 = findViewById(R.id.r4);
        Submit = findViewById(R.id.submit);
        fAuth =  FirebaseAuth.getInstance();
        fstore = FirebaseFirestore.getInstance();

        Submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String R1 = r1.getText().toString() ;
                String R2 = r2.getText().toString() ;
                String R3 = r3.getText().toString() ;
                String R4 = r4.getText().toString() ;

                userID = fAuth.getCurrentUser().getUid();
                DocumentReference documentReference = fstore.collection("Users").document(userID);
                Map<String,Object> user = new HashMap<>();
                user.put("Full Name",R1);
                user.put("Address",R2);
                user.put("Phone",R3);
                user.put("Email",R4 );
                documentReference.set(user);

                startActivity(new Intent(Profile.this.getApplicationContext(), FinalProfile.class));
            }
        });

    }
    public void Back (View view){
        startActivity(new Intent(getApplicationContext(),FinalProfile.class));
        finish();
    }

}