package com.example.maxi.nomorefat;

import android.app.Activity;
import android.content.res.Resources;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SlidingPaneLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TabHost;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends ActionBarActivity {

    private ListView listView, listView1, listView2, listView3;
    private SlidingPaneLayout mSlidingLayout;
    private final String[] opciones = { "Opción 1", "Opción 2"};
    TabHost.TabSpec spec;
    TabHost tabs;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = (ListView) findViewById(R.id.left_pane);
        listView1 = (ListView) findViewById(R.id.listView1);
        listView2 = (ListView) findViewById(R.id.listView2);
        listView3 = (ListView) findViewById(R.id.listView3);
        mSlidingLayout = (SlidingPaneLayout) findViewById(R.id.sliding_pane_layout);

        List<String> lista1 = new ArrayList<>();
        lista1.add("Primero");
        lista1.add("Segundo");
        lista1.add("Primero");
        lista1.add("Segundo");
        lista1.add("Primero");

        listView1.setAdapter(new ArrayAdapter<>(this,
                R.layout.my_layout, android.R.id.text1,
                lista1));

        List<String> lista2 = new ArrayList<>();
        lista2.add("Primero");
        lista2.add("Segundo");
        lista2.add("Primero");
        lista2.add("Segundo");
        lista2.add("Primero");

        listView2.setAdapter(new ArrayAdapter<>(this,
                R.layout.my_layout, android.R.id.text1,
                lista2));

        List<String> lista3 = new ArrayList<>();
        lista3.add("Primero");
        lista3.add("Segundo");
        lista3.add("Primero");
        lista3.add("Segundo");
        lista3.add("Primero");

        listView3.setAdapter(new ArrayAdapter<>(this,
                R.layout.my_layout, android.R.id.text1,
                lista3));

        listView.setAdapter(new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, android.R.id.text1,
                opciones));

        Resources res = getResources();

        tabs=(TabHost)findViewById(android.R.id.tabhost);
        tabs.setup();

        spec=tabs.newTabSpec("mitab1");
        spec.setContent(R.id.tab1);
        spec.setIndicator("Days",
                res.getDrawable(android.R.drawable.ic_btn_speak_now));
        tabs.addTab(spec);

        spec=tabs.newTabSpec("mitab2");
        spec.setContent(R.id.tab2);
        spec.setIndicator("Weeks",
                res.getDrawable(android.R.drawable.ic_dialog_map));
        tabs.addTab(spec);

        spec=tabs.newTabSpec("mitab3");
        spec.setContent(R.id.tab3);
        spec.setIndicator("Months",
                res.getDrawable(android.R.drawable.ic_dialog_map));
        tabs.addTab(spec);

        tabs.setCurrentTab(0);
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
        }
        return super.onKeyUp(keyCode, event);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                if (mSlidingLayout.isOpen()) {
                    mSlidingLayout.closePane();
                }else {
                    mSlidingLayout.openPane();
                }
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
