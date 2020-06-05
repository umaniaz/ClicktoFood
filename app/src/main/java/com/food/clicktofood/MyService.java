package com.food.clicktofood;//package bd.ctgshop.com.ctgshop;
//
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Handler;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.schedulers.Timed;

import static androidx.constraintlayout.widget.Constraints.TAG;
import static java.util.concurrent.TimeUnit.SECONDS;

public class MyService extends Service {
    private ScheduledFuture mHandle;
    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
    Notification notification;
    private boolean started = false;
    private Handler handler = new Handler();
    Runnable runnable;
    @Override
    public void onCreate() {
        if (Build.VERSION.SDK_INT >= 26) {
            String CHANNEL_ID = "my_channel_01";
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID,
                    "Channel human readable title",
                    NotificationManager.IMPORTANCE_DEFAULT);

            ((NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE)).createNotificationChannel(channel);

             notification = new NotificationCompat.Builder(this, CHANNEL_ID)
                    .setContentTitle("")
                    .setContentText("").build();
            startForeground(1, notification);

        }
       // Toast.makeText(getApplicationContext(), "Oncreate", Toast.LENGTH_LONG).show();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Toast.makeText(getApplicationContext(), "onStartCommand", Toast.LENGTH_LONG).show();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            startForeground(1, notification);
        } else {
        }

        start();
          runnable = new Runnable() {
            @Override
            public void run() {
                Toast.makeText(getApplicationContext(), "Called", Toast.LENGTH_LONG).show();
                if(started) {
                    start();
                }
            }
        };
        return START_STICKY;


        //-=================== for stopping after 5 seconds =====================
//        final Runnable deleteIt = new Runnable() {
//            public void run() {
//                mHandle.cancel(false); //don't cancel here if you want it to run every 24 hours
//                stopSelf();
//            }
//        };
//        if(mHandle == null)
//            mHandle = scheduler.scheduleAtFixedRate(deleteIt, 10, 10, SECONDS);
        //========================================================================

    }

    public void stop() {
        Toast.makeText(getApplicationContext(), "Stopped", Toast.LENGTH_LONG).show();
        started = false;
        handler.removeCallbacks(runnable);
    }

    public void start() {
        started = true;
        handler.postDelayed(runnable, 6000);
    }

    @Override
    public void onDestroy() {
        stop();
        Toast.makeText(getApplicationContext(), "OnDestroy", Toast.LENGTH_LONG).show();
        super.onDestroy();
    }

    @Override
    public IBinder onBind(Intent intent) {
        Toast.makeText(getApplicationContext(), "onBind", Toast.LENGTH_LONG).show();
        return null;
    }
}
