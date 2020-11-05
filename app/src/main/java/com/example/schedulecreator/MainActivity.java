package com.example.schedulecreator;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import com.example.schedulecreator.adapters.ScreenSlidePageAdapter;
import com.example.schedulecreator.database.AppDatabase;
import com.example.schedulecreator.database.Worker;
import com.example.schedulecreator.fragments.PersonnelListFragment;
import com.example.schedulecreator.fragments.PersonnelManagementFragment;
import com.example.schedulecreator.fragments.ScheduleCreatorFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    private MainActivityViewModel mViewModel;
    private BottomNavigationView mBottomNavigation;
    private ViewPager2 mViewPager;
    private ScreenSlidePageAdapter mPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mBottomNavigation = findViewById( R.id.activity_bottom_navigation_view);
        mBottomNavigation.inflateMenu( R.menu.main_activity_bottom_navigation );

        //Obtain viewModel
        mViewModel = new ViewModelProvider(this).get(MainActivityViewModel.class);

        AppDatabase db = AppDatabase.initialize(this);
        db = AppDatabase.getInstance();
        if( db == null){
            Log.d("antonio", " MainActivity; app database is empty");
        }


        //DBtest
        MutableLiveData<ArrayList<Worker>> testList = mViewModel.testDb();
        testList.observe(this, new Observer<ArrayList<Worker>>() {
            @Override
            public void onChanged(ArrayList<Worker> workers) {
                if (workers == null){
                    Log.d("antonio", " MainActivity; testList change observed: null");
                }else if ( workers.size() == 0){
                    Log.d("antonio", " MainActivity; testList change observed: list is empty");
                }else if( workers.size() > 0){
                    Log.d("antonio", " MainActivity; testList change observed: list is empty");
                }

            }
        });

        //Initialize fragment ViewPager
        mViewPager = findViewById( R.id.main_activity_view_pager );
        ArrayList<Fragment> fragmentList = new ArrayList<>();
        fragmentList.add( PersonnelListFragment.newInstance( mViewModel.getScheduleGeneratorSettings().getPersonnelList() ) );
//        fragmentList.add( DatesPickingFragment.newInstance( mViewModel.getScheduleGeneratorSettings().getStartDate(), mViewModel.getScheduleGeneratorSettings().getEndDate() ));
        fragmentList.add( new ScheduleCreatorFragment() );
        fragmentList.add( new PersonnelManagementFragment());
        mPagerAdapter = new ScreenSlidePageAdapter(getSupportFragmentManager(), getLifecycle());
        mPagerAdapter.setFragmentList( fragmentList );
        mViewPager.setAdapter( mPagerAdapter );
        mViewPager.setCurrentItem(0);

        mViewModel.getScheduleGeneratorSettings().getStartDate().observe(this, new Observer<Date>() {
            @Override
            public void onChanged(Date date) {
                Log.d("antonio", " start date change observed in main activity " + date);
            }
        });

        //Connecting the bottom navigation and viewPager
        mBottomNavigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Log.d("antonio", " MenuItem id: " + item.getItemId());
                switch( item.getItemId() ){
                    case R.id.nav_action_home:
                        Log.d("antonio", " bottom navigation; setting nav_action_home");
                        mViewPager.setCurrentItem(0);
                        break;
                    case R.id.nav_action_generator:
                        Log.d("antonio", " bottom navigation; setting nav_action_generator");
                        mViewPager.setCurrentItem(1);
                        break;
                    case R.id.nav_action_personnel:
                        Log.d("antonio", " bottom navigation; setting nav_action_personnel");
                        mViewPager.setCurrentItem(2);
                        break;
                }
                return true;
            }
        });

        mViewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                switch(position){
                    case 0:
                        Log.d("antonio", " view pager; setting nav_action_home  " + position);
                        mBottomNavigation.setSelectedItemId( R.id.nav_action_home );
                        break;
                    case 1:
                        Log.d("antonio", " view pager; setting nav_action_generator " + position);
                        mBottomNavigation.setSelectedItemId( R.id.nav_action_generator );
                        break;
                    case 2:
                        Log.d("antonio", " view pager; setting snav_action_personnel " + position);
                        mBottomNavigation.setSelectedItemId( R.id.nav_action_personnel );
                        break;
                }
            }
        });


    }
}