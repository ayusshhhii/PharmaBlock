package com.example.pharmablock.Distributor;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.pharmablock.Models.DistributordetailsModel;
import com.example.pharmablock.Producer.DetailsAdded;
import com.example.pharmablock.Producer.ProducerDetails;
import com.example.pharmablock.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class DistributorDetails extends AppCompatActivity {

    private EditText companyname;
    private EditText date;
    private EditText description;
    private EditText contact;

    private Button detailupload;

    FirebaseDatabase firebaseDatabase;
    private FirebaseAuth mAuth;
    DatabaseReference dbref, dbrefname, dbrefdate, dbrefdesc, dbrefcont;

    DistributordetailsModel distributordetailsModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_distributor_details);

        companyname=(EditText)findViewById(R.id.distributorcompany);
        date= (EditText)findViewById(R.id.datedist);
        description= (EditText)findViewById(R.id.productdescriptiondist);
        contact=(EditText)findViewById(R.id.contactnumberdist);

        detailupload=(Button)findViewById(R.id.distdetails);

        firebaseDatabase=FirebaseDatabase.getInstance();
        mAuth=FirebaseAuth.getInstance();

        dbref = firebaseDatabase.getReference("Distributor").child(mAuth.getCurrentUser().getUid()).child("Details");
        dbrefname = firebaseDatabase.getReference("Distributor").child(mAuth.getCurrentUser().getUid()).child("Details").child("Company_name");
        dbrefdate = firebaseDatabase.getReference("Distributor").child(mAuth.getCurrentUser().getUid()).child("Details").child("Manufacturing_date");
        dbrefdesc= firebaseDatabase.getReference("Distributor").child(mAuth.getCurrentUser().getUid()).child("Details").child("Description");
        dbrefcont = firebaseDatabase.getReference("Distributor").child(mAuth.getCurrentUser().getUid()).child("Details").child("Company_contact");

        distributordetailsModel = new DistributordetailsModel();

        detailupload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                upload();
            }
        });

        dbrefname.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String st= snapshot.getValue(String.class);
                companyname.setText(st);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        dbrefdate.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String st= snapshot.getValue(String.class);
                date.setText(st);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        dbrefdesc.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String st= snapshot.getValue(String.class);
                description.setText(st);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        dbrefcont.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String st= snapshot.getValue(String.class);
                contact.setText(st);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    private void upload() {
        String pname= companyname.getText().toString();
        String pdate= date.getText().toString();
        String pdesc= description.getText().toString();
        String pcont= contact.getText().toString();



        if(TextUtils.isEmpty(pname) && TextUtils.isEmpty(pcont) && TextUtils.isEmpty(pdate) && TextUtils.isEmpty(pdesc)){
            Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show();
        }else{
            addDatatoFirebase(pname,pdate,pdesc,pcont);
        }
    }

    private void addDatatoFirebase(String pname, String pdate, String pdesc, String pcont) {
        distributordetailsModel.setCompany_name(pname);
        distributordetailsModel.setDist_date(pdate);
        distributordetailsModel.setProduct_disc(pdesc);
        distributordetailsModel.setContact(pcont);

        dbref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                dbref.setValue(distributordetailsModel);
                Toast.makeText(DistributorDetails.this, "Details added", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(DistributorDetails.this, DetailsAdded.class));

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}