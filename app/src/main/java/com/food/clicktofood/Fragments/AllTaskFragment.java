package com.food.clicktofood.Fragments;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import com.food.clicktofood.Adapter.AllTaskJobListAdapter;
import com.food.clicktofood.Adapter.JobClicked;
import com.food.clicktofood.Adapter.JobListAdapter;
import com.food.clicktofood.Model.AllTaskResponseModel;
import com.food.clicktofood.Model.JobListResponse;
import com.food.clicktofood.R;
import com.food.clicktofood.Retrofit.APIInterface;
import com.food.clicktofood.Retrofit.ApiUtils;
import com.food.clicktofood.SessionData.SessionData;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AllTaskFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AllTaskFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private final String TAG = "ctf_"+this.getClass().getSimpleName();
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    View myview;
    AllTaskJobListAdapter jobListAdapter;
    RecyclerView recyclerView;
    LinearLayoutManager layoutManager;
    SessionData sessionData;
    ProgressDialog dialog;
    private CompositeDisposable mCompositeDisposable;
    APIInterface apiInterface;
    List<AllTaskResponseModel.Member> joblist;
    TextView error, dateValue, totalTask;
    SimpleDateFormat dateFormat;

    public static AllTaskFragment newInstance() {
        AllTaskFragment fragment = new AllTaskFragment();
        return fragment;
    }

    public AllTaskFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AllTaskFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AllTaskFragment newInstance(String param1, String param2) {
        AllTaskFragment fragment = new AllTaskFragment();
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
        myview = inflater.inflate(R.layout.fragment_all_task, container, false);

        sessionData = new SessionData(getActivity());
        mCompositeDisposable = new CompositeDisposable();
        apiInterface = ApiUtils.getService();
        joblist = new ArrayList<>();
        error = (TextView)myview.findViewById(R.id.tvError);
        totalTask = (TextView)myview.findViewById(R.id.tvTotalTask);

        Calendar cal = Calendar.getInstance();
        dateValue = (TextView)myview.findViewById(R.id.tvDate);
        dateValue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar newCalendar = Calendar.getInstance();
                DatePickerDialog fromDatePicker = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        Calendar newDate = Calendar.getInstance();
                        newDate.set(year, monthOfYear, dayOfMonth);
                        dateValue.setText(dateFormat.format(newDate.getTime()));
                        getAllTaskList();
                    }
                }, newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH)
                );
                fromDatePicker.show();
            }
        });
        dateFormat = new SimpleDateFormat("YYYY-MM-dd", Locale.US);
        dateValue.setText(dateFormat.format(cal.getTime()));

        recyclerView = myview.findViewById(R.id.rvJobList);
        jobListAdapter = new AllTaskJobListAdapter(getActivity(), joblist, new JobClicked() {
            @Override
            public void jobClicked(int position) {

            }
        });

        layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.hasFixedSize();
        recyclerView.setAdapter(jobListAdapter);
        getAllTaskList();
        return myview;
    }

    public void getAllTaskList(){
        dialog = ProgressDialog.show(getActivity(), "", "Data retrieving. Please wait.....", true);
        if(isNetworkAvailable()){
            //dialog = ProgressDialog.show(getApplicationContext(), "", "Signing in. Please wait.....", true);
            mCompositeDisposable.add(apiInterface.getAllJobList(sessionData.getUserDataModel().getData().getMember().get(0).getEmpID(), dateValue.getText().toString().trim()) //
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(this::handleResponsePromo, this::handleErrorPromo));
        }else{
            Toast.makeText(getActivity(), "Please check your internet connection and try again", Toast.LENGTH_LONG).show();
        }
    }


    private void handleResponsePromo(AllTaskResponseModel clientResponse) {
        Log.d(TAG, "All Joblist response "+clientResponse);
        dialog.dismiss();
        if(clientResponse.getIsSuccess()){
                error.setVisibility(View.GONE);
                joblist.clear();
                joblist.addAll(clientResponse.getData().getMember());
                recyclerView.setAdapter(jobListAdapter);
                jobListAdapter.notifyDataSetChanged();
                totalTask.setText("Total task completed : "+joblist.size());
        }else{
            totalTask.setText("Total task completed : 0");
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
}
