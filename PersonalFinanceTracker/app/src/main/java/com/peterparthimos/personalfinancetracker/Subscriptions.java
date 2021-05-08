package com.peterparthimos.personalfinancetracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Subscriptions extends AppCompatActivity {
    DBConnector db;
    EditText name, amount, renewDate;
    Button submit, cancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subscriptions);

        name = findViewById(R.id.name);
        amount = findViewById(R.id.renewAmount);
        renewDate = findViewById(R.id.renewDate);
        db = new DBConnector(this);

        submit = findViewById(R.id.submit);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String n = name.getText().toString();
                String a = amount.getText().toString();
                String d = renewDate.getText().toString();

                if(!n.equals("") || !a.equals("") || !d.equals("")) {
                    int cost = Integer.parseInt(a);
                    boolean isAdded = db.addSubscription(User.username, n, cost, d);
                    if(isAdded) {
                        Toast.makeText(submit.getContext(), "Successfully Added Subscription!",
                                Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(submit.getContext(), Dashboard.class);
                        startActivity(i);
                    } else {
                        Toast.makeText(submit.getContext(), "Something went wrong!",
                                Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(submit.getContext(), "Please fill in every box!",
                            Toast.LENGTH_SHORT).show();
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