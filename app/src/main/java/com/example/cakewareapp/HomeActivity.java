package com.example.cakewareapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.cakewareapp.Model.Users;
import com.example.cakewareapp.Prevalent.Prevalent;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

import io.paperdb.Paper;

public class HomeActivity extends AppCompatActivity {

    private Button LogoutBtn, ChangeAccountDetailsBtn;
    private TextView NavUsernameTitle, UsernameTitle, NavEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        UsernameTitle = (TextView) findViewById(R.id.username_title);
        UsernameTitle.setText(Paper.book().read(Prevalent.UserNameKey));

//        NavUsernameTitle = (TextView) findViewById(R.id.nav_username_title);
//        NavUsernameTitle.setText(Paper.book().read(Prevalent.UserNameKey));
//
//        NavEmail = (TextView) findViewById(R.id.nav_email);
//        NavEmail.setText(Paper.book().read(Prevalent.UserNameKey));

        LogoutBtn = (Button) findViewById(R.id.logout_btn);
        LogoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Paper.book().destroy();

                Intent intent = new Intent(HomeActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        ChangeAccountDetailsBtn = (Button) findViewById(R.id.change_account_details_btn);
        ChangeAccountDetailsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, AccountActivity.class);
                startActivity(intent);
            }
        });

    }
}