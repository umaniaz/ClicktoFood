package com.food.clicktofood;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.food.clicktofood.Model.LoginResponse;
import com.food.clicktofood.Retrofit.APIInterface;
import com.food.clicktofood.Retrofit.ApiUtils;
import com.food.clicktofood.SessionData.SessionData;
import com.google.firebase.iid.FirebaseInstanceId;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {
    private final String TAG = "ctf_"+this.getClass().getSimpleName();

    Button login;
    ProgressDialog dialog;
    private CompositeDisposable mCompositeDisposable;
    ProgressDialog loadingDialog;
    APIInterface apiInterface;
    EditText phone, email, password;
    TextView forgotPassword;
    String newToken;
    SessionData sessionData;

//    Name: Click Food
//    Email:ondemandclick2food@gmail.com
//    pass: Clicktofood2020

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sessionData = new SessionData(getApplicationContext());

        if(sessionData.getUserDataModel() != null){
            startActivity(new Intent(getApplicationContext(), AfterLoginActivity.class));
            finish();
        }else {
            setContentView(R.layout.activity_main);
            mCompositeDisposable = new CompositeDisposable();
            apiInterface = ApiUtils.getService();

            String refreshedToken = FirebaseInstanceId.getInstance().getToken();
            Log.d(TAG, "FCM token: " + refreshedToken);

            phone = (EditText) findViewById(R.id.etPhone);
            email = (EditText) findViewById(R.id.etEmail);
            password = (EditText) findViewById(R.id.etPassword);
            forgotPassword = (TextView) findViewById(R.id.tvForgotPassword);


            FirebaseInstanceId.getInstance().getInstanceId().addOnSuccessListener(this, instanceIdResult -> {
                newToken = instanceIdResult.getToken();
                Log.d(TAG, "Token " + newToken);
            });

            login = (Button) findViewById(R.id.btnLogin);
            login.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (validate() == null) {
                        postLogin();
                    } else {
                        Toast.makeText(getApplicationContext(), validate(), Toast.LENGTH_LONG).show();
                    }
                }
            });
        }
    }

    private String validate() {
        //boolean isValid = true;

        String isValid = null;

        if (TextUtils.isEmpty(phone.getText().toString().trim())) {
            isValid = "Please enter phone";
            return isValid;
        }
        if (TextUtils.isEmpty(email.getText().toString().trim())) {
            isValid = "Please enter email";
            return isValid;
        }
        if (TextUtils.isEmpty(password.getText().toString().trim())) {
            isValid = "Please enter password";
            return isValid;
        }

        return isValid;
    }

        public void postLogin(){

        if(isNetworkAvailable()){
            dialog = ProgressDialog.show(MainActivity.this, "", "Signing in. Please wait.....", true);
            mCompositeDisposable.add(apiInterface.postLogin(email.getText().toString(), phone.getText().toString(), password.getText().toString(), newToken) //
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(this::handleResponsePromo, this::handleErrorPromo));
        }else{
            AlertDialog.Builder ad = new AlertDialog.Builder(MainActivity.this);
            ad.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    //finish();
                }
            });
            ad.setPositiveButton("Retry", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {

                }
            });
            ad.setMessage("Please check your internet connection and try again");
            ad.show();
        }
    }


    private void handleResponsePromo(LoginResponse clientResponse) {
        Log.d(TAG, "Login response "+clientResponse);
        dialog.dismiss();
        if(clientResponse.getIsSuccess()){
            sessionData.setUserDataModel(clientResponse);
            startActivity(new Intent(getApplicationContext(), AfterLoginActivity.class));
            finish();
        }else{
            AlertDialog.Builder ad = new AlertDialog.Builder(MainActivity.this);
            ad.setNegativeButton("Ok", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                }
            });
            ad.setMessage(clientResponse.getMessage());
            ad.setCancelable(false);
            ad.show();
        }

    }

    private void handleErrorPromo(Throwable error) {
        dialog.dismiss();
        Log.d(TAG, "Error "+error);
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

}
