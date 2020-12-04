package com.example.cakewareapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cakewareapp.Model.Users;
import com.example.cakewareapp.Prevalent.Prevalent;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

import io.paperdb.Paper;

public class AccountActivity extends AppCompatActivity {

    private String parentDbName = "Users";
    private String username = Paper.book().read(Prevalent.UserNameKey);
    private ProgressDialog loadingBar;
    private Button UpdateEmailBtn, UpdatePasswordBtn, RefreshBtn;
    private EditText ChangeEmailInput, ChangePasswordInput, ConfirmPasswordInput;

    TextView UsernameDis, EmailDis;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);

        UsernameDis = (TextView) findViewById(R.id.username_dis);
        EmailDis = (TextView) findViewById(R.id.email_dis);

        displayAccountDetails(Paper.book().read(Prevalent.UserNameKey));

        ChangeEmailInput = (EditText) findViewById(R.id.email_change_input);
        ChangePasswordInput = (EditText) findViewById(R.id.password_change_input);

        UpdateEmailBtn = (Button) findViewById(R.id.email_change_btn);
        UpdateEmailBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateEmail(username);
            }
        });

        UpdatePasswordBtn = (Button) findViewById(R.id.password_change_btn);
        UpdatePasswordBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updatePassword(username);
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
                    UsernameDis.setText(usersData.getName());
                    EmailDis.setText(usersData.getEmail());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }

    private void updateEmail(String name) {
        final DatabaseReference RootRef;
        RootRef = FirebaseDatabase.getInstance().getReference();
        RootRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                if (dataSnapshot.child(parentDbName).child(name).exists()) {

                    HashMap<String, Object> userdataMap = new HashMap<>();
                    userdataMap.put("email", ChangeEmailInput.getText().toString());

                    RootRef.child("Users").child(name).updateChildren(userdataMap)
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {
                                        Toast.makeText(AccountActivity.this, "Email Changed Successfully!", Toast.LENGTH_SHORT).show();
                                        displayAccountDetails(name);
                                    } else {
                                        Toast.makeText(AccountActivity.this, "Email Not Changed!", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(AccountActivity.this, "Email Not Changed!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void updatePassword(String name) {
        final DatabaseReference RootRef;
        RootRef = FirebaseDatabase.getInstance().getReference();
        RootRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                if (dataSnapshot.child(parentDbName).child(name).exists()) {

                    HashMap<String, Object> userdataMap = new HashMap<>();
                    userdataMap.put("password", ChangePasswordInput.getText().toString());

                    RootRef.child("Users").child(name).updateChildren(userdataMap)
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {
                                        Toast.makeText(AccountActivity.this, "Password Changed Successfully!", Toast.LENGTH_SHORT).show();
                                    } else {
                                        Toast.makeText(AccountActivity.this, "Password Not Changed!", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(AccountActivity.this, "Password Not Changed!", Toast.LENGTH_SHORT).show();
            }
        });
    }


    private static void redirectActivity(Activity activity, Class aClass) {
        Intent intent = new Intent(activity, aClass);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        activity.startActivity(intent);
    }

    public void ClickHome(MenuItem item) {
        redirectActivity(this, HomeActivity.class);
    }

    public void ClickAccount(MenuItem item) {
        redirectActivity(this, AccountActivity.class);
    }

    public void ClickLocation(MenuItem item) {
        redirectActivity(this, LocationActivity.class);
    }

    public void ClickOrder(MenuItem item) {
        redirectActivity(this, NotificationActivity.class);
    }

    public void ClickCalculate(MenuItem item) {
        redirectActivity(this, CalculatorActivity.class);
    }

    public void ClickAddress(MenuItem item) {
        redirectActivity(this, AddressActivity.class);
    }

    public void ClickChatbot(MenuItem item) {
        redirectActivity(this, ChatActivity.class);
    }

    public void ClickLogout(MenuItem item) {
        redirectActivity(this, LoginActivity.class);
    }

    public void ClickExit(MenuItem item) {
        exitApp(this);
    }

    public static void exitApp(Activity activity) {
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
        if (!drawerLayout.isDrawerOpen((GravityCompat.START))) {
            drawerLayout.openDrawer(GravityCompat.START);
        }
    }
}