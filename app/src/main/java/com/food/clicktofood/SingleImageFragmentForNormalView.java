/*
 * Copyright (c) 2019. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

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
import android.widget.ProgressBar;

import androidx.fragment.app.DialogFragment;

import com.food.clicktofood.Adapter.InternalDataProvider;
import com.github.chrisbanes.photoview.PhotoView;
import com.squareup.picasso.Callback;
import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.Picasso;


public class SingleImageFragmentForNormalView extends DialogFragment {
    private final String TAG = "khan_" + this.getClass().getSimpleName();
    private static final String ARG_PARAM1 = "param1";
    private String mParam1;
    ///////----------------------------view---------------------------------////////
    private View rootView;
    private PhotoView photoView;

    /////------------------------------------------------------------------////////
    public SingleImageFragmentForNormalView() {
        // Required empty public constructor
    }

    public static SingleImageFragmentForNormalView newInstance() {
        SingleImageFragmentForNormalView fragment = new SingleImageFragmentForNormalView();
        Bundle args = new Bundle();
        //args.putString(ARG_PARAM1, param1);
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

        getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        photoView = rootView.findViewById(R.id.imagePerson);
        final ProgressBar progressBar = rootView.findViewById(R.id.progress);
        Log.d(TAG, " mParam1 " + InternalDataProvider.getInstance().getImageFile());

        Picasso.get()
                .load(InternalDataProvider.getInstance().getImageFile())
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