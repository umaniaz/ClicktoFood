package com.food.clicktofood.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.food.clicktofood.Fragments.MyProfileFragment;
import com.food.clicktofood.R;
import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by User on 2/28/2018.
 */

public class JobListAdapter extends RecyclerView.Adapter<JobListAdapter.ViewHolder> {
    Context context;
    List<String> jobslist;
    JobClicked jobClicked;
    //categoryProductClick categoryProductClick;
    private final String TAG = "berich_"+this.getClass().getSimpleName();
    SimpleDateFormat dateFormat;
    class ViewHolder extends RecyclerView.ViewHolder {
        TextView ScriptName;   TextView ScriptDate;
        RelativeLayout fullView;
        ImageView imgCat;
        Context context;
        CardView cardView; TextView btnColor;
        Button btnLogin;
        public ViewHolder(View view) {
            super(view);
            btnLogin = (Button)view.findViewById(R.id.btnLogin);
        }
    }
    public JobListAdapter(Context context, List<String> colors, JobClicked jobClicked ) {  //categoryProductClick categoryProductClick
        jobslist = colors;
        this.context = context;
        this.jobClicked = jobClicked;
    }

    @Override
    public int getItemCount() {

        //return jobslist.size();
        return 3;
    }

    @Override
    public JobListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        context = parent.getContext();
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.job_list, parent, false);
        JobListAdapter.ViewHolder viewHolder = new JobListAdapter.ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(JobListAdapter.ViewHolder holder, final int position) {
            holder.btnLogin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    jobClicked.jobClicked("test");
                }
            });
    }
}
