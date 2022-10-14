package com.example.pharmablock;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pharmablock.Consumer.ConsumerHome;
import com.example.pharmablock.Distributor.DistributorHome;
import com.example.pharmablock.Producer.ProducerHome;
import com.example.pharmablock.Retailer.RetailerHome;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class UserAdminSelection extends AppCompatActivity {

    private FirebaseAuth mAuth;
    Switch aSwitch;
    Button t1;
    LinearLayout producertxt, distributortxt, retailertxt, consumertxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_user_admin_selection);
        mAuth=FirebaseAuth.getInstance();
        FirebaseUser currentUser = mAuth.getCurrentUser();

        t1=(Button)findViewById(R.id.logout);

        t1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(UserAdminSelection.this, "hey", Toast.LENGTH_SHORT).show();
                mAuth.signOut();
            }
        });

        producertxt=(LinearLayout) findViewById(R.id.producertext);
        producertxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(UserAdminSelection.this, ProducerHome.class));
            }
        });

        distributortxt=(LinearLayout) findViewById(R.id.distributortext);
        distributortxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(UserAdminSelection.this, DistributorHome.class));
            }
        });

        retailertxt=(LinearLayout) findViewById(R.id.retailertext);
        retailertxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(UserAdminSelection.this, RetailerHome.class));
            }
        });

        consumertxt=(LinearLayout) findViewById(R.id.consumertext);
        consumertxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(UserAdminSelection.this, ConsumerHome.class));
            }
        });

    }



}