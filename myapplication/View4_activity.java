package com.example.myapplication;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.content.ContextCompat;

public class View4_activity extends AppCompatActivity {

    Button buy_btn4;
    ImageButton view4_prev_btn4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view4);

        buy_btn4 = findViewById(R.id.buy_btn4);
        view4_prev_btn4 = findViewById(R.id.view4_back_arrow);
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.TIRAMISU){
            if(ContextCompat.checkSelfPermission(View4_activity.this,
                    android.Manifest.permission.POST_NOTIFICATIONS)!=
                    PackageManager.PERMISSION_GRANTED){
                ActivityCompat.requestPermissions(View4_activity.this,
                        new String[]{android.Manifest.permission.POST_NOTIFICATIONS},101);
            }
        }

        buy_btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(View4_activity.this, "Thanks for Adopting Me!!", Toast.LENGTH_SHORT).show();
                makeNotification();
            }
        });

        view4_prev_btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i1 = new Intent(View4_activity.this, Adore_Me_activity.class);
                startActivity(i1);
            }
        });
    }
    public void makeNotification(){
        //  auth=FirebaseAuth.getInstance();

        //    user=auth.getCurrentUser();


//            textView.setText(user.getEmail());

        String chanelID="CHANNEL_ID_NOTIFICATION";
        NotificationCompat.Builder builder=new NotificationCompat.Builder(getApplicationContext(),chanelID);
        builder.setSmallIcon(R.drawable.ic_notifications)
                .setContentTitle("Notification Title")

                //.setContentText(user.getEmail()+ "Payment successfull please visit again")
                .setContentText("Payment successfull please visit again")
                .setAutoCancel(true)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);
        Intent intent=new Intent(getApplicationContext(),NotificationActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.putExtra("data","Some value to be passes");

        PendingIntent pendingIntent=PendingIntent.getActivity(getApplicationContext(),0,intent,PendingIntent.FLAG_MUTABLE);
        builder.setContentIntent(pendingIntent);
        NotificationManager notificationManager=
                (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);

        if(android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            NotificationChannel notificationChannel=notificationManager.getNotificationChannel(chanelID);
            if(notificationChannel==null){
                int importance=NotificationManager.IMPORTANCE_HIGH;
                notificationChannel = new NotificationChannel(chanelID, "Channel Name", importance);
                notificationChannel.setLightColor(Color.GREEN);
                notificationChannel.enableVibration(true);
                notificationManager.createNotificationChannel(notificationChannel);
            }
        }
        notificationManager.notify(0,builder.build());
    }
}