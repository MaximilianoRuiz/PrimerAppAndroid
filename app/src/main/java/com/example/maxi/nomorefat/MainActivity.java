package com.example.maxi.nomorefat;

import android.content.Context;
import android.content.res.Resources;
import android.support.v4.widget.SlidingPaneLayout;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

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
        lista1.add("60 kg");
        lista1.add("61 kg");
        lista1.add("62 kg");
        lista1.add("70 kg");
        lista1.add("31 kg");
        List<String> lista1_1 = new ArrayList<>();
        lista1_1.add("62 kg");
        lista1_1.add("70 kg");
        lista1_1.add("31 kg");
        lista1_1.add("31 kg");
        lista1_1.add("31 kg");

        ViewAdapter adapter1 = new ViewAdapter(this, lista1, lista1_1);
        listView1.setAdapter(adapter1);

        List<String> lista2 = new ArrayList<>();
        lista2.add("60 kg");
        lista2.add("61 kg");
        lista2.add("62 kg");
        lista2.add("70 kg");
        lista2.add("31 kg");
        List<String> lista2_1 = new ArrayList<>();
        lista2_1.add("62 kg");
        lista2_1.add("70 kg");
        lista2_1.add("31 kg");
        lista2_1.add("31 kg");
        lista2_1.add("31 kg");

        ViewAdapter adapter2 = new ViewAdapter(this, lista2, lista2_1);
        listView2.setAdapter(adapter2);

        List<String> lista3 = new ArrayList<>();
        lista3.add("60 kg");
        lista3.add("61 kg");
        lista3.add("62 kg");
        lista3.add("70 kg");
        lista3.add("31 kg");
        List<String> lista3_1 = new ArrayList<>();
        lista3_1.add("62 kg");
        lista3_1.add("70 kg");
        lista3_1.add("31 kg");
        lista3_1.add("31 kg");
        lista3_1.add("31 kg");

        ViewAdapter adapter3 = new ViewAdapter(this, lista3, lista3_1);
        listView3.setAdapter(adapter3);

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
                }else {
                    mSlidingLayout.openPane();
                }
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    class ViewAdapter extends ArrayAdapter<String> {
        Context context;
        List<String> morningArray;
        List<String> nightArray;
        ViewAdapter(Context c, List<String> morning, List<String> night) {
            super(c, R.layout.single_row, R.id.textView2, morning);
            this.context = c;
            this.morningArray = morning;
            this.nightArray = night;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View row = inflater.inflate(R.layout.single_row, parent, false);

            TextView txtMorning = (TextView) row.findViewById(R.id.textView2);
            TextView txtNight = (TextView) row.findViewById(R.id.textView4);

            txtMorning.setText(morningArray.get(position));
            txtNight.setText(nightArray.get(position));

            return row;
        }
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
}
