package com.notexample.austin.notificationslab;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Animatable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.support.v4.os.CancellationSignal;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.NotificationCompat;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = (TextView) findViewById(R.id.textView2);
        textView.setText("Welcome to the connection checker!" + "\n" + "\n" + "Click the button below to see if you're connected or not!");
        textView.setTextSize(20);
    }

    public void checkingconnection(View view) {
        ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()) {
            Toast.makeText(getApplicationContext(), "Connection ready",
                    Toast.LENGTH_SHORT).show();

            textView = (TextView) findViewById(R.id.textView2);
            textView.setText("Internet connection working, turn off connection to see notification");


            Intent intent1 = new Intent(this, YouHaveConnection.class);

            PendingIntent pendingIntent1 = PendingIntent.getActivity(this, (int) System.currentTimeMillis(), intent1, 0);



            NotificationCompat.BigPictureStyle bigPictureStyle = new NotificationCompat.BigPictureStyle();
            bigPictureStyle.bigPicture(BitmapFactory.decodeResource(getResources(),R.drawable.connectiongoo)).build();

            NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(this);
            mBuilder.setSmallIcon(android.R.drawable.star_on);
            mBuilder.setContentTitle("Connection Good!");
            mBuilder.setContentText("Get SURFIN");
            mBuilder.setContentIntent(pendingIntent1);
            mBuilder.setPriority(Notification.PRIORITY_MAX);
            mBuilder.setStyle(bigPictureStyle);
            mBuilder.addAction(android.R.drawable.ic_menu_info_details, "click to see message", pendingIntent1);

            NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
            notificationManager.notify(1, bigPictureStyle.build());
            notificationManager.cancel(6);




        } else {
            Toast.makeText(getApplicationContext(), "Connection not ready",
                    Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, YouDontHaveConnection.class);
            Intent intent1 = new Intent(Intent.ACTION_DIAL);

            textView = (TextView) findViewById(R.id.textView2);
            textView.setText("Internet connection not working, turn on connection to see notification");

            PendingIntent pendingIntent = PendingIntent.getActivity(this, (int) System.currentTimeMillis(), intent, 0);
            PendingIntent pendingIntent1 = PendingIntent.getActivity(this, (int) System.currentTimeMillis(), intent1.setData(Uri.parse("tel:9111")), 0);



            NotificationCompat.BigPictureStyle bigPictureStyle = new NotificationCompat.BigPictureStyle();
            bigPictureStyle.bigPicture(BitmapFactory.decodeResource(getResources(),R.drawable.no)).build();

            NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(this);
            mBuilder.setSmallIcon(android.R.drawable.stat_sys_warning);
            mBuilder.setContentTitle("No internet connection!");
            mBuilder.setContentText("To use the app, please enable WIFI, Thanks!");
            mBuilder.setContentIntent(pendingIntent);
            mBuilder.setPriority(Notification.PRIORITY_MAX);
            mBuilder.setStyle(bigPictureStyle);
            mBuilder.addAction(android.R.drawable.ic_menu_call, "Make Emergency Call", pendingIntent1);

            NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
            notificationManager.notify(6, bigPictureStyle.build());
            notificationManager.cancel(1);


        }
    }
}
