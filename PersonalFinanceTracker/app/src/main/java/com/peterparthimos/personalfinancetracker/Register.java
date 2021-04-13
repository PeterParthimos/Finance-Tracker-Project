package com.peterparthimos.personalfinancetracker;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Register extends AppCompatActivity {
    Button register, cancel;
    EditText fname, lname, username, password;
    DBConnector dbConnector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        dbConnector = new DBConnector(this);

        fname = findViewById(R.id.firstName);
        lname = findViewById(R.id.lastName);
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);

        register = findViewById(R.id.registerButton);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String first = fname.getText().toString();
                String last = lname.getText().toString();
                String user = username.getText().toString();
                String pass = password.getText().toString();
                if (!first.equals("") && !last.equals("") && !user.equals("") && !pass.equals("")) {
                    boolean isRegistered = dbConnector.register(user, pass, first, last);
                    if (isRegistered) {
                        Toast.makeText(register.getContext(), "Successfully Registered!", Toast.LENGTH_LONG).show();
                        Intent i = new Intent(register.getContext(), MainActivity.class);
                        startActivity(i);
                    } else {
                        Toast.makeText(register.getContext(), "Something Went Wrong!", Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(register.getContext(), "Please fill every box!", Toast.LENGTH_LONG).show();
                }
            }
        });

        cancel = findViewById(R.id.cancelButton);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(cancel.getContext(), MainActivity.class);
                startActivity(i);
            }
        });
    }
}