package com.example.pharmablock.Producer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.pharmablock.Models.DetailsModel;
import com.example.pharmablock.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ProducerDetails extends AppCompatActivity {

    private EditText companyname;
    private EditText date;
    private EditText cost;
    private EditText amount;
    private EditText description;
    private EditText contact;
    //    private EditText buyervehicletype;
    private Button detailupload;
    public String value;
    public String medname;

    public static String hashstring;
    public static int hashvalue;

    FirebaseDatabase firebaseDatabase;
    private FirebaseAuth mAuth;
    DatabaseReference dbref, dbrefname, dbrefdate, dbrefcost, dbrefamt, dbrefdesc, dbrefcont, dbrefmname, dbrefcode;

    DetailsModel detailsModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_producer_details);


        companyname=(EditText)findViewById(R.id.manufacturingcompany);
        date=(EditText)findViewById(R.id.date);
        cost=(EditText)findViewById(R.id.costofproduct);
        amount=(EditText)findViewById(R.id.amountofproduct);
        description=(EditText)findViewById(R.id.productdescription);
        contact=(EditText)findViewById(R.id.contactnumber);
        medname= Qrscanner.medicinename;
        hashstring= medname;
        hashvalue=hashstring.hashCode();

        detailupload=(Button)findViewById(R.id.donedetails);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            value = extras.getString("key");
            //The key argument here must match that used in the other activity
        }

        firebaseDatabase=FirebaseDatabase.getInstance();
        mAuth=FirebaseAuth.getInstance();

        dbref = firebaseDatabase.getReference("Producer").child(mAuth.getCurrentUser().getUid()).child(value).child("Details");
        dbrefname = firebaseDatabase.getReference("Producer").child(mAuth.getCurrentUser().getUid()).child("Details").child("Company_name");
        dbrefdate = firebaseDatabase.getReference("Producer").child(mAuth.getCurrentUser().getUid()).child("Details").child("Manufacturing_date");
        dbrefcost = firebaseDatabase.getReference("Producer").child(mAuth.getCurrentUser().getUid()).child("Details").child("Cost");
        dbrefamt = firebaseDatabase.getReference("Producer").child(mAuth.getCurrentUser().getUid()).child("Details").child("Quantity");
        dbrefdesc= firebaseDatabase.getReference("Producer").child(mAuth.getCurrentUser().getUid()).child("Details").child("Description");
        dbrefcont = firebaseDatabase.getReference("Producer").child(mAuth.getCurrentUser().getUid()).child("Details").child("Company_contact");

        dbrefmname = firebaseDatabase.getReference("Producer").child(mAuth.getCurrentUser().getUid()).child("Details").child("Medicine_name");
        dbrefcode= firebaseDatabase.getReference("Producer").child(mAuth.getCurrentUser().getUid()).child("Details").child("Hash_code");

        detailsModel= new DetailsModel();

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

        dbrefcost.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String st= snapshot.getValue(String.class);
                cost.setText(st);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        dbrefamt.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String st= snapshot.getValue(String.class);
                amount.setText(st);
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
        String pcost= cost.getText().toString();
        String pamount= amount.getText().toString();
        String pdesc= description.getText().toString();
        String pcont= contact.getText().toString();



        if(TextUtils.isEmpty(pname) && TextUtils.isEmpty(pcont) && TextUtils.isEmpty(pamount) && TextUtils.isEmpty(pdate) && TextUtils.isEmpty(pdesc)){
            Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show();
        }else{
            addDatatoFirebase(pname,pdate,pcost,pamount,pdesc,pcont);
        }

    }

    private void addDatatoFirebase(String pname, String pdate, String pcost, String pamount, String pdesc, String pcont) {
        detailsModel.setName(pname);
        detailsModel.setDate(pdate);
        detailsModel.setCost(pcost);
        detailsModel.setAmt(pamount);
        detailsModel.setDesc(pdesc);
        detailsModel.setCont(pcont);
        detailsModel.setMname(medname);
        detailsModel.setHashvalue(hashvalue);

        dbref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                dbref.setValue(detailsModel);
                Toast.makeText(ProducerDetails.this, "Details added", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(ProducerDetails.this, DetailsAdded.class));

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}