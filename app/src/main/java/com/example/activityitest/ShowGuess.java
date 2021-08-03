package com.example.activityitest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class ShowGuess extends AppCompatActivity {

    private TextView showGuessTextview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_guess);

        showGuessTextview = findViewById(R.id.name_result);

        //can use bundle to get more stuff
        Bundle extra = getIntent().getExtras();


        if (getIntent().getStringExtra("guess") != null) {

            showGuessTextview.setText(getIntent().getStringExtra("guess"));
            Log.d("Name extra", "onCreate: " + extra.getString("name"));
            Log.d("Name extra 2", "onCreate: " + extra.getInt("age"));

        }

        //quay trở lại activity thứ nhất  với intent
        showGuessTextview.setOnClickListener(v -> {
            Intent intent = getIntent();
            intent.putExtra("message_back","From second Activity");
            setResult(RESULT_OK,intent);
            finish();
        });
    }
}