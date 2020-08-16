package com.food.clicktofood.Fragments;

import android.Manifest;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.food.clicktofood.Adapter.CustomMapView;
import com.food.clicktofood.Adapter.ServiceStart;
import com.food.clicktofood.AfterLoginActivity;
import com.food.clicktofood.Model.JobCompleteResponse;
import com.food.clicktofood.Model.JobListResponse;
import com.food.clicktofood.Model.StatusPostingResponse;
import com.food.clicktofood.R;
import com.food.clicktofood.Retrofit.APIInterface;
import com.food.clicktofood.Retrofit.ApiUtils;
import com.food.clicktofood.SessionData.SessionData;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.gson.Gson;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

import static android.Manifest.permission.CALL_PHONE;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ConfirmDeliveryFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ConfirmDeliveryFragment extends Fragment implements OnMapReadyCallback {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    static final int MY_PERMISSIONS_REQUEST_READ_CONTACTS = 23;
    private final String TAG = "ctf_"+this.getClass().getSimpleName();
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private GoogleMap mMap;
    SupportMapFragment mapFragment;
    // MapView mapView;
    CustomMapView mapView;
    View myview;
    Button accept, reject;
    ServiceStart serviceStart;
    ProgressDialog dialog;
    private CompositeDisposable mCompositeDisposable;
    APIInterface apiInterface;
    SessionData sessionData;
    static JobListResponse.Member jobResponse;
    static Gson gson;
    TextView pickup, cashMode, amount, dropNotes, phone, nameValue;

    public static ConfirmDeliveryFragment newInstance() {
        ConfirmDeliveryFragment fragment = new ConfirmDeliveryFragment();
        return fragment;
    }

    public ConfirmDeliveryFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @return A new instance of fragment ConfirmDeliveryFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ConfirmDeliveryFragment newInstance(String param1) {
        ConfirmDeliveryFragment fragment = new ConfirmDeliveryFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
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
        myview = inflater.inflate(R.layout.fragment_confirm_delivery, container, false);
        //mapView = (MapView) myview.findViewById(R.id.mapview);
        mapView = (CustomMapView) myview.findViewById(R.id.mapview);

        serviceStart = (ServiceStart) getActivity();
        sessionData = new SessionData(getActivity());
        mCompositeDisposable = new CompositeDisposable();
        apiInterface = ApiUtils.getService();
        gson = new Gson();
        jobResponse = gson.fromJson(mParam1, JobListResponse.Member.class);

        dropNotes = (TextView) myview.findViewById(R.id.tvDropNotes);

        if (jobResponse.getN().getDropNotes() == null) {
            dropNotes.setText(" ");
        } else {
            dropNotes.setText(jobResponse.getN().getDropNotes() + "");
        }

        phone = (TextView) myview.findViewById(R.id.tvCell);
        if (jobResponse.getN().getCustomerPhone() == null) {
            phone.setText("");
        } else {
            phone.setText(jobResponse.getN().getCustomerPhone());
        }
        phone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (jobResponse.getN().getCustomerPhone() == null) {
                    Toast.makeText(getActivity(), "No phone number ", Toast.LENGTH_LONG).show();
                } else {
                    Intent callIntent = new Intent(Intent.ACTION_CALL);
                    callIntent.setData(Uri.parse("tel:" + jobResponse.getN().getCustomerPhone()));
                    if (ContextCompat.checkSelfPermission(getActivity(), CALL_PHONE) == PackageManager.PERMISSION_GRANTED) {
                        startActivity(callIntent);
                    } else {
                        requestPermissions(new String[]{CALL_PHONE}, 1);
                    }
                }
            }
        });

        nameValue = (TextView)myview.findViewById(R.id.tvNameValue);
        nameValue.setText(jobResponse.getN().getCustomerName());

        pickup = (TextView)myview.findViewById(R.id.tvName);
        pickup.setText(jobResponse.getN().getCustomerAddress());
        cashMode = (TextView)myview.findViewById(R.id.tvPaymentType);
        cashMode.setText(jobResponse.getN().getPaymentMode());
        amount = (TextView)myview.findViewById(R.id.tvTotalValue);
        amount.setText(String.format("%,.2f", jobResponse.getN().getTotalAmount()));

        accept = (Button)myview.findViewById(R.id.btnAccept);
        accept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder ad = new AlertDialog.Builder(getActivity());
                ad.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        sentStatus(3);
                    }
                });
                ad.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                ad.setTitle("Confirmation");
                ad.setMessage("Are you sure to complete the delivery?");
                ad.setCancelable(false);
                ad.show();
            }
        });

        reject = (Button)myview.findViewById(R.id.btnReject);
        reject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               // serviceStart.clickService("Stop");
            }
        });
        mapView.onCreate(savedInstanceState);
        checkPermission();
        return myview;
    }

    private void checkPermission(){
        if ( ContextCompat.checkSelfPermission( getActivity(), android.Manifest.permission.ACCESS_COARSE_LOCATION )
                != PackageManager.PERMISSION_GRANTED ) {

            ActivityCompat.requestPermissions( getActivity(), new String[] {  android.Manifest.permission.ACCESS_COARSE_LOCATION  },
                    MY_PERMISSIONS_REQUEST_READ_CONTACTS );
        }
        else{
            mapView.getMapAsync(this);
        }

    }


    public void sentStatus(Integer status){
        dialog = ProgressDialog.show(getActivity(), "", "Data posting. Please wait.....", true);
        if(isNetworkAvailable()){
            //dialog = ProgressDialog.show(getApplicationContext(), "", "Signing in. Please wait.....", true);
            Double lat = AfterLoginActivity.getLat();
            Double lon = AfterLoginActivity.getLon();
            Log.d(TAG, "Lat "+lat+" lon "+lon);
            if(lat==null){
                lat = 0.0;
            }
            if (lon==null){
                lon = 0.0;
            }
            mCompositeDisposable.add(apiInterface.postFinalStatus(sessionData.getUserDataModel().getData().getMember().get(0).getEmpID(), jobResponse.getN().getTaskID(), status, lat, lon) //
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(this::handleResponsePromo, this::handleErrorPromo));
        }else{
            Toast.makeText(getActivity(), "Please check your internet connection and try again", Toast.LENGTH_LONG).show();
        }
    }


    private void handleResponsePromo(JobCompleteResponse clientResponse) {
        dialog.dismiss();
        if(clientResponse.getIsSuccess()){
            serviceStart.clickService("Stop");
            // if (getFragmentManager().findFragmentByTag("JobListFragment") != null || getFragmentManager().findFragmentByTag("ConfirmOrderFragment") != null) {
            AlertDialog.Builder ad = new AlertDialog.Builder(getActivity());
            ad.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    if (getFragmentManager().findFragmentByTag("JoblistFragment") != null || getFragmentManager().findFragmentByTag("ConfirmOrderFragment") != null) {
                        getFragmentManager().popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
                        getFragmentManager()
                                .beginTransaction()
                                .add(R.id.fragmentHolder, new JobListFragment().newInstance(), "JobListFragment")//.addToBackStack("JobListFragment")
                                .commit();
                    }else{
                        getFragmentManager()
                                .beginTransaction()
                                .add(R.id.fragmentHolder, new JobListFragment().newInstance(), "JobListFragment")//.addToBackStack("JobListFragment")
                                .commit();
                    }
                }
            });

            ad.setMessage(clientResponse.getMessage());
            ad.setCancelable(false);
            ad.show();
//            } else {
//                getFragmentManager()
//                        .beginTransaction()
//                        .add(R.id.fragmentHolder, new ConfirmDeliveryFragment().newInstance(), "ConfirmDeliveryFragment").addToBackStack("ConfirmDeliveryFragment")
//                        .commit();
//            }

        }else{
            AlertDialog.Builder ad = new AlertDialog.Builder(getActivity());
            ad.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
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
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_READ_CONTACTS: {
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    mapView.getMapAsync(this);
                } else {
                    AlertDialog.Builder ad = new AlertDialog.Builder(getActivity());
                    ad.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            //redirect to home
                        }
                    });
                    ad.setPositiveButton("Retry", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            checkPermission();
                        }
                    });
                    ad.setMessage("Location permission is mandatory, please grant the location permission");
                    ad.show();
                }
                return;
            }
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        mMap.getUiSettings().setZoomControlsEnabled(true);
//        LatLng latLng = new LatLng(22.342096,91.830318);
//        Marker marker = mMap.addMarker(new MarkerOptions().title("Home").position(latLng));


        Double latitude = jobResponse.getN().getDropLatitude();
        Double longitude = jobResponse.getN().getDropLongitude();
        Log.d(TAG, "Lat "+latitude+" Lon "+longitude);
        if(latitude == null || longitude == null){
            LatLng latLng = new LatLng(0.0, 0.0);
            Marker marker = mMap.addMarker(new MarkerOptions().title("Invalid coordinates").position(latLng));
            marker.showInfoWindow();
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 13));
        }else {
            LatLng latLng = new LatLng(latitude, longitude);
            Marker marker = mMap.addMarker(new MarkerOptions().title(jobResponse.getN().getCustomerAddress()).position(latLng));
            marker.showInfoWindow();
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 13));
        }

//        LatLng latLng = new LatLng(jobResponse.getN().getDropLatitude(),jobResponse.getN().getDropLongitude());
//        Marker marker = mMap.addMarker(new MarkerOptions().title(jobResponse.getN().getCustomerAddress()).position(latLng));
//        marker.showInfoWindow();
//        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 13));
    }

    @Override
    public void onResume() {
        super.onResume();
        mapView.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mapView.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mapView.onSaveInstanceState(outState);
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapView.onLowMemory();
    }

}
