package com.project.laundryappui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class LogoutActivity extends AppCompatActivity {

    SessionManager sessionManager;
    Button BTNlogout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logout);



        BTNlogout = findViewById(R.id.BTNlogout);

        sessionManager = new SessionManager(getApplicationContext());

        BTNlogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sessionManager.logOut();
                Toast.makeText(LogoutActivity.this, "Berhasil Logout", Toast.LENGTH_SHORT).show();
            }
        });
    }
}