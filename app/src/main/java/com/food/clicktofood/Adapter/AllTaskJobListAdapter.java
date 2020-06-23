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

import com.food.clicktofood.Model.AllTaskResponseModel;
import com.food.clicktofood.Model.JobListResponse;
import com.food.clicktofood.R;

import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Created by User on 2/28/2018.
 */

public class AllTaskJobListAdapter extends RecyclerView.Adapter<AllTaskJobListAdapter.ViewHolder> {
    Context context;
    List<AllTaskResponseModel.Member> jobslist;
    JobClicked jobClicked;
    //categoryProductClick categoryProductClick;
    private final String TAG = "berich_"+this.getClass().getSimpleName();
    SimpleDateFormat dateFormat;
    class ViewHolder extends RecyclerView.ViewHolder {
        TextView title, pickUp, drop, amount;
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
            amount = (TextView) view.findViewById(R.id.tvAmount);
        }
    }
    public AllTaskJobListAdapter(Context context, List<AllTaskResponseModel.Member> colors, JobClicked jobClicked ) {  //categoryProductClick categoryProductClick
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
    public AllTaskJobListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        context = parent.getContext();
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.all_job_list, parent, false);
        AllTaskJobListAdapter.ViewHolder viewHolder = new AllTaskJobListAdapter.ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(AllTaskJobListAdapter.ViewHolder holder, final int position) {
            holder.btnLogin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    jobClicked.jobClicked(position);
                }
            });
            holder.pickUp.setText(jobslist.get(position).getPickupLocation());
            if(jobslist.get(position).getCategoryname()==null){
                holder.title.setText("");
            }else{
                holder.title.setText(jobslist.get(position).getCategoryname()+"");
            }

            holder.drop.setText(jobslist.get(position).getCustomerAddress());
            holder.amount.setText(String.format("%,.2f", jobslist.get(position).getTotalAmount())+" AED");
    }
}
