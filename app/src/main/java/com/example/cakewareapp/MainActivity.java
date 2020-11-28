package com.example.cakewareapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.content.Intent;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button register_page_open_button = (Button) findViewById(R.id.registerbtn);
        register_page_open_button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                openRegisterForm();
            }
        });

    }
    public void openRegisterForm(){
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
    }
}