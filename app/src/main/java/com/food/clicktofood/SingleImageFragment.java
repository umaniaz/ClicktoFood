package com.food.clicktofood;


import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.widget.ProgressBar;

import androidx.fragment.app.DialogFragment;

import com.github.chrisbanes.photoview.PhotoView;
import com.squareup.picasso.Callback;
import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.Picasso;


public class SingleImageFragment extends DialogFragment {
    private final String TAG = "khan_" + this.getClass().getSimpleName();
    private static final String ARG_PARAM1 = "param1";
    private String mParam1;
    ///////----------------------------view---------------------------------////////
    private View rootView;
    private PhotoView photoView;
    Button right, left;
    int rotationVariable = 0;

    /////------------------------------------------------------------------////////
    public SingleImageFragment() {
        // Required empty public constructor
    }

    public static SingleImageFragment newInstance(String param1) { //String param1
        SingleImageFragment fragment = new SingleImageFragment();
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
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_single_image, container, false);
        right = rootView.findViewById(R.id.btnRight);
        right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rotationVariable = rotationVariable + 90;
                rotate(rotationVariable);
            }
        });

        left = rootView.findViewById(R.id.btnLeft);
        left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rotationVariable = rotationVariable - 90;
                rotate(rotationVariable);
            }
        });

        getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        photoView = rootView.findViewById(R.id.imagePerson);
        final ProgressBar progressBar = rootView.findViewById(R.id.progress);
        Log.d(TAG, " mParam1 " + mParam1);

        Picasso.get()
                .load(mParam1)
                .placeholder(R.drawable.file_placeholder)
                .error(R.drawable.file_placeholder)
                .memoryPolicy(MemoryPolicy.NO_STORE)
                .into(photoView, new Callback() {
                    @Override
                    public void onSuccess() {
                        progressBar.setVisibility(View.GONE);
                    }

                    @Override
                    public void onError(Exception e) {
                        progressBar.setVisibility(View.GONE);
                    }
                });
        return rootView;
    }

    private void rotate(float degree) {
        final RotateAnimation rotateAnim = new RotateAnimation(0.0f, degree,
                RotateAnimation.RELATIVE_TO_SELF, 0.5f,
                RotateAnimation.RELATIVE_TO_SELF, 0.5f);

        rotateAnim.setDuration(0);
        rotateAnim.setFillAfter(true);
        photoView.startAnimation(rotateAnim);
    }

    @Override
    public void onStart() {
        super.onStart();
        Dialog dialog = getDialog();
        if (dialog != null) {
            dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.rgb(0, 0, 0)));
        }
    }

}