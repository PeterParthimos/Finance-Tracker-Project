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
/*

    THIS IS WHAT WAS IN THE MAIN ACTIVITY
    ALL IT DID FOR NOW IS CREATE A NOTIFICATION 2 SECONDS AFTER PRESSING THE BUTTON.

    THE ALARM MANAGER ALLOWS YOUR APPLICATION TO DO SOMETHING IN THE FUTURE (EVEN IF IT'S CLOSED
    IF I UNDERSTAND CORRECTLY)

    THIS COULD BE USED TO CALCULATE MONTHLY AVERAGES OR FOR NOTIFICATIONS.

    THE NOTIFICATION CHANNEL METHOD IS REQUIRED TO DISPLAY NOTIFICATIONS IN OUR VERSION OF ANDROID


    RESOURCES TO HELP UNDERSTAND:
    https://www.youtube.com/watch?v=nl-dheVpt8o&ab_channel=LemubitAcademy
    https://proandroiddev.com/android-alarmmanager-as-deep-as-possible-909bd5b64792


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        createNotificationChannel();

        Button button = findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Reminder Set!", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(MainActivity.this, ReminderBroadcast.class);
                PendingIntent pendingIntent = PendingIntent.getBroadcast(MainActivity.this, 0, intent, 0);

                AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);

                long timeAtButtonClick = System.currentTimeMillis();

                long twoSecondsMillis = 1000 * 2;

                alarmManager.set(AlarmManager.RTC_WAKEUP, timeAtButtonClick + twoSecondsMillis, pendingIntent);
            }
        });
    }

    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel("Notification",
                    "ReminderChannel", NotificationManager.IMPORTANCE_DEFAULT);
            channel.setDescription("Channel for reminder");

            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }

    }


 */

}