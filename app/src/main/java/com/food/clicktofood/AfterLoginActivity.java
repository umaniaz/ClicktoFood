package com.food.clicktofood;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.Toast;

import com.food.clicktofood.Fragments.JobListFragment;
import com.food.clicktofood.Fragments.NavFragment;
import com.food.clicktofood.Fragments.NavigationClickListener;
import com.google.firebase.iid.FirebaseInstanceId;

public class AfterLoginActivity extends AppCompatActivity implements NavigationClickListener{
    private final String TAG = "ctf_"+this.getClass().getSimpleName();
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle drawerToggle;
    private Toolbar toolbar;
    static AfterLoginActivity activityA;
    RelativeLayout menu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_after_login);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        activityA = this;

        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout_home);

        drawerToggle = setupDrawerToggle();
        drawerToggle.setDrawerIndicatorEnabled(false);
        drawerLayout.addDrawerListener(drawerToggle);
        drawerToggle.syncState();

        menu = (RelativeLayout)findViewById(R.id.menuHolder);
        menu.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("WrongConstant")
            @Override
            public void onClick(View view) {
                drawerLayout.openDrawer(Gravity.START);
            }
        });

        Log.d(TAG, "Firebase token "+ FirebaseInstanceId.getInstance().getToken());

        if (getSupportFragmentManager().findFragmentByTag("JobListFragment") != null) {
            getSupportFragmentManager().popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.fragmentHolder, new JobListFragment().newInstance(), "JobListFragment")
                    .commit();
        } else {
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.fragmentHolder, new JobListFragment().newInstance(), "JobListFragment")
                    .commit();
        }

        if(getSupportFragmentManager().findFragmentByTag("NavFragment")!=null){
            getSupportFragmentManager().popBackStack("NavFragment", 0);
        }else {
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.navigationFragmentHolder, new NavFragment().newInstance(), "NavFragment")
                    .commit();
        }
    }

    public static AfterLoginActivity getInstance(){
        return activityA;
    }

    @Override
    public void onNavItemChanged() {
        drawerLayout.closeDrawers();
    }

    @Override
    public void setChoice(boolean value) {

    }

    @SuppressLint("WrongConstant")
    public void setDrawerOnClick(){
        drawerLayout.closeDrawer(Gravity.START);
    }

    private ActionBarDrawerToggle setupDrawerToggle() {
        return new ActionBarDrawerToggle(this, drawerLayout, toolbar,
                R.string.open,  R.string.close){
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }
        };
    }
}
