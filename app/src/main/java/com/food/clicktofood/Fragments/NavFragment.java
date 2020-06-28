package com.food.clicktofood.Fragments;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.food.clicktofood.AfterLoginActivity;
import com.food.clicktofood.MainActivity;
import com.food.clicktofood.Model.DutyStatus;
import com.food.clicktofood.Model.LoginResponse;
import com.food.clicktofood.Model.StatusPostingResponse;
import com.food.clicktofood.R;
import com.food.clicktofood.Retrofit.APIInterface;
import com.food.clicktofood.Retrofit.ApiUtils;
import com.food.clicktofood.SessionData.SessionData;
import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link NavFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NavFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private final String TAG = "ctf_"+this.getClass().getSimpleName();
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    View myview;
    Switch onOffSwitch;
    TextView profileEdit, logout, allTask, cashCollections;
    SessionData sessionData;
    ImageView imgCat;
    ProgressDialog dialog;
    private CompositeDisposable mCompositeDisposable;
    APIInterface apiInterface;
    LoginResponse sessionResponse;
    public static NavFragment newInstance() {
        NavFragment fragment = new NavFragment();
        return fragment;
    }

    public NavFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment NavFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static NavFragment newInstance(String param1, String param2) {
        NavFragment fragment = new NavFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        myview = inflater.inflate(R.layout.fragment_nav, container, false);
        sessionData = new SessionData(getActivity());
        mCompositeDisposable = new CompositeDisposable();
        apiInterface = ApiUtils.getService();
        sessionResponse = new LoginResponse();
        imgCat = (ImageView)myview.findViewById(R.id.imgCat);
        allTask = (TextView) myview.findViewById(R.id.tvAllTask);
        allTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AfterLoginActivity.getInstance().setDrawerOnClick();
                //AfterLoginActivity.getInstance().setUI();

                getFragmentManager()
                        .beginTransaction()
                        .add(R.id.fragmentHolder, new AllTaskFragment().newInstance(), "AllTaskFragment").addToBackStack("AllTaskFragment")
                        .commit();
                //startActivity(new Intent(getActivity(), AfterLoginActivity.class));
            }
        });

        profileEdit = (TextView)myview.findViewById(R.id.tvEdit);
        profileEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AfterLoginActivity.getInstance().setDrawerOnClick();
                //AfterLoginActivity.getInstance().setUI();
                getFragmentManager()
                        .beginTransaction()
                        .add(R.id.fragmentHolder, new MyProfileFragment().newInstance(), "MyProfileFragment").addToBackStack("MyProfileFragment")
                        .commit();
            }
        });

        cashCollections = (TextView)myview.findViewById(R.id.tvCashCollection);
        cashCollections.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AfterLoginActivity.getInstance().setDrawerOnClick();
                //AfterLoginActivity.getInstance().setUI();
                getFragmentManager()
                        .beginTransaction()
                        .add(R.id.fragmentHolder, new CashCollectionsFragment().newInstance(), "CashCollectionsFragment").addToBackStack("CashCollectionsFragment")
                        .commit();
            }
        });

        logout = (TextView)myview.findViewById(R.id.tvLogout);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                postLogout();
            }
        });

        Picasso.get()
                .load(sessionData.getUserDataModel().getData().getMember().get(0).getPicture())
                .memoryPolicy(MemoryPolicy.NO_CACHE)
                .networkPolicy(NetworkPolicy.NO_CACHE)
                .fit()
                .centerCrop()
                .placeholder(R.drawable.person_placeholder)
                .error(R.drawable.person_placeholder)
                .into(imgCat);

        //Log.d(TAG, "duty status in session "+sessionData.getUserDataModel().getData().getMember().get(0).getDutyStatus());
        onOffSwitch = (Switch)myview.findViewById(R.id.on_off_switch);
        if(sessionData.getUserDataModel().getData().getMember().get(0).getDutyStatus()==1){
            onOffSwitch.setChecked(true);
        }else{
            onOffSwitch.setChecked(false);
        }

        onOffSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(onOffSwitch.isPressed()){
                    getDuty();
                }
            }
        });


        return myview;
    }

    public void getDuty(){

        if(isNetworkAvailable()){
            dialog = ProgressDialog.show(getActivity(), "", "Getting duty status. Please wait.....", true);
            mCompositeDisposable.add(apiInterface.getDutyStatus(sessionData.getUserDataModel().getData().getMember().get(0).getEmpID(), sessionData.getUserDataModel().getData().getMember().get(0).getFirebaseToken()) //
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(this::handleResponsePromo, this::handleErrorPromo));
        }else{
            AlertDialog.Builder ad = new AlertDialog.Builder(getActivity());
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


    private void handleResponsePromo(DutyStatus clientResponse) {
        Log.d(TAG, "Login response "+clientResponse);
        dialog.dismiss();
        if(clientResponse.getIsSuccess()){
            Log.d(TAG, "duty status to save "+clientResponse.getData().getMember().get(0).getDutyStatus());

            sessionResponse = sessionData.getUserDataModel();
            sessionResponse.getData().getMember().get(0).setDutyStatus(clientResponse.getData().getMember().get(0).getDutyStatus());
            sessionData.setUserDataModel(sessionResponse);
            AfterLoginActivity.getInstance().setDrawerOnClick();
            AfterLoginActivity.getInstance().setUI();
            //startActivity(new Intent(getActivity(), AfterLoginActivity.class));
            //getActivity().finish();

            //sessionData.getUserDataModel().getData().getMember().get(0).setDutyStatus(clientResponse.getData().getMember().get(0).getDutyStatus());
//            if(clientResponse.getData().getMember().get(0).getDutyStatus()==1){
//                onOffSwitch.setChecked(true);
//            }else{
//                onOffSwitch.setChecked(false);
//            }
        }else{
            AlertDialog.Builder ad = new AlertDialog.Builder(getActivity());
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
        Toast.makeText(getActivity(), "Something went wrong, please try again", Toast.LENGTH_SHORT).show();
    }

    public void postLogout(){

        if(isNetworkAvailable()){
            dialog = ProgressDialog.show(getActivity(), "", "Logging out. Please wait.....", true);
            mCompositeDisposable.add(apiInterface.postLogout(sessionData.getUserDataModel().getData().getMember().get(0).getEmpID()) //
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(this::handleResponseLogout, this::handleErrorLogout));
        }else{
            AlertDialog.Builder ad = new AlertDialog.Builder(getActivity());
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


    private void handleResponseLogout(StatusPostingResponse clientResponse) {
        Log.d(TAG, "Login response "+clientResponse);
        dialog.dismiss();
        if(clientResponse.getIsSuccess()){
            sessionResponse = sessionData.getUserDataModel();
            sessionResponse.getData().getMember().get(0).setDutyStatus(0);
            //sessionData.setUserDataModel(sessionResponse);
            sessionData.clearPrefData();
            startActivity(new Intent(getActivity(), MainActivity.class));
            getActivity().finish();
        }else{
            Toast.makeText(getActivity(), clientResponse.getMessage(), Toast.LENGTH_LONG).show();
        }

    }

    private void handleErrorLogout(Throwable error) {
        dialog.dismiss();
        Log.d(TAG, "Error "+error);
        Toast.makeText(getActivity(), "Something went wrong, during logout", Toast.LENGTH_SHORT).show();
    }

    private boolean isNetworkAvailable(){

        ConnectivityManager cm = (ConnectivityManager)getActivity().getSystemService(Context.CONNECTIVITY_SERVICE); // from arman
        NetworkInfo netinfo = cm.getActiveNetworkInfo();

        if (netinfo != null && netinfo.isConnectedOrConnecting()) {
            android.net.NetworkInfo wifi = cm.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
            android.net.NetworkInfo mobile = cm.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);

            if((mobile != null && mobile.isConnectedOrConnecting()) || (wifi != null && wifi.isConnectedOrConnecting())) return true;
            else return false;
        } else return false;
    }
}
