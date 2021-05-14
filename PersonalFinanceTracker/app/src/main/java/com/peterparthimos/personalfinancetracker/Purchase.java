package com.peterparthimos.personalfinancetracker;

import android.app.DatePickerDialog;
import android.app.DialogFragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.text.DateFormat;
import java.util.Calendar;

public class Purchase extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {
    DBConnector db;
    EditText name, amount;
    TextView dateText;
    Button submit, cancel, dateButton;
    Switch isSubscription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_purchase);

        name = findViewById(R.id.name);
        amount = findViewById(R.id.cost);
        dateText = findViewById(R.id.dateText);
        db = new DBConnector(this);
        submit = findViewById(R.id.submit);
        dateButton = findViewById(R.id.dateButton);
        isSubscription = findViewById(R.id.isSubscription);

        dateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment datePicker = new DatePickerFragment();
                datePicker.show(getFragmentManager(), "date picker");
            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String n = name.getText().toString();
                String a = amount.getText().toString();
                String d = dateText.getText().toString();

                boolean isSet = (!n.equals("") || !a.equals("") || !d.equals(""));

                if (isSubscription.isChecked()) {
                    if (isSet) {
                        double cost = Double.parseDouble(a);
                        String[] dates = d.split("/");
                        int day = Integer.parseInt(dates[1]);
                        boolean isAdded = db.addSubscription(User.username, n, cost, day);
                        if (isAdded) {
                            User.spent += cost;
                            db.updateSpent(User.username, User.spent, cost);
                            Toast.makeText(submit.getContext(), "Successfully Added Purchase!", Toast.LENGTH_LONG).show();
                            Intent i = new Intent(submit.getContext(), Dashboard.class);
                            startActivity(i);
                        } else {
                            Toast.makeText(submit.getContext(), "Something Went Wrong!", Toast.LENGTH_LONG).show();
                        }
                    } else {
                        Toast.makeText(submit.getContext(), "Please fill every box!", Toast.LENGTH_LONG).show();
                    }
                } else {
                    if (isSet) {
                        double cost = Double.parseDouble(a);
                        boolean isAdded = db.addPurchase(User.username, n, cost, d);
                        if (isAdded) {
                            User.spent += cost;
                            db.updateSpent(User.username, User.spent, cost);
                            Toast.makeText(submit.getContext(), "Successfully Added Purchase!", Toast.LENGTH_LONG).show();
                            Intent i = new Intent(submit.getContext(), Dashboard.class);
                            startActivity(i);
                        } else {
                            Toast.makeText(submit.getContext(), "Something Went Wrong!", Toast.LENGTH_LONG).show();
                        }
                    } else {
                        Toast.makeText(submit.getContext(), "Please fill every box!", Toast.LENGTH_LONG).show();
                    }
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

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, year);
        c.set(Calendar.MONTH, month);
        c.set(Calendar.DAY_OF_MONTH, dayOfMonth);
        String currentDate = DateFormat.getDateInstance(DateFormat.SHORT).format(c.getTime());
        dateText.setText(currentDate);
    }
}