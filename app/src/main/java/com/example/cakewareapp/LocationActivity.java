package com.example.cakewareapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.os.Environment;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import com.example.cakewareapp.HomeActivity.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class LocationActivity extends AppCompatActivity {

    Button GetLocationBtn;
    FusedLocationProviderClient fusedLocationProviderClient;
    TextView DisplayLatitude, DisplayLongitude, DisplayCountry, DisplayLocality, DisplayAddress;

//    Button SaveLocationBtn, LoadLocationBtn;
//    TextView DisplayLoadAddress;
//    EditText InputAddress;
//    String MyAddress = "Dongri Charnull";
//    String fileName = "";
//    String filePath = "";
//    String fileContent = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);

        GetLocationBtn = (Button) findViewById(R.id.get_location_btn);
        DisplayLatitude = (TextView) findViewById(R.id.display_latitude);
        DisplayLongitude = (TextView) findViewById(R.id.display_longitude);
        DisplayCountry = (TextView) findViewById(R.id.display_country);
        DisplayLocality = (TextView) findViewById(R.id.display_locality);
        DisplayAddress = (TextView) findViewById(R.id.display_address);

        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);

        GetLocationBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ActivityCompat.checkSelfPermission(LocationActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                    getLocation();

                } else {
                    ActivityCompat.requestPermissions(LocationActivity.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 44);
                }
            }
        });


//        SaveLocationBtn = (Button) findViewById(R.id.save_location_btn);
//        LoadLocationBtn = (Button) findViewById(R.id.load_location_btn);
//        DisplayLoadAddress = (TextView) findViewById(R.id.display_load_address);
//        InputAddress = (EditText) findViewById(R.id.address_input);
//        fileName = "address.txt";
//        filePath = "AddressDir";
//
//        if (!isExternalStorageAvailable()) {
//            SaveLocationBtn.setEnabled(false);
//        }
//
//        SaveLocationBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                DisplayLoadAddress.setText("");
//                fileContent = InputAddress.getText().toString().trim();
//                if (!fileContent.equals("")) {
//                    File myExternalFile = new File(getExternalFilesDir(filePath), fileName);
//                    FileOutputStream fileOutputStream = null;
//                    try {
//                        fileOutputStream = new FileOutputStream(myExternalFile);
//                        fileOutputStream.write(fileContent.getBytes());
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
//                    Toast.makeText(LocationActivity.this, "Address has been saved successfully!", Toast.LENGTH_SHORT).show();
//                } else {
//                    Toast.makeText(LocationActivity.this, "Please enter your address!", Toast.LENGTH_SHORT).show();
//                }
//            }
//        });
//
//        LoadLocationBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                FileReader fileReader = null;
//                File file = new File(getExternalFilesDir(filePath), fileName);
//                StringBuilder stringBuilder = new StringBuilder();
//                try {
//                    fileReader = new FileReader(file);
//                    BufferedReader bufferedReader = new BufferedReader(fileReader);
//                    String line = bufferedReader.readLine();
//                    while (line != null){
//                        stringBuilder.append(line).append('\n');
//                        line = bufferedReader.readLine();
//                    }
//                } catch (IOException e) {
//                    e.printStackTrace();
//                } finally {
//                    String fileContent = "File content\n" + stringBuilder.toString();
//                    DisplayLoadAddress.setText(fileContent);
//                }
//            }
//        });

    }

    private void getLocation() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        fusedLocationProviderClient.getLastLocation().addOnCompleteListener(new OnCompleteListener<Location>() {
            @Override
            public void onComplete(@NonNull Task<Location> task) {
                Location location = task.getResult();
                if (location != null) {
                    Geocoder geocoder = new Geocoder(LocationActivity.this, Locale.getDefault());

                    try {
                        List<Address> addresses = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);
                        DisplayLatitude.setText(String.valueOf(addresses.get(0).getLatitude()));
                        DisplayLongitude.setText(String.valueOf(addresses.get(0).getLongitude()));
                        DisplayCountry.setText(String.valueOf(addresses.get(0).getCountryName()));
                        DisplayLocality.setText(String.valueOf(addresses.get(0).getLocality()));
                        DisplayAddress.setText(String.valueOf(addresses.get(0).getAddressLine(0)));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

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
        recreate();
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