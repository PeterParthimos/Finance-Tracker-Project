package com.peterparthimos.personalfinancetracker;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Purchase extends AppCompatActivity {
    DBConnector db;
    EditText name, amount, date;
    Button submit, cancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_purchase);

        name = findViewById(R.id.name);
        amount = findViewById(R.id.cost);
        date = findViewById(R.id.date);
        db = new DBConnector(this);

        submit = findViewById(R.id.submit);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String n = name.getText().toString();
                String a = amount.getText().toString();
                String d = date.getText().toString();

                if(!n.equals("") || !a.equals("") || !d.equals("")) {
                    int cost = Integer.parseInt(a);
                    RecentPurchases.username = User.username;
                    boolean isAdded = db.addPurchase(RecentPurchases.username, n, cost, d);
                    if(isAdded) {
                        Toast.makeText(submit.getContext(), "Successfully Added Payment!", Toast.LENGTH_LONG).show();
                        Intent i = new Intent(submit.getContext(), Dashboard.class);
                        startActivity(i);
                    } else {
                        Toast.makeText(submit.getContext(), "Something Went Wrong!", Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(submit.getContext(), "Please fill every box!", Toast.LENGTH_LONG).show();
                }
            }
        });

        cancel = findViewById(R.id.cancel);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(cancel.getContext(), Dashboard.class);
                startActivity(i);
            }
        });
    }
}
