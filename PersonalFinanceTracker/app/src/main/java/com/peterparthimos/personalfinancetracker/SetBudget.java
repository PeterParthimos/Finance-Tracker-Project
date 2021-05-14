package com.peterparthimos.personalfinancetracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class SetBudget extends AppCompatActivity {
    EditText newBudget;
    TextView currentBudget;
    Button submit, cancel;
    DBConnector dbConnector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_budget);

        currentBudget = findViewById(R.id.currentBudget);
        currentBudget.setText("Current Budget: $" + User.budget);

        newBudget = findViewById(R.id.newBudget);
        dbConnector = new DBConnector(this);

        submit = findViewById(R.id.submitBudget);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nb = newBudget.getText().toString();
                if (!nb.equals("")) {
                    double budget = Double.parseDouble(nb);
                    boolean isUpdated = dbConnector.editBudget(User.username, budget);
                    if (isUpdated) {
                        User.budget = budget;
                        Toast.makeText(submit.getContext(), "Budget Changed Successfully!", Toast.LENGTH_LONG).show();
                        Intent i = new Intent(submit.getContext(), Dashboard.class);
                        startActivity(i);
                    } else {
                        Toast.makeText(submit.getContext(), "Something Went Wrong!", Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(submit.getContext(), "Please Enter a Valid Number!", Toast.LENGTH_LONG).show();
                }
            }
        });

        cancel = findViewById(R.id.cancelBudget);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent x = new Intent(cancel.getContext(), Dashboard.class);
                startActivity(x);
            }
        });
    }
}