package com.example.pharmablock.Distributor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.example.pharmablock.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class DistributorHome extends AppCompatActivity {

    FloatingActionButton scanbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_distributor_home);

        scanbtn=(FloatingActionButton)findViewById(R.id.scanner);

        scanbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DistributorHome.this, QrScanDistributor.class));
            }
        });
    }
}