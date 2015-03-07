package com.example.maxi.nomorefat;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;


public class MainActivity extends ActionBarActivity {

    private ListView listView;
    private DrawerLayout drawerLayout;
    private final String[] opciones = { "Opción 1", "Opción 2", "Opción 3", "Opción 4" };

    private ActionBar actionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getActionBar().setDisplayHomeAsUpEnabled(true);

        listView = (ListView) findViewById(R.id.list_view);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

        listView.setAdapter(new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, android.R.id.text1,
                opciones));

        actionBar = getSupportActionBar();

        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

        ActionBar.Tab tab = actionBar.newTab()
                .setText("Days")
                .setTabListener(new TabsListener(
                        this, "Days", DayFragment.class));
        actionBar.addTab(tab);

        tab = actionBar.newTab()
                .setText("Weeks")
                .setTabListener(new TabsListener(
                        this, "Weeks", WeekFragment.class));
        actionBar.addTab(tab);

        tab = actionBar.newTab()
                .setText("Months")
                .setTabListener(new TabsListener(
                        this, "Months", MonthFragment.class));
        actionBar.addTab(tab);

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
                if (drawerLayout.isDrawerOpen(listView)) {
                    drawerLayout.closeDrawers();
                }else {
                    MainActivity.this.finish();
                }
                return true;
        }
        return super.onKeyUp(keyCode, event);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                if (drawerLayout.isDrawerOpen(listView)) {
                    drawerLayout.closeDrawers();
                } else {
                    drawerLayout.openDrawer(listView);
                }
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public class TabsListener  implements ActionBar.TabListener {

        private Fragment fragment;
        private final String tag;

        public TabsListener(Activity activity, String tag, Class cls) {
            this.tag = tag;
            fragment = Fragment.instantiate(activity, cls.getName());
        }

        public void onTabSelected(ActionBar.Tab tab, FragmentTransaction ft) {
            ft.replace(android.R.id.content, fragment, tag);
        }

        public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction ft) {
            ft.remove(fragment);
        }

        public void onTabReselected(ActionBar.Tab tab, FragmentTransaction ft) {}
    }
}
