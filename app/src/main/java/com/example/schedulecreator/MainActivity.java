package com.example.schedulecreator;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import com.example.schedulecreator.ViewModels.MainActivityViewModel;
import com.example.schedulecreator.adapters.ScreenSlidePageAdapter;
import com.example.schedulecreator.database.Worker;
import com.example.schedulecreator.fragments.SchedulerCreatorPersonnelListFragment;
import com.example.schedulecreator.fragments.PersonnelManagementFragment;
import com.example.schedulecreator.fragments.ScheduleCreatorFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Random;
import java.util.function.Predicate;
import java.util.stream.Collectors;

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



        //Initialize fragment ViewPager
        mViewPager = findViewById( R.id.main_activity_view_pager );
        ArrayList<Fragment> fragmentList = new ArrayList<>();
        fragmentList.add( new SchedulerCreatorPersonnelListFragment());
        fragmentList.add( new ScheduleCreatorFragment() );
        fragmentList.add( new PersonnelManagementFragment());
        mPagerAdapter = new ScreenSlidePageAdapter(getSupportFragmentManager(), getLifecycle());
        mPagerAdapter.setFragmentList( fragmentList );
        mViewPager.setAdapter( mPagerAdapter );
        mViewPager.setCurrentItem(0);


        mViewModel.getSchedulerSettPersonnelList().observe(this, new Observer<ArrayList<Worker>>() {
            @Override
            public void onChanged(ArrayList<Worker> workers) {
                Random random = new Random();



                for(Worker worker : workers){
                    worker.setnOrdinaryDays(random.nextInt(7));
                    worker.setnThursdays(random.nextInt(7));
                    worker.setnFridays(random.nextInt(7));
                    worker.setnSaturdays(random.nextInt(7));
                    worker.setnSundays(random.nextInt(7));
                }

                workers.sort((worker, t1) -> worker.getnSundays()-t1.getnSundays());


                ArrayList<Worker> bufferList = new ArrayList(workers.parallelStream().
                        filter(worker -> worker.getnSundays() == workers.get(0)
                                .getnSundays() )
                        .collect(Collectors.toList()));

//                ArrayList<Worker> bufferList = new ArrayList<>();
//                for (Worker worker:workers){
//                    if(worker.getnSundays() == workers.get(0).getnSundays() ){
//                        bufferList.add( worker );
//                    }else{
//                        break;
//                    }
//
//                }

                for (Worker worker:bufferList){
                    worker.printNumOfDays();
                }



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