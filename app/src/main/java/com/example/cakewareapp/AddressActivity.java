package com.example.cakewareapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;

public class AddressActivity extends AppCompatActivity {

    Button SaveLocationBtn, LoadLocationBtn;
    TextView DisplayLoadAddress;
    EditText InputAddress1, InputAddress2, InputAddressPincode, InputAddressCity, InputAddressState, InputAddressCountry;
    String fileName = "";
    String filePath = "";
    String fileContent = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address);

        SaveLocationBtn = (Button) findViewById(R.id.save_location_btn);
        LoadLocationBtn = (Button) findViewById(R.id.load_location_btn);
        DisplayLoadAddress = (TextView) findViewById(R.id.display_address);
        InputAddress1 = (EditText) findViewById(R.id.address1_input);
        InputAddress2 = (EditText) findViewById(R.id.address2_input);
        InputAddressPincode = (EditText) findViewById(R.id.address_pincode_input);
        InputAddressCity = (EditText) findViewById(R.id.address_city_input);
        InputAddressState = (EditText) findViewById(R.id.address_state_input);
        InputAddressCountry = (EditText) findViewById(R.id.address_country_input);
        fileName = "address.txt";
        filePath = "AddressDir";

        if (!isExternalStorageAvailable())
            SaveLocationBtn.setEnabled(false);


        if (ActivityCompat.checkSelfPermission(AddressActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED)
            ActivityCompat.requestPermissions(AddressActivity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 44);

        if (ActivityCompat.checkSelfPermission(AddressActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED)
            ActivityCompat.requestPermissions(AddressActivity.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 44);


        SaveLocationBtn.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(InputAddress1.getText().toString())) {
                    Toast.makeText(AddressActivity.this, "Please enter address line 1!", Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(InputAddress2.getText().toString())) {
                    Toast.makeText(AddressActivity.this, "Please enter address line 2!", Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(InputAddressPincode.getText().toString())) {
                    Toast.makeText(AddressActivity.this, "Please enter pincode!", Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(InputAddressCity.getText().toString())) {
                    Toast.makeText(AddressActivity.this, "Please enter the city!", Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(InputAddressState.getText().toString())) {
                    Toast.makeText(AddressActivity.this, "Please enter the state!", Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(InputAddressCountry.getText().toString())) {
                    Toast.makeText(AddressActivity.this, "Please enter the country!", Toast.LENGTH_SHORT).show();
                } else {
                    DisplayLoadAddress.setText("Address Saved at: " + getExternalFilesDir(filePath) + "/" + fileName);
                    String address = "Current Saved Address: \n"
                            + InputAddress1.getText().toString()
                            + ",\n" + InputAddress2.getText().toString()
                            + ",\n" + InputAddressCity.getText().toString()
                            + " - " + InputAddressPincode.getText().toString()
                            + ",\n" + InputAddressState.getText().toString()
                            + ", " + InputAddressCountry.getText().toString();
                    fileContent = address;
                    File myExternalFile = new File(getExternalFilesDir(filePath), fileName);
                    FileOutputStream fileOutputStream = null;
                    try {
                        fileOutputStream = new FileOutputStream(myExternalFile);
                        fileOutputStream.write(fileContent.getBytes());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    Toast.makeText(AddressActivity.this, "Address has been saved successfully!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        LoadLocationBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FileReader fileReader = null;
                File file = new File(getExternalFilesDir(filePath), fileName);
                StringBuilder stringBuilder = new StringBuilder();
                try {
                    fileReader = new FileReader(file);
                    BufferedReader bufferedReader = new BufferedReader(fileReader);
                    String line = bufferedReader.readLine();
                    while (line != null) {
                        stringBuilder.append(line).append('\n');
                        line = bufferedReader.readLine();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    String fileContent = stringBuilder.toString();
                    DisplayLoadAddress.setText(fileContent);
                }
            }
        });

    }

    private boolean isExternalStorageAvailable() {
        String extStorageState = Environment.getExternalStorageState();
        return extStorageState.equals(Environment.MEDIA_MOUNTED);
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
        redirectActivity(this, HomeActivity.class);
    }

    public void ClickCalculate(MenuItem item) {
        redirectActivity(this, HomeActivity.class);
    }

    public void ClickAddress(MenuItem item) {
        redirectActivity(this, AddressActivity.class);
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