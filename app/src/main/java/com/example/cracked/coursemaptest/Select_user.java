package com.example.cracked.coursemaptest;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class Select_user extends AppCompatActivity {


    //widget
    private Button mHost,mGuest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_user);

        mHost = (Button)findViewById(R.id.host);
        mGuest = (Button)findViewById(R.id.guest);

        mHost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent host_details = new Intent(Select_user.this,Host_details.class);
                startActivity(host_details);
            }
        });

        mGuest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent guest_details = new Intent(Select_user.this,Guest_details.class);
                startActivity(guest_details);
            }
        });

    }
}
