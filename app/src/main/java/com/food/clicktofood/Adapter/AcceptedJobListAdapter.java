package com.food.clicktofood.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.food.clicktofood.Model.AcceptedTaskListResponse;
import com.food.clicktofood.Model.JobListResponse;
import com.food.clicktofood.R;

import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Created by User on 2/28/2018.
 */

public class AcceptedJobListAdapter extends RecyclerView.Adapter<AcceptedJobListAdapter.ViewHolder> {
    Context context;
    List<AcceptedTaskListResponse.Assigned> jobslist;
    JobClicked jobClicked;
    //categoryProductClick categoryProductClick;
    private final String TAG = "berich_"+this.getClass().getSimpleName();
    SimpleDateFormat dateFormat;
    class ViewHolder extends RecyclerView.ViewHolder {
        TextView title, pickUp, drop;
        RelativeLayout fullView;
        ImageView imgCat;
        Context context;
        CardView cardView; TextView btnColor;
        Button btnLogin;
        public ViewHolder(View view) {
            super(view);
            btnLogin = (Button)view.findViewById(R.id.btnLogin);
            title = (TextView) view.findViewById(R.id.tvTitle);
            pickUp = (TextView) view.findViewById(R.id.tvPickUp);
            drop = (TextView) view.findViewById(R.id.tvDrop);
        }
    }
    public AcceptedJobListAdapter(Context context, List<AcceptedTaskListResponse.Assigned> colors, JobClicked jobClicked ) {  //categoryProductClick categoryProductClick
        jobslist = colors;
        this.context = context;
        this.jobClicked = jobClicked;
    }

    @Override
    public int getItemCount() {

        return jobslist.size();
        //return 3;
    }

    @Override
    public AcceptedJobListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        context = parent.getContext();
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.accepted_job_list, parent, false);
        AcceptedJobListAdapter.ViewHolder viewHolder = new AcceptedJobListAdapter.ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(AcceptedJobListAdapter.ViewHolder holder, final int position) {
            holder.btnLogin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    jobClicked.jobClicked(position);
                }
            });
            holder.pickUp.setText(jobslist.get(position).getTask().getPickupLocation());
            holder.title.setText(jobslist.get(position).getTask().getCategoryname());
            holder.drop.setText("Drop location - "+jobslist.get(position).getTask().getCustomerAddress());
            holder.pickUp.setText(jobslist.get(position).getTask().getPickupLocation());
    }
}
