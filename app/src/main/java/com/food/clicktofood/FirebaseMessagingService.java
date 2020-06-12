package com.food.clicktofood;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.media.AudioAttributes;
import android.media.MediaPlayer;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Vibrator;
import android.util.Log;

import androidx.core.app.NotificationCompat;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import com.google.firebase.messaging.RemoteMessage;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import io.opencensus.internal.Utils;

//import com.bumptech.glide.request.animation.GlideAnimation;

/**
 * Created by Royex Technologies on 2/26/2017.
 */

public class FirebaseMessagingService extends com.google.firebase.messaging.FirebaseMessagingService {

    private final String TAG = "c2f_"+this.getClass().getSimpleName();

    private NotificationCompat.Builder mBuilder;
    private String mTitle,mDescription;
    LocalBroadcastManager broadcaster;
    public static String TestContent = "Sohan";
    String CHANNEL_ID="1234";

    @Override
    public void onNewToken(String s) {
        super.onNewToken(s);
        Log.d("NEW_TOKEN",s);
    }


//    @Override
//    public void onMessageReceived(RemoteMessage remoteMessage) {
//        Log.e(TAG,"data received = "+remoteMessage.getData().toString());
//
//        if(!remoteMessage.getData().toString().contains("{custom=")){
//            if(remoteMessage.getData().size() > 0){
//                showNotification(remoteMessage.getData().get("title"),
//                        remoteMessage.getData().get("body"),
//                        remoteMessage.getData().get("icon"),
//                        remoteMessage.getData().get("image"),
//                        remoteMessage.getData().get("customlink"));
//
//            }
//        }
////        Log.d(TAG,"noti received body "+remoteMessage.getNotification().getBody().toString()+
////        " title "+remoteMessage.getNotification().getTitle().toString()+
////        " icon "+remoteMessage.getNotification().getIcon().toString());
//        String cLink= "";
//    }

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);
        Log.d(TAG, "onMessageReceived: " + remoteMessage);

        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_ONE_SHOT);
        String channelId = "Default";
        Uri soundUri = Uri.parse("android.resource://com.food.clicktofood/raw/can_we_kiss_forever");
        Log.d(TAG, "uri "+soundUri);

        //==================== new / test ===================================

        NotificationManager mNotificationManager = (NotificationManager) getApplicationContext().getSystemService(Context.NOTIFICATION_SERVICE);

        //For API 26+ you need to put some additional code like below:
        NotificationChannel mChannel;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            mChannel = new NotificationChannel(CHANNEL_ID, "Test", NotificationManager.IMPORTANCE_HIGH);
            mChannel.setLightColor(Color.GRAY);
            mChannel.enableLights(true);
            mChannel.enableVibration(true);
            mChannel.setVibrationPattern(new long[]{ 0 });
            //mChannel.setDescription(Utils.CHANNEL_SIREN_DESCRIPTION);
            AudioAttributes audioAttributes = new AudioAttributes.Builder()
                    .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                    .setUsage(AudioAttributes.USAGE_NOTIFICATION)
                    .build();
            mChannel.setSound(soundUri, audioAttributes);

            if (mNotificationManager != null) {
                mNotificationManager.createNotificationChannel( mChannel );
            }
        }
        //else {

//            NotificationCompat.Builder status = new NotificationCompat.Builder(getApplicationContext(), CHANNEL_ID);
//            status.setAutoCancel(true)
//                    .setWhen(System.currentTimeMillis())
//                    .setSmallIcon(R.mipmap.ic_launcher)
//                    //.setOnlyAlertOnce(true)
//                    .setContentTitle(remoteMessage.getNotification().getTitle())
//                    .setContentText(remoteMessage.getNotification().getBody()).setAutoCancel(true).setContentIntent(pendingIntent)
//                    //.setContentText(messageBody)
//                    .setVibrate(new long[]{0, 500, 1000})
//                    .setDefaults(Notification.DEFAULT_LIGHTS)
//                    .setSound(soundUri)
//                    .setContentIntent(pendingIntent);
//            //.setContent(views);
//
//            mNotificationManager.notify(0, status.build());


        NotificationCompat.Builder builder = new NotificationCompat.Builder(getApplicationContext(), CHANNEL_ID)
                .setContentTitle(remoteMessage.getNotification().getTitle())
                //.setContentIntent(PendingIntent.getActivity(context, 0, new Intent(context, MainActivity_.class), 0))
                .setContentText(remoteMessage.getNotification().getBody()).setAutoCancel(true).setContentIntent(pendingIntent)
                .setAutoCancel(true)
                .setSound(soundUri)
                .setDefaults(Notification.DEFAULT_LIGHTS)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                //.setWhen(System.currentTimeMillis())
                .setSmallIcon(R.mipmap.ic_launcher);

        Notification notification = builder.build();
        NotificationManager notificationManager = (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(0, notification);
        //notificationManager.notify(0, builder.build());
        //}
        Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        // Vibrate for 500 milliseconds
        v.vibrate(3000);

        //====================================================================

        //=================== previous working ==============================
//        NotificationCompat.Builder builder = new  NotificationCompat.Builder(this, channelId)
//                .setSmallIcon(R.mipmap.ic_launcher)
//                .setContentTitle(remoteMessage.getNotification().getTitle())
//                .setSound(soundUri)
//                .setContentText(remoteMessage.getNotification().getBody()).setAutoCancel(true).setContentIntent(pendingIntent);;
//        NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//            NotificationChannel channel = new NotificationChannel(channelId, "Default channel", NotificationManager.IMPORTANCE_DEFAULT);
//            manager.createNotificationChannel(channel);
//        }
//        manager.notify(0, builder.build());
        //==================================================================

//        broadcaster = LocalBroadcastManager.getInstance(getBaseContext());
//        Intent newintent = new Intent(TestContent);
//        newintent.putExtra("Key", "Arrived");
//        broadcaster.sendBroadcast(intent);

        Intent newintent = new Intent("custom-event-name");
        newintent.putExtra("message", "This is my message!");
        LocalBroadcastManager.getInstance(this).sendBroadcast(newintent);

    }

    private void showNotification(String title,String description,
                                  String logo, String banner, String cLink){

        Log.d(TAG,"inside showNotification "+title+" "+description+" "+logo+" "+banner );
        mTitle = title;
        mDescription = description;
        Intent intent = new Intent(this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        intent.putExtra("cLink", cLink);
        PendingIntent pendingIntent = PendingIntent.getActivity(this,
                                        0,
                                        intent, //new Intent(),  //Dummy Intent do nothing
                                        PendingIntent.FLAG_UPDATE_CURRENT);

        Uri soundUri = null;
        try {
            soundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
            Log.d(TAG,"uri "+soundUri.toString());
        }catch (Exception e){
            Log.d(TAG,"exception caught "+e.toString());
        }

        mBuilder = new NotificationCompat.Builder(this)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle(title)
                .setContentText(description)
                .setContentInfo("info :"+description)
                .setContentIntent(pendingIntent)
                .setAutoCancel(true)
                .setLargeIcon(BitmapFactory.decodeResource(this.getResources(),
                        this.getResources().
                                getIdentifier("ic_launcher", "mipmap", this.getPackageName())))
                .setSound(soundUri);

        if(!banner.isEmpty()) {
//            Glide.with(getApplicationContext())
//                    .load(banner)
//                    .asBitmap()
//                    .into(new SimpleTarget<Bitmap>() {
//                        @Override
//                        public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
//                            mBuilder.setStyle(new NotificationCompat.BigPictureStyle().bigPicture(resource));
//                            NotificationManager mNotifyMgr = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
//                            mNotifyMgr.notify(randomWithRange(1, 100), mBuilder.build());
//                        }
//                    });
            GetBitmapFromURLAsync getBitmapFromURLAsync = new GetBitmapFromURLAsync();
            getBitmapFromURLAsync.execute(banner);
        }
        else {
            NotificationManager mNotifyMgr = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
            mNotifyMgr.notify(randomWithRange(1, 100), mBuilder.build());
        }
    }

    int randomWithRange(int min, int max) {
        int range = Math.abs(max - min) + 1;
        return (int)(Math.random() * range) + (min <= max ? min : max);
    }

    private class GetBitmapFromURLAsync extends AsyncTask<String, Void, Bitmap> {
        @Override
        protected Bitmap doInBackground(String... params) {
            return getBitmapFromURL(params[0]);
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            Log.d(TAG,"onPostExecute");
            if(bitmap != null){
                NotificationCompat.BigPictureStyle notiStyle = new NotificationCompat.BigPictureStyle();
                notiStyle.bigPicture(bitmap);
                notiStyle.setBigContentTitle(mTitle);
                notiStyle.setSummaryText(mDescription);
//                mBuilder.setStyle(notiStyle).
                mBuilder.setStyle(new NotificationCompat.BigTextStyle().bigText(mTitle));
            }
            NotificationManager mNotifyMgr = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
            mNotifyMgr.notify(randomWithRange(1, 100), mBuilder.build());
        }
    }

    public static Bitmap getBitmapFromURL(String src) {
        try {
            URL url = new URL(src);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoInput(true);
            connection.connect();
            InputStream input = connection.getInputStream();
            Bitmap myBitmap = BitmapFactory.decodeStream(input);
            return myBitmap;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
