package com.food.clicktofood;//package bd.ctgshop.com.ctgshop;
//
import android.app.AlertDialog;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.ProgressDialog;
import android.app.Service;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Handler;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import com.food.clicktofood.Adapter.LocationTrackerEnabled;
import com.food.clicktofood.Adapter.ServiceStart;
import com.food.clicktofood.Model.LoginResponse;
import com.food.clicktofood.Retrofit.APIInterface;
import com.food.clicktofood.Retrofit.ApiUtils;
import com.food.clicktofood.SessionData.SessionData;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
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
    ProgressDialog dialog;
    private CompositeDisposable mCompositeDisposable;
    ProgressDialog loadingDialog;
    APIInterface apiInterface;
    SessionData sessionData;
    private LocationTrackerEnabled locationTrackerEnabled;
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
            //locationTrackerEnabled = (LocationTrackerEnabled) MyService.this;
        }
        sessionData = new SessionData(getApplicationContext());
        mCompositeDisposable = new CompositeDisposable();
        apiInterface = ApiUtils.getService();

        // Toast.makeText(getApplicationContext(), "Oncreate", Toast.LENGTH_LONG).show();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        //Toast.makeText(getApplicationContext(), "onStartCommand", Toast.LENGTH_LONG).show();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            startForeground(1, notification);
        } else {
        }

        start();
          runnable = new Runnable() {
            @Override
            public void run() {
                //postLogin();
                sendMessage();
                //Toast.makeText(getApplicationContext(), "Called", Toast.LENGTH_LONG).show();
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

    public void postLogin(){

        if(isNetworkAvailable()){
            //dialog = ProgressDialog.show(getApplicationContext(), "", "Signing in. Please wait.....", true);
            mCompositeDisposable.add(apiInterface.postLogin(sessionData.getUserDataModel().getData().getMember().get(0).getEmail(),sessionData.getUserDataModel().getData().getMember().get(0).getPhoneNo(),"88991",sessionData.getUserDataModel().getData().getMember().get(0).getFirebaseToken()) //
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(this::handleResponsePromo, this::handleErrorPromo));
        }else{
            Toast.makeText(getApplicationContext(), "Please check your internet connection and try again", Toast.LENGTH_LONG).show();
        }
    }


    private void handleResponsePromo(LoginResponse clientResponse) {
        //dialog.dismiss();
        if(clientResponse.getIsSuccess()){
            sessionData.setUserDataModel(clientResponse);
            Log.d("Azad", "Login response success "+clientResponse.getData().getMember().get(0).getFullName());
            //startActivity(new Intent(getApplicationContext(), AfterLoginActivity.class));

        }else{
            Toast.makeText(getApplicationContext(), clientResponse.getMessage(), Toast.LENGTH_LONG).show();
        }

    }

    private void handleErrorPromo(Throwable error) {
        //dialog.dismiss();
        Toast.makeText(getApplicationContext(), "Something went wrong, please try again", Toast.LENGTH_SHORT).show();
    }

    private boolean isNetworkAvailable(){

        ConnectivityManager cm = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE); // from arman
        NetworkInfo netinfo = cm.getActiveNetworkInfo();

        if (netinfo != null && netinfo.isConnectedOrConnecting()) {
            android.net.NetworkInfo wifi = cm.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
            android.net.NetworkInfo mobile = cm.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);

            if((mobile != null && mobile.isConnectedOrConnecting()) || (wifi != null && wifi.isConnectedOrConnecting())) return true;
            else return false;
        } else return false;
    }

    public void stop() {
       // Toast.makeText(getApplicationContext(), "Stopped", Toast.LENGTH_LONG).show();
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
        Intent intent = new Intent("my-event-location-track");
        // add data
        intent.putExtra("locationTrack", "off");
        LocalBroadcastManager.getInstance(this).sendBroadcast(intent);

       // Toast.makeText(getApplicationContext(), "OnDestroy", Toast.LENGTH_LONG).show();
        super.onDestroy();
    }

    @Override
    public IBinder onBind(Intent intent) {
        //Toast.makeText(getApplicationContext(), "onBind", Toast.LENGTH_LONG).show();
        return null;
    }

    public void setCallbacks(LocationTrackerEnabled callbacks) {
        locationTrackerEnabled = callbacks;
    }

    private void sendMessage() {
        Intent intent = new Intent("my-event-location-track");
        // add data
        intent.putExtra("locationTrack", "location");
        LocalBroadcastManager.getInstance(this).sendBroadcast(intent);
    }
}
