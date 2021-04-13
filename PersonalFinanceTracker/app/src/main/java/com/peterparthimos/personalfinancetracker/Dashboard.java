package com.peterparthimos.personalfinancetracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Dashboard extends AppCompatActivity {
    Button editBudget, averageExpenses, addExpense;
    TextView hello, remaining, budget;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        hello = findViewById(R.id.helloLabel);
        remaining = findViewById(R.id.remainingBalance);
        budget = findViewById(R.id.budget);

        hello.setText("Hello, " + User.fname + "!");
        remaining.setText("$" + (User.budget - User.spent));
        budget.setText("of $" + User.budget + " Remaining");

        averageExpenses = findViewById(R.id.averageExpenseButton);
        averageExpenses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(averageExpenses.getContext(), "Feature Coming Soon!", Toast.LENGTH_LONG).show();
            }
        });

        editBudget = findViewById(R.id.editBudgetButton);
        editBudget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent x = new Intent(editBudget.getContext(), SetBudget.class);
                startActivity(x);
            }
        });

        addExpense = findViewById(R.id.addExpenseButton);
        addExpense.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(averageExpenses.getContext(), "Feature Coming Soon!", Toast.LENGTH_LONG).show();
            }
        });
    }
}