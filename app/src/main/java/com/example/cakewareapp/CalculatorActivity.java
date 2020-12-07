package com.example.cakewareapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

public class CalculatorActivity extends AppCompatActivity {
    Spinner dropdown1,dropdown2;
    Button calcostbtn;
    TextView dcc;
    String product,qty;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);

        //get the spinner from the xml.
        dropdown1 = (Spinner) findViewById(R.id.spinner);
        dropdown2 = (Spinner) findViewById(R.id.spinner2);
        calcostbtn = (Button) findViewById(R.id.calcostbtn);
        dcc = (TextView) findViewById(R.id.dcc);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, getResources().getStringArray(R.array.spin1));
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dropdown1.setAdapter(adapter);

        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, getResources().getStringArray(R.array.spin2));
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dropdown2.setAdapter(adapter2);


        dropdown1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                product = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        dropdown2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                qty = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });




        calcostbtn.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View v) {
                int cost = 0;
                if(product.equals("Cocoa Powder 80/kg")){
                    cost = (80*Integer.parseInt(qty))/1000;
                } else if(product.equals("Dark Chocolate Compound 1000/kg")){
                    cost = (1000*Integer.parseInt(qty))/1000;
                } else if(product.equals("Baking Powder 40/kg")){
                    cost = (40*Integer.parseInt(qty))/1000;
                }
                dcc.setText(Integer.toString(cost));
            }
        });
    }

/*    DON'T CHANGE ANYTHING BELOW THIS LINE   */

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
        redirectActivity(this, ChatbotActivity.class);
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