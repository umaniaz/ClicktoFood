package com.food.clicktofood.Fragments;

import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.food.clicktofood.Adapter.JobClicked;
import com.food.clicktofood.Adapter.JobListAdapter;
import com.food.clicktofood.FirebaseMessagingService;
import com.food.clicktofood.Model.JobListResponse;
import com.food.clicktofood.Model.LoginResponse;
import com.food.clicktofood.R;
import com.food.clicktofood.Retrofit.APIInterface;
import com.food.clicktofood.Retrofit.ApiUtils;
import com.food.clicktofood.SessionData.SessionData;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link JobListFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class JobListFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private final String TAG = "ctf_"+this.getClass().getSimpleName();
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    BroadcastReceiver receiver;
    View myview;
    JobListAdapter jobListAdapter;
    RecyclerView recyclerView;
    LinearLayoutManager layoutManager;
    SessionData sessionData;
    ProgressDialog dialog;
    private CompositeDisposable mCompositeDisposable;
    APIInterface apiInterface;
    List<JobListResponse.Member> joblist;
    TextView error;
    private Gson gson;
    Button reload;
    public static JobListFragment newInstance() {
        JobListFragment fragment = new JobListFragment();
        return fragment;
    }

    public JobListFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment JobListFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static JobListFragment newInstance(String param1, String param2) {
        JobListFragment fragment = new JobListFragment();
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
        myview = inflater.inflate(R.layout.fragment_job_list, container, false);

        sessionData = new SessionData(getActivity());
        gson = new Gson();

        mCompositeDisposable = new CompositeDisposable();
        apiInterface = ApiUtils.getService();
        joblist = new ArrayList<>();
        error = (TextView)myview.findViewById(R.id.tvError);

        recyclerView = myview.findViewById(R.id.rvJobList);
        jobListAdapter = new JobListAdapter(getActivity(), joblist, new JobClicked() {
            @Override
            public void jobClicked(int position) {
                String model = gson.toJson(joblist.get(position));
                getFragmentManager()
                        .beginTransaction()
                        .add(R.id.fragmentHolder, new ConfirmRequestFragment().newInstance(model), "ConfirmRequestFragment").addToBackStack("ConfirmRequestFragment")
                        .commit();
            }
        });

        layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.hasFixedSize();
        recyclerView.setAdapter(jobListAdapter);

        receiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                try {
                    String value= intent.getStringExtra("key");
                    Toast.makeText(getActivity(), value, Toast.LENGTH_LONG).show();
                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(getActivity(), "Exception "+e, Toast.LENGTH_LONG).show();
                }

            }
        };

        reload = (Button)myview.findViewById(R.id.btnReload);
        reload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getJobList();
            }
        });
        getJobList();
        return myview;
    }

    private BroadcastReceiver mMessageReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if(sessionData.getAppState()) {
                String message = intent.getStringExtra("message");
                Toast.makeText(getActivity(), "Task list reloaded", Toast.LENGTH_LONG).show();
                getJobList();
            }else{
                Toast.makeText(getActivity(), "New task available", Toast.LENGTH_LONG).show();
            }
        }
    };

    public void getJobList(){
        dialog = ProgressDialog.show(getActivity(), "", "Data retrieving. Please wait.....", true);
        if(isNetworkAvailable()){
            //dialog = ProgressDialog.show(getApplicationContext(), "", "Signing in. Please wait.....", true);
            mCompositeDisposable.add(apiInterface.getJobListNew(sessionData.getUserDataModel().getData().getMember().get(0).getEmpID()) //
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(this::handleResponsePromo, this::handleErrorPromo));
        }else{
            Toast.makeText(getActivity(), "Please check your internet connection and try again", Toast.LENGTH_LONG).show();
        }
    }


    private void handleResponsePromo(JobListResponse clientResponse) {
        Log.d(TAG, "Joblist response "+clientResponse);
        dialog.dismiss();
        if(clientResponse.getIsSuccess()){
//            if(clientResponse.getData().getCurrentStatus()==1){
//                String model = gson.toJson(clientResponse.getData().getAssigned());
//                getFragmentManager().popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
//                getFragmentManager()
//                        .beginTransaction()
//                        .add(R.id.fragmentHolder, new ConfirmOrderFragment().newInstance(model), "ConfirmOrderFragment")
//                        .commit();
//            }else if(clientResponse.getData().getCurrentStatus()==2){
//                String model = gson.toJson(clientResponse.getData().getAssigned());
//                getFragmentManager().popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
//                getFragmentManager()
//                        .beginTransaction()
//                        .add(R.id.fragmentHolder, new ConfirmDeliveryFragment().newInstance(model), "ConfirmDeliveryFragment")
//                        .commit();
//            }else {
                error.setVisibility(View.GONE);
                joblist.clear();
                joblist.addAll(clientResponse.getData().getMember());
                recyclerView.setAdapter(jobListAdapter);
                jobListAdapter.notifyDataSetChanged();
            //}
        }else{
            recyclerView.setAdapter(null);
            error.setVisibility(View.VISIBLE);
            error.setText(clientResponse.getMessage());
            //Toast.makeText(getActivity(), clientResponse.getMessage(), Toast.LENGTH_LONG).show();
        }

    }

    private void handleErrorPromo(Throwable error) {
        dialog.dismiss();
        Log.d(TAG, "Error "+error);
        Toast.makeText(getActivity(), "Something went wrong, please try again", Toast.LENGTH_SHORT).show();
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


    @Override
    public void onStart() {
        super.onStart();
        LocalBroadcastManager.getInstance(getActivity()).registerReceiver(mMessageReceiver,
                        new IntentFilter("custom-event-name"));
        sessionData.setAppState(true);
    }



    @Override
    public void onStop() {
        super.onStop();
        //LocalBroadcastManager.getInstance(getActivity()).unregisterReceiver(receiver);
        LocalBroadcastManager.getInstance(getActivity()).unregisterReceiver(mMessageReceiver);
        sessionData.setAppState(false);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
