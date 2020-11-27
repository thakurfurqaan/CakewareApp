package com.example.cakewareapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button rbtn = findViewById(R.id.registerbtn);
        rbtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                openRegisterForm();
            }
        });

    }
    public void openRegisterForm(){
        Intent intent = new Intent(this, Register1.class);
        startActivity(intent);
    }
}