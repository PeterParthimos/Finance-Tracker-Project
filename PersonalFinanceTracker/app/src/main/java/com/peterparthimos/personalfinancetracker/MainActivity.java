package com.peterparthimos.personalfinancetracker;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    Button login, register;
    EditText username, password;
    DBConnector dbConnector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        username = findViewById(R.id.usernameLogin);
        password = findViewById(R.id.passwordLogin);

        dbConnector = new DBConnector(this);

        register = findViewById(R.id.registerButtonLogin);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(register.getContext(), Register.class);
                startActivity(i);
            }
        });

        login = findViewById(R.id.loginButton);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = username.getText().toString();
                String pass = password.getText().toString();
                if (!user.equals("") && !pass.equals("")) {
                    Cursor account = dbConnector.login(user, pass);
                    if (account.getCount() == 0) {
                        Toast.makeText(login.getContext(), "Invalid Login!", Toast.LENGTH_LONG).show();
                    } else {
                        Calendar c = Calendar.getInstance();
                        int dayOfMonth = c.get(Calendar.DAY_OF_MONTH);
                        account.moveToNext();
                        if (dayOfMonth == 1) {
                            User.username = account.getString(0);
                            User.fname = account.getString(2);
                            User.lname = account.getString(3);
                            User.budget = Double.parseDouble(account.getString(4));
                            User.spent = 0.0;
                            double spent = Double.parseDouble(account.getString(5));
                            double preAvg = Double.parseDouble(account.getString(6));
                            User.average = ((spent + preAvg) / 2);
                            dbConnector.editUser(User.username, User.average);
                        } else {
                            User.username = account.getString(0);
                            User.fname = account.getString(2);
                            User.lname = account.getString(3);
                            User.budget = Double.parseDouble(account.getString(4));
                            User.spent = Double.parseDouble(account.getString(5));
                            User.average = Double.parseDouble(account.getString(6));
                        }
                        Intent x = new Intent(login.getContext(), Dashboard.class);
                        startActivity(x);
                    }
                }

            }
        });
    }
}