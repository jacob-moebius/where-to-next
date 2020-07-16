package edu.miracostacollege.cs134.wheretonext;

import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.text.NumberFormat;

public class CollegeDetailsActivity extends AppCompatActivity {

    ImageView collegeDetailsImageView;
    TextView collegeDetailsNameTextView;
    TextView collegeDetailsPopulationTextView;
    TextView collegeDetailsTuitionTextView;
    RatingBar collegeDetailsRatingBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_college_details);

        collegeDetailsImageView = findViewById(R.id.collegeDetailsImageView);
        collegeDetailsNameTextView = findViewById(R.id.collegeDetailsNameTextView);
        collegeDetailsPopulationTextView = findViewById(R.id.collegeDetailsPopulationTextView);
        collegeDetailsTuitionTextView = findViewById(R.id.collegeDetailsTuitionTextView);
        collegeDetailsRatingBar = findViewById(R.id.collegeDetailsRatingBar);

        Intent intent = getIntent();
        String name = intent.getStringExtra("Name");
        int population = intent.getIntExtra("Population", 0);
        float tuition = (float) intent.getDoubleExtra("Tuition", 0.0);
        float rating = (float) intent.getDoubleExtra("Rating", 0.0);
        String imageName = intent.getStringExtra("ImageName");

        NumberFormat currency = NumberFormat.getCurrencyInstance();
        NumberFormat thousands = NumberFormat.getIntegerInstance();

        collegeDetailsNameTextView.setText(name);
        collegeDetailsPopulationTextView.setText(getString(R.string.annual_enrollment, thousands.format(population)));
        collegeDetailsTuitionTextView.setText(getString(R.string.in_state_tuition, currency.format(tuition)));
        collegeDetailsRatingBar.setRating((float) rating);

        AssetManager am = this.getAssets();
        try {
            InputStream stream = am.open(imageName);
            Drawable college = Drawable.createFromStream(stream, name);
            collegeDetailsImageView.setImageDrawable(college);
        }
        catch (IOException ex)
        {
            Log.e("Where To Next", "Error loading " + imageName, ex);
        }
    }
}
