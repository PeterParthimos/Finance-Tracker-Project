package com.peterparthimos.personalfinancetracker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class Dashboard extends AppCompatActivity {
    Button editBudget, averageExpenses, addExpense;
    TextView hello, remaining, budget;
    MyRecyclerViewAdapter adapter;
    SubscriptionsRecyclerViewAdapter subscriptionsAdapter;
    DBConnector dbConnector;
    ArrayList<String> prices = new ArrayList<>();
    ArrayList<String> names = new ArrayList<>();
    ArrayList<String> dates = new ArrayList<>();

    ArrayList<String> monthlyPrices = new ArrayList<>();
    ArrayList<String> subscriptionNames = new ArrayList<>();
    ArrayList<String> dueDates = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        dbConnector = new DBConnector(this);

        RecyclerView recyclerView = findViewById(R.id.purchaseRecycler);
        dbConnector.getAllPurchases(User.username, prices, dates, names);
        adapter = new MyRecyclerViewAdapter(prices, dates, names, this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        RecyclerView subscriptionsView = findViewById(R.id.subscriptionRecycler);
        dbConnector.getAllSubscriptions(User.username, monthlyPrices, dueDates, subscriptionNames);
        subscriptionsAdapter = new SubscriptionsRecyclerViewAdapter(monthlyPrices, dueDates, subscriptionNames, this);
        subscriptionsView.setAdapter(subscriptionsAdapter);
        subscriptionsView.setLayoutManager(new LinearLayoutManager(this));

        hello = findViewById(R.id.helloLabel);
        remaining = findViewById(R.id.remainingBalance);
        budget = findViewById(R.id.budget);

        hello.setText("Hello, " + User.fname + "!");
        remaining.setText(String.format("$%.2f", (User.budget - User.spent)));
        budget.setText(String.format("of $%.2f Remaining", User.budget));

        averageExpenses = findViewById(R.id.averageExpenseButton);
        averageExpenses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent z = new Intent(averageExpenses.getContext(), Average.class);
                startActivity(z);
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
                Intent i = new Intent(addExpense.getContext(), Purchase.class);
                startActivity(i);
            }
        });
    }
}