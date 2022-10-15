package com.example.pharmablock.Producer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.example.pharmablock.R;

public class DetailsAdded extends AppCompatActivity {

    ImageView tickimg;
    Button roadmapbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_added);

        tickimg=(ImageView)findViewById(R.id.done);

        roadmapbtn=(Button)findViewById(R.id.roadmap);

        YoYo.with(Techniques.ZoomIn)
                .duration(3000)
                .repeat(0)
                .playOn(findViewById(R.id.done));


        roadmapbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(DetailsAdded.this , Roadmap.class));
            }
        });

    }
}