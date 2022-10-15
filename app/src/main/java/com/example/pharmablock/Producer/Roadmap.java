package com.example.pharmablock.Producer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.TextView;

import com.baoyachi.stepview.VerticalStepView;
import com.example.pharmablock.R;
import com.example.pharmablock.UserAdminSelection;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class Roadmap extends AppCompatActivity {

    VerticalStepView verticalStepView;

    TextView dateadded;

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(Roadmap.this , UserAdminSelection.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_roadmap);

        verticalStepView=(VerticalStepView)findViewById(R.id.verticalStepView);
        dateadded=(TextView)findViewById(R.id.date);

        Date c = Calendar.getInstance().getTime();

        SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy", Locale.getDefault());
        String formattedDate = df.format(c);
        dateadded.setText("-Date " +formattedDate);

        List<String> sources = new ArrayList<>();


        sources.add("Producer");
        sources.add("Distributor");
        sources.add("Retailer");
        sources.add("Consumer");

        int n=sources.size();
        verticalStepView.setStepsViewIndicatorComplectingPosition(n-3)
                .reverseDraw(false)
                .setStepViewTexts(sources)
                .setLinePaddingProportion(3.85f)
                .setStepsViewIndicatorCompletedLineColor(Color.parseColor("#FFFF00"))
                .setStepViewComplectedTextColor(Color.parseColor("#FFFF00"))
                .setStepViewComplectedTextColor(ContextCompat.getColor(this, R.color.uncompleted_text_color))
                .setStepsViewIndicatorUnCompletedLineColor(Color.parseColor("#FFFF00"))
                .setStepsViewIndicatorCompleteIcon(ContextCompat.getDrawable(this , R.drawable.pngreen))
                .setStepsViewIndicatorAttentionIcon(ContextCompat.getDrawable(this , R.drawable.attention))
                .setStepsViewIndicatorDefaultIcon(ContextCompat.getDrawable(this , R.drawable.default_icon));



    }

}