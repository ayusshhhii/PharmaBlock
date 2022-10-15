package com.example.pharmablock.Distributor;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.pharmablock.Producer.ProducerDetails;
import com.example.pharmablock.Producer.Qrscanner;
import com.example.pharmablock.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.zxing.Result;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;

import org.jetbrains.annotations.NotNull;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class QrScanDistributor extends AppCompatActivity implements ZXingScannerView.ResultHandler{

    ZXingScannerView scannerView;
    DatabaseReference dbref;
    private FirebaseAuth mAuth;
    public int hashcheck;

   @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       scannerView=new ZXingScannerView(this);
       setContentView(scannerView);


       mAuth=FirebaseAuth.getInstance();

       dbref= FirebaseDatabase.getInstance().getReference();

       hashcheck= ProducerDetails.hashvalue;

       Dexter.withContext(getApplicationContext())
               .withPermission(Manifest.permission.CAMERA)
               .withListener(new PermissionListener() {
                   @Override
                   public void onPermissionGranted(PermissionGrantedResponse permissionGrantedResponse) {

                       scannerView.startCamera();
                   }

                   @Override
                   public void onPermissionDenied(PermissionDeniedResponse permissionDeniedResponse) {

                   }

                   @Override
                   public void onPermissionRationaleShouldBeShown(PermissionRequest permissionRequest, PermissionToken permissionToken) {
                       permissionToken.continuePermissionRequest();
                   }
               }).check();
   }

    @Override
    public void handleResult(Result rawResult) {
        String data = rawResult.getText().toString();
        if(data.equals(hashcheck)){
            Toast.makeText(this, "verified", Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(this, "not verified", Toast.LENGTH_SHORT).show();
        }

    }



    @Override
    protected void onPause() {
        super.onPause();
        scannerView.stopCamera();
    }


    @Override
    protected void onResume() {
        super.onResume();
        scannerView.setResultHandler(this);
        scannerView.startCamera();
    }
}