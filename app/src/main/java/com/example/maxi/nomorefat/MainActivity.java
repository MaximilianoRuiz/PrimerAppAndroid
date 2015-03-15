package com.example.maxi.nomorefat;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SlidingPaneLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends ActionBarActivity implements ActionBar.TabListener{

    private ListView listView;
    private SlidingPaneLayout mSlidingLayout;
    private final String[] opciones = { "Opción 1", "Opción 2"};
    private final String[] tabs = { "Days", "Weeks", "Months" };
    private ViewPager viewPager;
    private MyAdapter mAdapter;
    private ActionBar actionBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = (ListView) findViewById(R.id.left_pane);

        mSlidingLayout = (SlidingPaneLayout) findViewById(R.id.sliding_pane_layout);

        listView.setAdapter(new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, android.R.id.text1,
                opciones));

        viewPager = (ViewPager) findViewById(R.id.pager);
        viewPager.setOnPageChangeListener( new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                Log.d("VIVZ", "onPageScrolled at position" + position);
            }

            @Override
            public void onPageSelected(int position) {
                actionBar.setSelectedNavigationItem(position);
                Log.d("VIVZ","onPageSelected at position"+ position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                if (state==ViewPager.SCROLL_STATE_IDLE){
                    Log.d("VIVZ","onPageScrollStateChanged idle");
                }
                if (state==ViewPager.SCROLL_STATE_DRAGGING){
                    Log.d("VIVZ","onPageScrollStateChanged dragging");
                }
                if (state==ViewPager.SCROLL_STATE_SETTLING){
                    Log.d("VIVZ","onPageScrollStateChanged settling");
                }
            }
        });
        actionBar = getSupportActionBar();
        mAdapter = new MyAdapter(getSupportFragmentManager());

        viewPager.setAdapter(mAdapter);
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

        for (String tab_name : tabs) {
            actionBar.addTab(actionBar.newTab().setText(tab_name)
                    .setTabListener(this));
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        switch(keyCode){
            case KeyEvent.KEYCODE_BACK:
                if (mSlidingLayout.isOpen()) {
                    mSlidingLayout.closePane();
                }else {
                    MainActivity.this.finish();
                }
                return true;
            case KeyEvent.KEYCODE_MENU:
                if (mSlidingLayout.isOpen()) {
                    mSlidingLayout.closePane();
                }else {
                    mSlidingLayout.openPane();
                }
                return true;
        }
        return super.onKeyUp(keyCode, event);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                if (mSlidingLayout.isOpen()) {
                    mSlidingLayout.closePane();
                }else if(!mSlidingLayout.isOpen()&& viewPager.getCurrentItem()==0){
                    mSlidingLayout.openPane();
                }
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void addDayDialog(View v){
        AddDayDialog myDialog = new AddDayDialog();
        myDialog.show(getFragmentManager(), "Dialog");
    }

    public void addWeekDialog(View v){
        AddWeekDialog myDialog = new AddWeekDialog();
        myDialog.show(getFragmentManager(), "Dialog");
    }

    public void addMonthDialog(View v){
        AddMonthDialog myDialog = new AddMonthDialog();
        myDialog.show(getFragmentManager(), "Dialog");
    }

    public void waitingToast(View v){
        Toast.makeText(this, "WAIT! Programador trabajando duro como un exclavo", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onTabSelected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
        viewPager.setCurrentItem(tab.getPosition());
    }

    @Override
    public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {

    }

    @Override
    public void onTabReselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {

    }

}

class MyAdapter extends FragmentPagerAdapter{

    public MyAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment=null;
        if (position==0){
            fragment = new DayFragment();
        }
        if (position==1){
            fragment = new WeekFragment();
        }
        if (position==2){
            fragment = new MonthFragment();
        }

        return fragment;
    }

    @Override
    public int getCount() {
        return 3;
    }
}

