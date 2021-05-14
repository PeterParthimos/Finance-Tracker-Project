package com.peterparthimos.personalfinancetracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Average extends AppCompatActivity {
    TextView avg, exp, status;
    Button back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_average);

        avg = findViewById(R.id.averageExpense);
        exp = findViewById(R.id.currentSpent);
        status = findViewById(R.id.statusMessage);
        back = findViewById(R.id.backButtonAvg);

        avg.setText(String.format("$%.2f", User.average));
        exp.setText(String.format("$%.2f", User.spent));

        if (User.spent > User.average) {
            status.setText("Oh no! You are currently above your monthly average!");
        } else if (User.spent == User.average) {
            status.setText("Close one! You are currently matching your monthly average!");
        } else {
            status.setText("Congratulations! You are currently below your monthly average!");
        }

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(back.getContext(), Dashboard.class);
                startActivity(i);
            }
        });
    }
}