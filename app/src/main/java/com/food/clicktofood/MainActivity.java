package com.food.clicktofood;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatCheckBox;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.food.clicktofood.Model.LoginResponse;
import com.food.clicktofood.Model.NewUserModel;
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
    ImageView passViewHide;
    AppCompatCheckBox chk;
    String passwordValue = "";
    Boolean ischecked = false;
//    Name: Click Food
//    Email:ondemandclick2food@gmail.com
//    pass: Clicktofood2020

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setUI();
    }

    public void setUI(){
        sessionData = new SessionData(getApplicationContext());

//        setContentView(R.layout.activity_main);
//        password = (EditText) findViewById(R.id.etPassword);
//        chk = (AppCompatCheckBox)findViewById(R.id.chkRememberMe);

        if(sessionData.getUserDataModel() != null){
            //setContentView(R.layout.activity_main);
            //startActivity(new Intent(getApplicationContext(), MainActivity.class));
            mCompositeDisposable = new CompositeDisposable();
            apiInterface = ApiUtils.getService();
//            FirebaseInstanceId.getInstance().getInstanceId().addOnSuccessListener(this, instanceIdResult -> {
//                newToken = instanceIdResult.getToken();
//                Log.d(TAG, "Token " + newToken);
//            });
            String refreshedToken = FirebaseInstanceId.getInstance().getToken();
            //password.setText(sessionData.getUserDataModel().getData().getMember().get(0).getPassword());
            passwordValue = sessionData.getPassword();
            ischecked = sessionData.getRememberMe();
            postLogin(sessionData.getUserDataModel().getData().getMember().get(0).getEmail(),sessionData.getUserDataModel().getData().getMember().get(0).getPhoneNo(),passwordValue,refreshedToken);
            //finish();
        }else {
            setContentView(R.layout.activity_main);
            sessionData = new SessionData(getApplicationContext());
            chk = (AppCompatCheckBox)findViewById(R.id.chkRememberMe);
            mCompositeDisposable = new CompositeDisposable();
            apiInterface = ApiUtils.getService();
            sessionData = new SessionData(getApplicationContext());
            String refreshedToken = FirebaseInstanceId.getInstance().getToken();
            Log.d(TAG, "FCM token: " + refreshedToken);

            phone = (EditText) findViewById(R.id.etPhone);
            email = (EditText) findViewById(R.id.etEmail);
            password = (EditText) findViewById(R.id.etPassword);

            //if(sessionData.getNewUserModel() != null){
            if(sessionData.getRememberMe()){
                chk.setChecked(true);
                phone.setText(sessionData.getNewUserModel().getPhone());
                email.setText(sessionData.getNewUserModel().getEmail());
                password.setText(sessionData.getPassword());
            }else{
                chk.setChecked(false);
            }
//            }else{
//
//            }
            forgotPassword = (TextView) findViewById(R.id.tvForgotPassword);
            forgotPassword.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    AlertDialog.Builder ad = new AlertDialog.Builder(MainActivity.this);
                    ad.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                        }
                    });
                    ad.setTitle("FORGOT PASSWORD");
                    ad.setMessage("Please contact our support team for this operation");
                    ad.show();
                }
            });
            passViewHide = (ImageView) findViewById(R.id.imgPassword);
            passViewHide.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(password.getTransformationMethod().equals(PasswordTransformationMethod.getInstance())){
                        passViewHide.setImageResource(R.drawable.eye_latest_open);

                        //Show Password
                        password.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    }
                    else{
                        passViewHide.setImageResource(R.drawable.eye_logo);

                        //Hide Password
                        password.setTransformationMethod(PasswordTransformationMethod.getInstance());

                    }
                }
            });


            FirebaseInstanceId.getInstance().getInstanceId().addOnSuccessListener(this, instanceIdResult -> {
                newToken = instanceIdResult.getToken();
                Log.d(TAG, "Token " + newToken);
            });

            login = (Button) findViewById(R.id.btnLogin);
            login.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (validate() == null) {
                        if(chk.isChecked()){
                            ischecked = true;
                        }else{
                            ischecked = false;
                        }
                        passwordValue = password.getText().toString();
                        postLogin(email.getText().toString(), phone.getText().toString(), passwordValue, newToken);
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

        public void postLogin(String email, String phone, String password, String token){
        Log.d(TAG, "email "+email+" phone "+phone+" password "+password+" token "+token);
        if(isNetworkAvailable()){
            dialog = ProgressDialog.show(MainActivity.this, "", "Signing in. Please wait.....", true);
            mCompositeDisposable.add(apiInterface.postLogin(email, phone, password, token) //
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(this::handleResponsePromo, this::handleErrorPromo));
        }else{
            AlertDialog.Builder ad = new AlertDialog.Builder(MainActivity.this);
            ad.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    finish();
                }
            });
            ad.setPositiveButton("Retry", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    setUI();
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
            sessionData.setPassword(passwordValue);

            NewUserModel newUserModel = new NewUserModel();
            newUserModel.setEmail(clientResponse.getData().getMember().get(0).getEmail());
            newUserModel.setPhone(clientResponse.getData().getMember().get(0).getPhoneNo());
            newUserModel.setPassword(passwordValue);
            sessionData.setNewUserModel(newUserModel);

            if(ischecked){
                sessionData.setRememberMe(true);
            } else{
                sessionData.setRememberMe(false);
                //sessionData.setPassword("");
            }
            startActivity(new Intent(getApplicationContext(), AfterLoginActivity.class));
            finish();
        }else{
            AlertDialog.Builder ad = new AlertDialog.Builder(MainActivity.this);
            ad.setNegativeButton("Ok", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    startActivity(new Intent(getApplicationContext(), MainActivity.class));
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
//        startActivity(new Intent(getApplicationContext(), MainActivity.class));
//        //  finish();
        //Toast.makeText(getApplicationContext(), "Something went wrong, please try again", Toast.LENGTH_SHORT).show();

        AlertDialog.Builder ad = new AlertDialog.Builder(MainActivity.this);
        ad.setPositiveButton("Retry", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                setUI();
            }
        });
        ad.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                finish();
            }
        });
        ad.setMessage("Something went wrong, please try again?");
        ad.setCancelable(false);
        ad.show();
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
