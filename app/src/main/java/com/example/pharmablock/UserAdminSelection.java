package com.example.pharmablock;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class UserAdminSelection extends AppCompatActivity {

    private FirebaseAuth mAuth;
    Switch aSwitch;
    TextView t1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_admin_selection);
        mAuth=FirebaseAuth.getInstance();
        FirebaseUser currentUser = mAuth.getCurrentUser();

        t1=(TextView)findViewById(R.id.logout);

        t1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mAuth.signOut();
            }
        });

    }


    public void onClick(View view) {
        Toast.makeText(this, "hey", Toast.LENGTH_SHORT).show();
        mAuth.signOut();

    }
}