package com.food.clicktofood;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;

import androidx.core.app.NotificationCompat;

import com.google.firebase.messaging.RemoteMessage;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

//import com.bumptech.glide.request.animation.GlideAnimation;

/**
 * Created by Royex Technologies on 2/26/2017.
 */

public class FirebaseMessagingService extends com.google.firebase.messaging.FirebaseMessagingService {

    private final String TAG = "c2f_"+this.getClass().getSimpleName();

    private NotificationCompat.Builder mBuilder;
    private String mTitle,mDescription;

    @Override
    public void onNewToken(String s) {
        super.onNewToken(s);
        Log.d("NEW_TOKEN",s);
    }


    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        Log.e(TAG,"data received = "+remoteMessage.getData().toString());

        if(!remoteMessage.getData().toString().contains("{custom=")){
            if(remoteMessage.getData().size() > 0){
                showNotification(remoteMessage.getData().get("title"),
                        remoteMessage.getData().get("body"),
                        remoteMessage.getData().get("icon"),
                        remoteMessage.getData().get("image"),
                        remoteMessage.getData().get("customlink"));

            }
        }
//        Log.d(TAG,"noti received body "+remoteMessage.getNotification().getBody().toString()+
//        " title "+remoteMessage.getNotification().getTitle().toString()+
//        " icon "+remoteMessage.getNotification().getIcon().toString());
        String cLink= "";
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
