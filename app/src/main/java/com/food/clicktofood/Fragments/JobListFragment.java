package com.food.clicktofood.Fragments;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.food.clicktofood.Adapter.JobClicked;
import com.food.clicktofood.Adapter.JobListAdapter;
import com.food.clicktofood.FirebaseMessagingService;
import com.food.clicktofood.R;
import com.food.clicktofood.SessionData.SessionData;

import java.util.ArrayList;
import java.util.List;

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

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    BroadcastReceiver receiver;
    View myview;
    JobListAdapter jobListAdapter;
    RecyclerView recyclerView;
    List<String> jobList;
    LinearLayoutManager layoutManager;
    SessionData sessionData;
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
        jobList = new ArrayList<>();
        recyclerView = myview.findViewById(R.id.rvJobList);
        jobListAdapter = new JobListAdapter(getActivity(), jobList, new JobClicked() {
            @Override
            public void jobClicked(String id) {
                getFragmentManager()
                        .beginTransaction()
                        .add(R.id.fragmentHolder, new ConfirmRequestFragment().newInstance(), "ConfirmRequestFragment").addToBackStack("ConfirmRequestFragment")
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

        return myview;
    }

    private BroadcastReceiver mMessageReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if(sessionData.getAppState()) {
                String message = intent.getStringExtra("message");
                Toast.makeText(getActivity(), "Job fragment", Toast.LENGTH_LONG).show();
            }else{
                Toast.makeText(getActivity(), "Other fragment", Toast.LENGTH_LONG).show();
            }
        }
    };


    @Override
    public void onStart() {
        super.onStart();
        Toast.makeText(getActivity(), "onStart joblist ", Toast.LENGTH_LONG).show();

        LocalBroadcastManager.getInstance(getActivity()).registerReceiver(mMessageReceiver,
                        new IntentFilter("custom-event-name"));
        sessionData.setAppState(true);
    }



    @Override
    public void onStop() {
        super.onStop();
        Toast.makeText(getActivity(), "onStop joblist ", Toast.LENGTH_LONG).show();
        //LocalBroadcastManager.getInstance(getActivity()).unregisterReceiver(receiver);
        LocalBroadcastManager.getInstance(getActivity()).unregisterReceiver(mMessageReceiver);
        sessionData.setAppState(false);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Toast.makeText(getActivity(), "onDestroy joblist ", Toast.LENGTH_LONG).show();
    }
}
