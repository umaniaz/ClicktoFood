package com.food.clicktofood;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.media.Image;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Looper;
import android.provider.Settings;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.Toast;

import com.food.clicktofood.Adapter.LocationTrackerEnabled;
import com.food.clicktofood.Adapter.ServiceStart;
import com.food.clicktofood.Fragments.ConfirmRequestFragment;
import com.food.clicktofood.Fragments.JobListFragment;
import com.food.clicktofood.Fragments.MyProfileFragment;
import com.food.clicktofood.Fragments.NavFragment;
import com.food.clicktofood.Fragments.NavigationClickListener;
import com.food.clicktofood.Model.JobListResponse;
import com.food.clicktofood.Model.LocationResponse;
import com.food.clicktofood.Model.LoginResponse;
import com.food.clicktofood.Retrofit.APIInterface;
import com.food.clicktofood.Retrofit.ApiUtils;
import com.food.clicktofood.SessionData.SessionData;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.iid.FirebaseInstanceId;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

public class AfterLoginActivity extends AppCompatActivity implements NavigationClickListener, ServiceStart {
    private final String TAG = "ctf_"+this.getClass().getSimpleName();
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle drawerToggle;
    private static final int REQUEST_LOCATION = 1;
    private Toolbar toolbar;
    static AfterLoginActivity activityA;
    RelativeLayout menu;
    ImageView logo;
    ProgressDialog dialog;
    private CompositeDisposable mCompositeDisposable;
    ProgressDialog loadingDialog;
    APIInterface apiInterface;
    SessionData sessionData;
    LocationManager locationManager;
    String latitude, longitude;
    int PERMISSION_ID = 44;
    FusedLocationProviderClient mFusedLocationClient;
    Double lat, lon;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_after_login);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Log.d(TAG, "Firebase token "+ FirebaseInstanceId.getInstance().getToken());

//        if (getSupportFragmentManager().findFragmentByTag("JobListFragment") != null) {
//            getSupportFragmentManager().popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
//            getSupportFragmentManager()
//                    .beginTransaction()
//                    .add(R.id.fragmentHolder, new JobListFragment().newInstance(), "JobListFragment")
//                    .commit();
//        } else {
//            getSupportFragmentManager()
//                    .beginTransaction()
//                    .add(R.id.fragmentHolder, new JobListFragment().newInstance(), "JobListFragment")
//                    .commit();
//        }
        setUI();
    }

    public void setUI(){
        activityA = this;

        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout_home);

        drawerToggle = setupDrawerToggle();
        drawerToggle.setDrawerIndicatorEnabled(false);
        drawerLayout.addDrawerListener(drawerToggle);
        drawerToggle.syncState();

        sessionData = new SessionData(getApplicationContext());
        mCompositeDisposable = new CompositeDisposable();

        apiInterface = ApiUtils.getService();
        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);



        menu = (RelativeLayout)findViewById(R.id.menuHolder);
        menu.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("WrongConstant")
            @Override
            public void onClick(View view) {
                drawerLayout.openDrawer(Gravity.START);
            }
        });



        logo = (ImageView) findViewById(R.id.imgLogo);
        logo.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("WrongConstant")
            @Override
            public void onClick(View view) {
                if (getSupportFragmentManager().findFragmentByTag("JobListFragment") != null) {
                    getSupportFragmentManager().popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
                    getSupportFragmentManager()
                            .beginTransaction()
                            .add(R.id.fragmentHolder, new JobListFragment().newInstance(), "JobListFragment")
                            .commit();
                } else {
                    getSupportFragmentManager()
                            .beginTransaction()
                            .add(R.id.fragmentHolder, new JobListFragment().newInstance(), "JobListFragment")
                            .commit();
                }
            }
        });


        if(getSupportFragmentManager().findFragmentByTag("NavFragment")!=null){
            getSupportFragmentManager().popBackStack("NavFragment", 0);
        }else {
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.navigationFragmentHolder, new NavFragment().newInstance(), "NavFragment")
                    .commit();
        }

        if (getSupportFragmentManager().findFragmentByTag("JobListFragment") != null) {
            getSupportFragmentManager().popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.fragmentHolder, new JobListFragment().newInstance(), "JobListFragment")
                    .commit();
        } else {
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.fragmentHolder, new JobListFragment().newInstance(), "JobListFragment")
                    .commit();
        }

//        if (getSupportFragmentManager().findFragmentByTag("ConfirmRequestFragment") != null) {
//            getSupportFragmentManager().popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
//            getSupportFragmentManager()
//                    .beginTransaction()
//                    .add(R.id.fragmentHolder, new MyProfileFragment().newInstance(), "MyProfileFragment")
//                    .commit();
//        } else {
//            getSupportFragmentManager()
//                    .beginTransaction()
//                    .add(R.id.fragmentHolder, new MyProfileFragment().newInstance(), "MyProfileFragment")
//                    .commit();
//        }
    }


    public void postLocation(){

        if(isNetworkAvailable()){
            //dialog = ProgressDialog.show(getApplicationContext(), "", "Signing in. Please wait.....", true);
            mCompositeDisposable.add(apiInterface.postLocation(lat, lon, sessionData.getUserDataModel().getData().getMember().get(0).getEmpID()) //
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(this::handleResponsePromo, this::handleErrorPromo));
        }else{
            Toast.makeText(getApplicationContext(), "Please check your internet connection and try again", Toast.LENGTH_LONG).show();
        }
    }


    private void handleResponsePromo(LocationResponse clientResponse) {
        //dialog.dismiss();
        if(clientResponse.getIsSuccess()){
            //startActivity(new Intent(getApplicationContext(), AfterLoginActivity.class));
            Log.d("Azad", "location api response "+clientResponse);
            Toast.makeText(getApplicationContext(), clientResponse.getMessage(), Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(getApplicationContext(), clientResponse.getMessage(), Toast.LENGTH_LONG).show();
        }

    }

    private void handleErrorPromo(Throwable error) {
        //dialog.dismiss();
        Toast.makeText(getApplicationContext(), "Something went wrong, in updating location", Toast.LENGTH_SHORT).show();
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

    public static AfterLoginActivity getInstance(){
        return activityA;
    }

    @Override
    public void onNavItemChanged() {
        drawerLayout.closeDrawers();
    }

    @Override
    public void setChoice(boolean value) {

    }

    @SuppressLint("WrongConstant")
    public void setDrawerOnClick(){
        drawerLayout.closeDrawer(Gravity.START);
    }

    private ActionBarDrawerToggle setupDrawerToggle() {
        return new ActionBarDrawerToggle(this, drawerLayout, toolbar,
                R.string.open,  R.string.close){
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }
        };
    }

    @Override
    public void clickService(String mood) {
        if(mood.equals("Start")){
            //startService(new Intent(this, MyService.class));

            Intent service = new Intent(this, MyService.class);
            this.startService(service);
            startService(new Intent(this, MyService.class));

//            Intent startIntent = new Intent(this, MyService.class);
//            startService(startIntent);
//            bindService(startIntent, mService, Context.BIND_AUTO_CREATE);
        }else{
            stopService(new Intent(this, MyService.class));
        }
    }
    public void stopService(){
    }

    //===================================== location tracker from internet ==============================

    @SuppressLint("MissingPermission")
    private void requestNewLocationData(){

        LocationRequest mLocationRequest = new LocationRequest();
        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        mLocationRequest.setInterval(0);
        mLocationRequest.setFastestInterval(0);
        mLocationRequest.setNumUpdates(1);

        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
        mFusedLocationClient.requestLocationUpdates(
                mLocationRequest, mLocationCallback,
                Looper.myLooper()
        );

    }

    private LocationCallback mLocationCallback = new LocationCallback() {
        @Override
        public void onLocationResult(LocationResult locationResult) {
            Location mLastLocation = locationResult.getLastLocation();
        }
    };

    private boolean checkPermissions() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            return true;
        }
        return false;
    }

    private void requestPermissions() {
        ActivityCompat.requestPermissions(
                this,
                new String[]{Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION},
                PERMISSION_ID
        );
    }

    private boolean isLocationEnabled() {
        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) || locationManager.isProviderEnabled(
                LocationManager.NETWORK_PROVIDER
        );
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == PERMISSION_ID) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                getLastLocation();
            }
        }
    }

    @SuppressLint("MissingPermission")
    private void getLastLocation(){
        if (checkPermissions()) {
            if (isLocationEnabled()) {
                mFusedLocationClient.getLastLocation().addOnCompleteListener(
                        new OnCompleteListener<Location>() {
                            @Override
                            public void onComplete(@NonNull Task<Location> task) {
                                Location location = task.getResult();
                                if (location == null) {
                                    requestNewLocationData();
                                } else {
                                    lat = location.getLatitude();
                                    lon = location.getLongitude();
                                    Log.d("Azad", "in final Latitude "+location.getLatitude()+ " Longitude "+location.getLongitude());
                                    // call network
                                    postLocation();
                                }
                            }
                        }
                );
            } else {
                Toast.makeText(this, "Turn on location", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                startActivity(intent);
            }
        } else {
            requestPermissions();
        }
    }

    //==================================================================================================/
    @Override
    protected void onResume() {
        super.onResume();
        //setUI();
        LocalBroadcastManager.getInstance(this).registerReceiver(mMessageReceiver,
                new IntentFilter("my-event-location-track"));
    }

    private BroadcastReceiver mMessageReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            // Extract data included in the Intent
            String message = intent.getStringExtra("locationTrack");
            Toast.makeText(getApplicationContext(), "Message "+message, Toast.LENGTH_LONG).show();
            if(message.equals("off")){
                Toast.makeText(getApplicationContext(), "OFf in afterlogin ", Toast.LENGTH_LONG).show();
                LocalBroadcastManager.getInstance(getApplicationContext()).unregisterReceiver(mMessageReceiver);
            }else {
                getLastLocation();
            }
        }
    };

    @Override
    protected void onPause() {
        // Unregister since the activity is not visible
        //LocalBroadcastManager.getInstance(this).unregisterReceiver(mMessageReceiver);
        super.onPause();
    }



}
