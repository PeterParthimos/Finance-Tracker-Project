package com.peterparthimos.personalfinancetracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

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
                        account.moveToNext();
                        User.username = account.getString(0);
                        User.fname = account.getString(2);
                        User.lname = account.getString(3);
                        User.budget = Integer.parseInt(account.getString(4));
                        User.spent = Integer.parseInt(account.getString(5));
                        User.average = Integer.parseInt(account.getString(6));
                        Intent x = new Intent(login.getContext(), Dashboard.class);
                        startActivity(x);
                    }
                }

            }
        });
    }
}