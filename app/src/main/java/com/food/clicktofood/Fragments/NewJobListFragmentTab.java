package com.food.clicktofood.Fragments;

import android.content.IntentFilter;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.food.clicktofood.Adapter.JoblistPgAdapter;
import com.food.clicktofood.R;
import com.food.clicktofood.SessionData.SessionData;
import com.google.android.material.tabs.TabLayout;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link NewJobListFragmentTab#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NewJobListFragmentTab extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    TabLayout tabLayout;
    View myview;
    SessionData sessionData;

    public NewJobListFragmentTab() {
        // Required empty public constructor
    }

    public static NewJobListFragmentTab newInstance() {
        NewJobListFragmentTab fragment = new NewJobListFragmentTab();
        return fragment;
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment NewJobListFragmentTab.
     */
    // TODO: Rename and change types and number of parameters
    public static NewJobListFragmentTab newInstance(String param1, String param2) {
        NewJobListFragmentTab fragment = new NewJobListFragmentTab();
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
        myview = inflater.inflate(R.layout.fragment_new_job_list_tab, container, false);
        tabLayout = (TabLayout)myview.findViewById(R.id.tab_layout);
        tabLayout.addTab(tabLayout.newTab().setText("Pending"));
        tabLayout.addTab(tabLayout.newTab().setText("Accepted"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        sessionData = new SessionData(getActivity());
        final ViewPager viewPager = (ViewPager)myview.findViewById(R.id.pager);
        final JoblistPgAdapter adapter = new JoblistPgAdapter(getFragmentManager(),tabLayout.getTabCount());
        viewPager.setAdapter(adapter);
        viewPager.setOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        return myview;
    }

    @Override
    public void onStart() {
        super.onStart();
        sessionData.setAppState(true);
    }



    @Override
    public void onStop() {
        super.onStop();
        //LocalBroadcastManager.getInstance(getActivity()).unregisterReceiver(receiver);
        sessionData.setAppState(false);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
