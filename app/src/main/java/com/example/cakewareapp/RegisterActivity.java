package com.example.cakewareapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {

    private EditText InputName;
    private EditText InputPassword;
    private ProgressDialog loadingBar;
    private EditText InputEmail;
    private EditText InputRePassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
//        Button createAccountButton = (Button) findViewById(R.id.register_btn);
        InputName = (EditText) findViewById(R.id.register_username_input);
        InputEmail = (EditText) findViewById(R.id.register_email_input);
        InputPassword = (EditText) findViewById(R.id.register_password_input);
        InputRePassword = (EditText) findViewById(R.id.register_repassword_input);
//        InputPersonal = (EditText) findViewById(R.id.personal_acc_input);
        loadingBar = new ProgressDialog(this);

//        createAccountButton.setOnClickListener(new View.OnClickListener() {
////            @Override
//            public void onClick(View view)
//            {
//                CreateAccount();
//            }
//        });
        Button home_page_open_button = (Button) findViewById(R.id.register_btn);
        home_page_open_button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                openHomepage();
            }
        });

    }
    public void openHomepage(){
        Intent intent = new Intent(this, HomepageActivity.class);
        startActivity(intent);
    }

    private void CreateAccount()
    {
        String name = InputName.getText().toString();
        String phone = InputEmail.getText().toString();
        String password = InputPassword.getText().toString();

        if (TextUtils.isEmpty(name))
        {
            Toast.makeText(this, "Please write your name...", Toast.LENGTH_SHORT).show();
        }
        else if (TextUtils.isEmpty(phone))
        {
            Toast.makeText(this, "Please write your phone number...", Toast.LENGTH_SHORT).show();
        }
        else if (TextUtils.isEmpty(password))
        {
            Toast.makeText(this, "Please write your password...", Toast.LENGTH_SHORT).show();
        }
        else
        {
            loadingBar.setTitle("Create Account");
            loadingBar.setMessage("Please wait, while we are checking the credentials.");
            loadingBar.setCanceledOnTouchOutside(false);
            loadingBar.show();

//            ValidateEmail(name, email, password);
        }
    }



//    private void ValidateEmail(final String name, final String email, final String password)
//    {
//        final DatabaseReference RootRef;
//        RootRef = FirebaseDatabase.getInstance().getReference();
//
//        RootRef.addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot)
//            {
//                if (!(dataSnapshot.child("Users").child(phone).exists()))
//                {
//                    HashMap<String, Object> userdataMap = new HashMap<>();
//                    userdataMap.put("phone", phone);
//                    userdataMap.put("password", password);
//                    userdataMap.put("name", name);
//
//                    RootRef.child("Users").child(phone).updateChildren(userdataMap)
//                            .addOnCompleteListener(new OnCompleteListener<Void>() {
//                                @Override
//                                public void onComplete(@NonNull Task<Void> task)
//                                {
//                                    if (task.isSuccessful())
//                                    {
//                                        Toast.makeText(RegisterActivity.this, "Congratulations, your account has been created.", Toast.LENGTH_SHORT).show();
//                                        loadingBar.dismiss();
//
//                                        Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
//                                        startActivity(intent);
//                                    }
//                                    else
//                                    {
//                                        loadingBar.dismiss();
//                                        Toast.makeText(RegisterActivity.this, "Network Error: Please try again after some time...", Toast.LENGTH_SHORT).show();
//                                    }
//                                }
//                            });
//                }
//                else
//                {
//                    Toast.makeText(RegisterActivity.this, "This " + phone + " already exists.", Toast.LENGTH_SHORT).show();
//                    loadingBar.dismiss();
//                    Toast.makeText(RegisterActivity.this, "Please try again using another phone number.", Toast.LENGTH_SHORT).show();
//
//                    Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
//                    startActivity(intent);
//                }
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//
//            }
//        });
//    }
}
