package com.example.maxi.nomorefat;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


public class DayFragment extends Fragment {

    private ListView listView1;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_day, container, false);

        listView1 = (ListView) view.findViewById(R.id.listView1);

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

        ViewAdapter adapter1 = new ViewAdapter(getActivity(), lista1, lista1_1);
        listView1.setAdapter(adapter1);

        return view;
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
}
