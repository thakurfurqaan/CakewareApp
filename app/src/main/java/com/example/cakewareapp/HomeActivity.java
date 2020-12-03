package com.example.cakewareapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.cakewareapp.Model.Users;
import com.example.cakewareapp.Prevalent.Prevalent;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import io.paperdb.Paper;

public class HomeActivity extends AppCompatActivity {
    private String parentDbName = "Users";
    private Button LogoutBtn;
    private TextView NavUsernameTitle, UsernameTitle, NavEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        UsernameTitle = (TextView) findViewById(R.id.username_title);
        NavUsernameTitle = (TextView) findViewById(R.id.nav_username_title);
        NavEmail = (TextView) findViewById(R.id.nav_email);


//        NavUsernameTitle.setText("Hahaha");

        displayAccountDetails(Paper.book().read(Prevalent.UserNameKey));

        LogoutBtn = (Button) findViewById(R.id.logout_btn);
        LogoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Paper.book().destroy();

                Intent intent = new Intent(HomeActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
    }

    private void displayAccountDetails(String name) {
        final DatabaseReference RootRef;
        RootRef = FirebaseDatabase.getInstance().getReference();
        RootRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.child(parentDbName).child(name).exists()) {
                    Users usersData = dataSnapshot.child(parentDbName).child(name).getValue(Users.class);
                    UsernameTitle.setText(usersData.getName());
//                    NavUsernameTitle.setText(usersData.getName());
//                    NavEmail.setText(usersData.getEmail());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }

    private static void redirectActivity(Activity activity, Class aClass){
        Intent intent = new Intent(activity, aClass);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        activity.startActivity(intent);
    }

    public void ClickHome(MenuItem item) {
        recreate();
    }
    public void ClickAccount(MenuItem item) {
        redirectActivity(this, AccountActivity.class);
    }
    public void ClickLocation(MenuItem item) {
        redirectActivity(this, HomeActivity.class);
    }
    public void ClickOrder(MenuItem item) {
        redirectActivity(this, HomeActivity.class);
    }
    public void ClickCalculate(MenuItem item) {
        redirectActivity(this, HomeActivity.class);
    }
    public void ClickAddress(MenuItem item) {
        redirectActivity(this, HomeActivity.class);
    }
    public void ClickChatbot(MenuItem item) {
        redirectActivity(this, HomeActivity.class);
    }
    public void ClickLogout(MenuItem item) {
        redirectActivity(this, LoginActivity.class);
    }
    public void ClickExit(MenuItem item) {
        exitApp(this);
    }
    public static void exitApp(Activity activity){
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setTitle("Exit");
        builder.setMessage("Are you sure you want to exit?");
        builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                activity.finishAffinity();
                System.exit(0);
            }
        });
        builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.show();
    }

    public void ClickMenu(View view) {
        DrawerLayout drawerLayout = findViewById(R.id.drawer_layout);
        if (!drawerLayout.isDrawerOpen((GravityCompat.START))){
            drawerLayout.openDrawer(GravityCompat.START);
        }
    }


}