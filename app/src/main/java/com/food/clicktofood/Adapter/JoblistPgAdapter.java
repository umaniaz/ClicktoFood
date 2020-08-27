package com.food.clicktofood.Adapter;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.food.clicktofood.Fragments.AcceptedTaskFragment;
import com.food.clicktofood.Fragments.JobListFragment;

public class JoblistPgAdapter extends FragmentStatePagerAdapter {
    int mNoOfTabs;

    String title[] = {"Pending","Accepted"};
    public JoblistPgAdapter(FragmentManager fm, int NumberOfTabs)
    {
        super(fm);
        this.mNoOfTabs = NumberOfTabs;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return title[position];
    }

    @Override
    public Fragment getItem(int position) {
        switch(position)
        {
            case 0:
                JobListFragment tab1 = new JobListFragment();
                return tab1;
            case 1:
                AcceptedTaskFragment tab2 = new AcceptedTaskFragment();
                return  tab2;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return mNoOfTabs;
    }
}
