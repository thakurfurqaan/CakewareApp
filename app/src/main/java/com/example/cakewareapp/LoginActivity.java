package com.example.cakewareapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.example.cakewareapp.Model.Users;
import com.example.cakewareapp.Prevalent.Prevalent;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import io.paperdb.Paper;

public class LoginActivity extends AppCompatActivity {
    private Button login_btn;
    private Button register_btn;
    private EditText InputName, InputPassword;
    private ProgressDialog loadingBar;

    private CheckBox RememberMe;

    private String parentDbName = "Users";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        RememberMe = (CheckBox) findViewById(R.id.remember_me);
        Paper.init(this);

        String UserNameKey = Paper.book().read(Prevalent.UserNameKey);
        String UserPasswordKey = Paper.book().read(Prevalent.UserPasswordKey);

//        if(UserNameKey != "" && UserPasswordKey != ""){
//            if (!TextUtils.isEmpty(UserNameKey) && !TextUtils.isEmpty(UserPasswordKey)) {
//
//                AllowAccessToAccount(UserNameKey, UserPasswordKey);
//
//                loadingBar.setTitle("Logging In");
//                loadingBar.setMessage("Checking your credentials...");
//                loadingBar.setCanceledOnTouchOutside(false);
//                loadingBar.show();
//            }
//        }


        register_btn = (Button) findViewById(R.id.registerbtn);
        register_btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                openRegisterForm();
            }
        });

        InputName = (EditText) findViewById(R.id.input_email);
        InputPassword = (EditText) findViewById(R.id.input_password);
        loadingBar = new ProgressDialog(this);
        login_btn = (Button) findViewById(R.id.loginbtn);
        login_btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Login();
            }
        });

    }

    public void openRegisterForm(){
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
    }
    public void Login(){
        String name = InputName.getText().toString();
        String password = InputPassword.getText().toString();

        if (TextUtils.isEmpty(name))
        {
            Toast.makeText(this, "Please enter your username!", Toast.LENGTH_SHORT).show();
        }
        else if (TextUtils.isEmpty(password))
        {
            Toast.makeText(this, "Please enter your password!", Toast.LENGTH_SHORT).show();
        }
        else{
            loadingBar.setTitle("Login");
            loadingBar.setMessage("Checking your credentials...");
            loadingBar.setCanceledOnTouchOutside(false);
            loadingBar.show();

            Paper.book().write(Prevalent.UserNameKey, name);
            Paper.book().write(Prevalent.UserPasswordKey, password);

            AllowAccessToAccount(name, password);
        }
    }

    private void AllowAccessToAccount(String name, String password){

        final DatabaseReference RootRef;
        RootRef = FirebaseDatabase.getInstance().getReference();


        RootRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.child(parentDbName).child(name).exists()) {
                    Users usersData = dataSnapshot.child(parentDbName).child(name).getValue(Users.class);

                    if(usersData.getName().equals(name)){
                        if(usersData.getPassword().equals(password)){
                            Toast.makeText(LoginActivity.this, "Logged in Successfully!", Toast.LENGTH_SHORT).show();
                            loadingBar.dismiss();
                            Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                            startActivity(intent);
                        }
                        else{
                            loadingBar.dismiss();
                            Toast.makeText(LoginActivity.this, "Invalid Credentials!", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
                else{
                    Toast.makeText(LoginActivity.this, "Account does not exist!", Toast.LENGTH_SHORT).show();
                    Toast.makeText(LoginActivity.this, "Please Register!", Toast.LENGTH_SHORT).show();
                    loadingBar.dismiss();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
}