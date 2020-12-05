package com.example.cakewareapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class NotificationActivity extends AppCompatActivity {
    Button placeorderbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);

        placeorderbtn = (Button) findViewById(R.id.placeorderbtn);

        placeorderbtn.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {
                String message = "We have received your order request and your order will be delivered to you shortly.";

                NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    String CHANNEL_ID = "my_channel_01";
                    CharSequence name = "my_channel";
                    int importance = NotificationManager.IMPORTANCE_HIGH;
                    NotificationChannel notificationChannel = new NotificationChannel(CHANNEL_ID, name, importance);
                    notificationManager.createNotificationChannel(notificationChannel);


                    NotificationCompat.Builder builder = new NotificationCompat.Builder(NotificationActivity.this, CHANNEL_ID).setSmallIcon(R.drawable.ic_message).setContentTitle("Order Placed Successfully!").setContentText(message).setAutoCancel(true);

                    Intent intent = new Intent(NotificationActivity.this,
                            HomeActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    //intent.putExtra("message",message)

                    PendingIntent pendingIntent = PendingIntent.getActivity(NotificationActivity.this,
                            0, intent, PendingIntent.FLAG_UPDATE_CURRENT);

                    builder.setContentIntent(pendingIntent);

                    notificationManager.notify(0, builder.build());
                }
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